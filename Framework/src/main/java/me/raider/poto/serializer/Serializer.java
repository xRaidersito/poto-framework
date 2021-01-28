package me.raider.poto.serializer;

import me.raider.poto.storage.types.Storable;

import java.util.Map;

public interface Serializer<T extends Storable> {

    SerializedObject<T> serialize(T instance);

    SerializedObject<T> deserialize(Map<String, Object> map);

}
