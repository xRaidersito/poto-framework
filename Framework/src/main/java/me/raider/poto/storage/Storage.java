package me.raider.poto.storage;

import me.raider.poto.Nameable;
import me.raider.poto.serializer.Serializer;
import me.raider.poto.storage.types.Storable;

import java.util.Map;

public interface Storage<T extends Storable> extends Nameable {

    /**
     * Gets the instance of the map that storage the data.
     *
     * @return the {@link Map} of the storage.
     */
    Map<String, T> get();

    /**
     * Load an object from a database using its unique identifier.
     * If this object does not exist it will create a new one.
     *
     * @param key represent the unique id of the object to load.
     */
    T load(String key);

    /**
     * Save an object to a database using its unique identifier.
     *
     * @param key represent the unique id of the object to load.
     */
    void save(String key);



    T createIfAbsent(String key);


    /**
     * Gets the type of the storage.
     *
     * @return the {@link StorageType} of the storage.
     */
    StorageType getType();

    Serializer<T> getSerializer();

}
