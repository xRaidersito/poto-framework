package me.raider.poto.commons.serializer.annotated;

import me.raider.poto.commons.serializer.annotated.annotation.Key;
import me.raider.poto.commons.serializer.annotated.annotation.Serialize;

import java.lang.reflect.Field;

public class SerializeAnnotationProcessorImpl implements SerializeAnnotationProcessor {

    @Override
    public ProcessorResult process(Class<?> clazz) {

        ProcessorResult.Builder builder = new ProcessorResultImpl.Builder();

        for (Field field : getClass().getDeclaredFields()) {

            field.setAccessible(true);

            if (!field.isAnnotationPresent(Serialize.class)) {
                continue;
            }

            Serialize annotation = field.getAnnotation(Serialize.class);

            SerializedKey key = new SerializedKeyImpl(annotation.path());

            SerializedField serializedField = new SerializedFieldImpl(field.getType());
            serializedField.setField(field);

            if (field.isAnnotationPresent(Key.class)) {
                key.setKey(true);
            }

            if (field.getType().isInterface()) {
                serializedField.setInterface(true);
            }

            builder.add(key, serializedField);
            field.setAccessible(field.isAccessible());
        }

        return builder.build();
    }
}
