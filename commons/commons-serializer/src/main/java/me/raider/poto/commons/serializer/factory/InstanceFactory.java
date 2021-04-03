package me.raider.poto.commons.serializer.factory;

import me.raider.poto.commons.serializer.SerializedObject;

public interface InstanceFactory<T> {

    T create(SerializedObject serializedObject);

}
