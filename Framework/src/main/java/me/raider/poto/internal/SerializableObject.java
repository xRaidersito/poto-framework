package me.raider.poto.internal;

import me.raider.poto.storage.types.Storable;

import java.util.Map;

public interface SerializableObject<T extends Storable> {



    Map<String, Object> serialize(Class<?> clazz, Object instance, boolean yaml) throws IllegalAccessException;

    T deserialize(Map<String, Object> map);

}
