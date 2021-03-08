package me.raider.poto.commons.serializer;

public interface InstanceFactory<T> {

    T create(SerializedObject<T> serializedObject);

}
