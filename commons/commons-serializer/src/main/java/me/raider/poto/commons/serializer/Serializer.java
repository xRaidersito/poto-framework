package me.raider.poto.commons.serializer;

public interface Serializer<T> {

    SerializedObject serialize(T instance, String key);

    SerializedObject deserialize(Class<T> clazz, String key);

}
