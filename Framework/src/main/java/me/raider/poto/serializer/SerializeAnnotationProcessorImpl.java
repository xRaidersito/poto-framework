package me.raider.poto.serializer;

import me.raider.poto.serializer.field.SerializeAnnotatedField;
import me.raider.poto.serializer.field.SerializeAnnotatedFieldImpl;
import me.raider.poto.storage.types.Storable;
import me.raider.poto.utils.Utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SerializeAnnotationProcessorImpl implements SerializeAnnotationProcessor{

    @Override
    public Map<String, Object> serialize(Class<?> clazz, Object instance) throws IllegalAccessException {

        Map<String, Object> serializeMap = new HashMap<>();

        for (Field field : Utils.getAllFields(clazz)) {

            field.setAccessible(true);

            if (!field.isAnnotationPresent(Serialize.class) || field.get(instance)==null) {
                continue;
            }

            if (Storable.class.isAssignableFrom(field.getType())) {

                if (field.getType().isInterface()) {
                    serializeMap.putAll(serializeChild(field.get(instance).getClass(), field.get(instance), field.getAnnotation(Serialize.class).path()));
                }

                serializeMap.putAll(serializeChild(field.getType(), field.get(instance), field.getAnnotation(Serialize.class).path()));
                continue;
            }

            serializeMap.put(field.getAnnotation(Serialize.class).path(), field.get(instance));
            field.setAccessible(field.isAccessible());
        }
        return serializeMap;
    }

    @Override
    public Map<String, Object> serializeChild(Class<?> clazz, Object instance, String previousKey) throws IllegalAccessException {

        Map<String, Object> serializeMap = new HashMap<>();
        Map<String, Object> subMap = new HashMap<>();

        for (Field field : Utils.getAllFields(clazz)) {

            field.setAccessible(true);

            if (!field.isAnnotationPresent(Serialize.class) || field.get(instance)==null) {
                continue;
            }

            if (Storable.class.isAssignableFrom(field.getType())) {

                if (field.getType().isInterface()) {
                    subMap.putAll(serializeChild(field.get(instance).getClass(), field.get(instance), field.getAnnotation(Serialize.class).path()));
                }

                subMap.putAll(serializeChild(field.getType(), field.get(instance), field.getAnnotation(Serialize.class).path()));
                continue;
            }

            subMap.put(field.getAnnotation(Serialize.class).path(), field.get(instance));
            serializeMap.put(previousKey, subMap);

            field.setAccessible(field.isAccessible());
        }
        return serializeMap;
    }

    @Override
    public List<SerializeAnnotatedField> processFields(Class<?> clazz) {

        List<SerializeAnnotatedField> annotatedFieldList = new ArrayList<>();

        for (Field field : Utils.getAllFields(clazz)) {

            if (field.isAnnotationPresent(Serialize.class)) {

                SerializeAnnotatedField annotatedField = new SerializeAnnotatedFieldImpl();

                annotatedField.setName(field.getAnnotation(Serialize.class).path());
                annotatedField.setUniqueKey(false);

                if (field.isAnnotationPresent(Key.class)) {
                    annotatedField.setUniqueKey(true);
                }

                annotatedFieldList.add(annotatedField);

            }
        }
        return annotatedFieldList;
    }


}
