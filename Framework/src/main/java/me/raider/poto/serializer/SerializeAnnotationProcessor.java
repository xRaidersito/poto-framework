package me.raider.poto.serializer;

import me.raider.poto.storage.types.Storable;

import java.util.Map;

public interface SerializeAnnotationProcessor {

    /**
     * Create a Map with all fields with @Serialize annotation.
     *
     * @param clazz A class that extends from {@link Storable}
     * @param instance The instance of the object to serialize.
     * @return A map with the serialized fields and their values.
     */
    Map<String, Object> serialize(Class<?> clazz, Object instance) throws IllegalAccessException;

    Map<String, Object> serializeChild(Class<?> clazz, Object instance, String previousKey) throws IllegalAccessException;

}
