package me.raider.plib.commons.serializer.factory;

import me.raider.plib.commons.serializer.SerializedObject;

public interface InstanceFactory<T> {

    T create(SerializedObject serializedObject);

}
