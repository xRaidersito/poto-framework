package me.raider.poto.commons.serializer;

import me.raider.poto.commons.serializer.annotated.ProcessorResult;
import me.raider.poto.commons.serializer.annotated.SerializeAnnotationProcessor;
import me.raider.poto.commons.serializer.annotated.SerializedField;
import me.raider.poto.commons.serializer.annotated.SerializedKey;
import me.raider.poto.commons.serializer.bind.Binder;
import me.raider.poto.commons.serializer.repository.FindableRepository;
import me.raider.poto.commons.serializer.repository.RepositorySection;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class SimpleSerializer<T> implements Serializer<T> {

    private final FindableRepository findableRepository;
    private final SerializeAnnotationProcessor processor;
    private final Binder binder;

    public SimpleSerializer(FindableRepository findableRepository, SerializeAnnotationProcessor processor, Binder binder) {
        this.findableRepository = findableRepository;
        this.processor = processor;
        this.binder = binder;
    }

    @Override
    public SerializedObject serialize(T instance, String key) {
        RepositorySection root = findableRepository.find(key);

        if (root==null) {
            return null;
        }

        serializeClass(instance.getClass(), root, instance);
        return new SimpleSerializedObject(instance.getClass(), null);
    }

    @Override
    public SerializedObject deserialize(Class<T> clazz, String key) {
        RepositorySection root = findableRepository.find(key);

        if (root==null) {
            return null;
        }

        Map<String, Object> deserializedData = deserializeClass(clazz, root);
        return new SimpleSerializedObject(clazz, deserializedData);
    }

    private void serializeClass(Class<?> clazz, RepositorySection section, Object instance) {
        ProcessorResult result = processor.process(clazz);

        for (SerializedKey field : result.getFields().keySet()) {
            SerializedField value = result.getFields().get(field);

            if (value.isInterface()) {
                Class<?> bind = binder.getBinding(value.getClazz(), field.getNamed());

                if (bind==null) {
                    throw new SerializerException("Interfaces with @Serialize annotation needs to bind");
                }

                RepositorySection newSection = section.getChild(field.getName());
                try {
                    serializeClass(bind, newSection, value.getField().get(instance));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                continue;
            }
            if (value.isSerializable()) {
                RepositorySection newSection = section.getChild(field.getName());
                try {
                    serializeClass(value.getClazz(), newSection, value.getField().get(instance));
                } catch (IllegalAccessException e) {
                        e.printStackTrace();
                }
                continue;
            }
            try {
                Field rawField = value.getField();
                rawField.setAccessible(true);
                section.getRepositoryPath().set(field.getName(), rawField.get(instance));
                rawField.setAccessible(rawField.isAccessible());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private Map<String, Object> deserializeClass(Class<?> clazz, RepositorySection section) {
        ProcessorResult result = processor.process(clazz);
        Map<String, Object> deserialized = new HashMap<>();

        for (SerializedKey field : result.getFields().keySet()) {
            SerializedField value = result.getFields().get(field);
            if (value.isInterface()) {
                Class<?> bind = binder.getBinding(value.getClazz(), field.getNamed());

                if (bind==null) {
                    throw new SerializerException("Interfaces with @Serialize annotation needs to bind");
                }

                RepositorySection newSection = section.getChild(field.getName());
                deserialized.putAll(deserializeClass(bind, newSection));
                continue;
            }
            if (value.isSerializable()) {
                RepositorySection newSection = section.getChild(field.getName());
                deserialized.putAll(deserializeClass(value.getClazz(), newSection));
                continue;
            }
            deserialized.put(field.getName(), section.getRepositoryPath().get(field.getName()));
        }
        return deserialized;
    }

}
