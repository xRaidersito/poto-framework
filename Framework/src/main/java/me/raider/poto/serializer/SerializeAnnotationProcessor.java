package me.raider.poto.serializer;


import java.util.Map;

public interface SerializeAnnotationProcessor {

    Map<String, Object> serialize(Class<?> clazz, Object instance) throws IllegalAccessException;

    Map<String, Object> serializeChild(Class<?> clazz, Object instance, String previousKey) throws IllegalAccessException;

}
