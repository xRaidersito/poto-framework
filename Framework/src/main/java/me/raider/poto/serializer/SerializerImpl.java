package me.raider.poto.serializer;

import me.raider.poto.Factory;
import me.raider.poto.serializer.field.SerializeAnnotatedObjectFields;
import me.raider.poto.storage.types.Storable;

import java.util.Map;

public class SerializerImpl<T extends Storable> implements Serializer<T> {

    private final SerializeAnnotationProcessor annotationProcessor;
    private final Factory<T> factory;

    public SerializerImpl(Factory<T> factory) {
        this.annotationProcessor = new SerializeAnnotationProcessorImpl();
        this.factory=factory;
    }


    @Override
    public SerializedObject<T> serialize(T instance) {

        Map<String, Object> serializedMap = null;

        try {
            serializedMap = annotationProcessor.serialize(instance.getClass(), instance);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        if (serializedMap==null) {
            throw new SerializerException("Error serializing the object!");
        }

        return new SimpleSerializedObject<>(serializedMap, factory);
    }

    @Override
    public SerializedObject<T> deserialize(Map<String, Object> map) {
        return new SimpleSerializedObject<>(map, factory);
    }

    @Override
    public SerializeAnnotatedObjectFields getAllAnnotatedFields(Class<?> clazz) {

        return new SerializeAnnotatedObjectFields(annotationProcessor.processFields(clazz));

    }

}
