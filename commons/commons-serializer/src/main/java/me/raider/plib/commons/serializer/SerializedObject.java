package me.raider.plib.commons.serializer;

import java.util.Map;

public interface SerializedObject {

    Class<?> getLinkedClass();

    Map<String, Object> getLinkedMap();

}
