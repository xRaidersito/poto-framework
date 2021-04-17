package me.raider.plib.commons.storage;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import me.raider.commons.utils.Nameable;
import me.raider.plib.commons.serializer.SerializerManager;
import me.raider.plib.commons.storage.factory.InstanceFactoryManager;

import java.util.Map;
import java.util.Optional;

public interface Storage<T> extends Nameable {

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
    T load(String key, boolean addToCache);

    /**
     * Save an object to a database using its unique identifier.
     *
     * @param key represent the unique id of the object to save.
     */
    void save(String key, boolean removeFromCache);

    /**
     * Load in async form an object from a database using its unique identifier.
     * If this object does not exist it will create a new one.
     *
     * @param key represent the unique id of the object to load.
     */
    default ListenableFuture<T> loadAsync(String key, boolean addToCache) {
        return getExecutorService().submit(() -> load(key, addToCache));
    }

    /**
     * Save in async form an object to a database using its unique identifier.
     *
     * @param key represent the unique id of the object to save.
     */
    default ListenableFuture<?> saveAsync(String key, boolean removeFromCache) {
        return getExecutorService().submit(() -> save(key, removeFromCache));
    }

    /**
     * Gets the indicated data from the linked map of the storage.
     *
     * @param key represent the unique id of the object to get.
     */
    default Optional<T> findOne(String key) {
        return Optional.ofNullable(get().get(key));
    }

    /**
     * Load all objects from a database using its uniques identifiers.
     *
     * @param keys represent the uniques ids of the objects to load.
     */
    default void loadAll(String[] keys) {
        for (String key : keys) {
            load(key, true);
        }
    }

    /**
     * Save all objects to a database using its uniques identifiers.
     *
     * @param keys represent the uniques ids of the objects to save.
     */
    default void saveAll(String[] keys) {
        for (String key : keys) {
            save(key, true);
        }
    }

    /**
     * It will create a new object if it does not exist in the database.
     *
     * @param key represent the unique id of the object to create.
     * @return the new object.
     */
    T createIfAbsent(String key);

    /**
     * Gets the type of the storage.
     *
     * @return the {@link StorageType} of the storage.
     */
    StorageType getType();

    /**
     * Gets the linked serializer.
     *
     * @return the {@link SerializerManager} of the storage.
     */
    SerializerManager getSerializer();

    boolean hasSerializer();

    void setSerializer(boolean hasSerializer);

    /**
     * Gets the linked executor service.
     *
     * @return the {@link ListeningExecutorService} of the storage.
     */
    ListeningExecutorService getExecutorService();

    Class<T> getLinkedClass();

    InstanceFactoryManager getFactory();

}
