package me.raider.poto.commons.serializer;

import java.util.Map;

public interface SerializedObject<T> {

    Class<T> getLinkedClass();

    Map<String, Object> getLinkedMap();

}
