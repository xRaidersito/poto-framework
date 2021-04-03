package me.raider.poto.commons.serializer;

import java.util.Map;

public interface SerializedObject {

    Class<?> getLinkedClass();

    Map<String, Object> getLinkedMap();

}
