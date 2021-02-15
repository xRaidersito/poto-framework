package me.raider.poto.serializer;

import me.raider.poto.storage.types.Storable;

import java.util.Map;

public interface SerializedObject<T extends Storable> {

    /**
     * Gets the instance of the linked map.
     *
     * @return the linked {@link Map} of the serializated object.
     */
    Map<String, Object> getLinkedMap();

    /**
     * It will create a new object from the given data.
     *
     * @return A new object.
     */
    T createWithData();

}
