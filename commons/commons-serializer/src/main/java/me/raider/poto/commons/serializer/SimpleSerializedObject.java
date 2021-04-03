package me.raider.poto.commons.serializer;

import java.util.Map;

public class SimpleSerializedObject implements SerializedObject {

    private final Class<?> linkedClass;
    private final Map<String, Object> linkedMap;

    public SimpleSerializedObject(Class<?> linkedClass, Map<String, Object> linkedMap) {
        this.linkedClass = linkedClass;
        this.linkedMap = linkedMap;
    }

    @Override
    public Class<?> getLinkedClass() {
        return linkedClass;
    }

    @Override
    public Map<String, Object> getLinkedMap() {
        return linkedMap;
    }
}
