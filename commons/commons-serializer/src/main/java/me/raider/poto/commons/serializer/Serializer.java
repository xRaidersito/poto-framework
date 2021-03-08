package me.raider.poto.commons.serializer;

public interface Serializer<T> {

    SerializedObject<T> serialize();

    SerializedObject<T> deserialize();

}
