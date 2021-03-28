package me.raider.poto.commons.serializer;

public interface Serializer<T> {

    SerializedObject<T> serialize(T instance, String key);

    SerializedObject<T> deserialize(Class<T> clazz, String key);

}
