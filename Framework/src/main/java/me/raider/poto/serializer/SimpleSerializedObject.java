package me.raider.poto.serializer;

import me.raider.poto.Factory;
import me.raider.poto.storage.types.Storable;

import java.util.Map;

public class SimpleSerializedObject<T extends Storable> implements SerializedObject<T> {

    private final Map<String, Object> linkedMap;
    private final Factory<T> factory;

    public SimpleSerializedObject(Map<String, Object> linkedMap, Factory<T> factory) {
        this.factory=factory;
        this.linkedMap=linkedMap;
    }

    @Override
    public Map<String, Object> getLinkedMap() {
        return linkedMap;
    }

    @Override
    public T createWithData() {
        return factory.create(linkedMap);
    }
}
