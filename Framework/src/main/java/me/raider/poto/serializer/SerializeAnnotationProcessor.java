package me.raider.poto.serializer;

import me.raider.poto.storage.StorageType;

import java.util.Map;

public interface SerializeAnnotationProcessor {

    Map<String, Object> serialize(Class<?> clazz, Object instance, StorageType type) throws IllegalAccessException;

    Map<String, Object> serializeChild(Class<?> clazz, Object instance, String previousKey, StorageType type) throws IllegalAccessException;

}
