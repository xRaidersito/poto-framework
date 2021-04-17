package me.raider.plib.commons.storage;

import com.google.common.util.concurrent.ListeningExecutorService;
import me.raider.plib.commons.serializer.SerializerManager;
import me.raider.plib.commons.storage.factory.InstanceFactoryManager;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractStorage<T> implements Storage<T> {

    private final Map<String, T> cache = new HashMap<>();

    private final String name;
    private final StorageType type;
    private final ListeningExecutorService executorService;
    private final InstanceFactoryManager instanceFactoryManager;

    private SerializerManager serializer;
    private boolean hasSerializer;

    private final Class<T> linkedClass;

    public AbstractStorage(String name, StorageType type, SerializerManager serializer,
                           ListeningExecutorService executorService, InstanceFactoryManager instanceFactoryManager,
                           boolean hasSerializer, Class<T> linkedClass) {
        this.name = name;
        this.type = type;
        this.executorService = executorService;
        this.serializer = serializer;
        this.instanceFactoryManager = instanceFactoryManager;
        this.hasSerializer = hasSerializer;
        this.linkedClass = linkedClass;
    }

    public AbstractStorage(String name, StorageType type, InstanceFactoryManager instanceFactoryManager,
                           SerializerManager serializer, ListeningExecutorService executorService,
                           Class<T> linkedClass) {
        this.name = name;
        this.type = type;
        this.instanceFactoryManager = instanceFactoryManager;
        this.serializer = serializer;
        this.executorService = executorService;
        this.linkedClass = linkedClass;
        this.hasSerializer = true;
    }

    public AbstractStorage(String name, StorageType type, ListeningExecutorService executorService,
                           InstanceFactoryManager instanceFactoryManager, Class<T> linkedClass) {
        this.name = name;
        this.type = type;
        this.executorService = executorService;
        this.instanceFactoryManager = instanceFactoryManager;
        this.linkedClass = linkedClass;
        this.hasSerializer = false;
    }

    @Override
    public T load(String key, boolean addToCache, boolean returnAbsent) {
        if (hasSerializer) {
            T deserialized = serializer.deserialize(linkedClass, key);
            if (deserialized==null) {
                T absent = create(key);
                if (addToCache) {
                    cache.put(key, absent);
                }
                if (returnAbsent) {
                    return absent;
                }
                return null;
            }
            cache.put(key, deserialized);
            return deserialized;
        }
        throw new StorageException("You cant use this load if dont have serializer");
    }

    @Override
    public void save(String key, boolean removeFromCache) {
        if (hasSerializer) {
            T serialize = cache.get(key);
            serializer.serialize(serialize, key);
            if (removeFromCache) {
                get().remove(key);
            }
            return;
        }
        throw new StorageException("You cant use this save if dont have serializer");
    }

    @Override
    public T create(String key) {
        return instanceFactoryManager.getFactory(linkedClass).create(key);
    }

    @Override
    public Map<String, T> get() {
        return cache;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public StorageType getType() {
        return type;
    }

    @Override
    public SerializerManager getSerializer() {
        if (!hasSerializer) {
            throw new StorageException("The storage specify that serializer cant be used");
        }
        return serializer;
    }

    @Override
    public ListeningExecutorService getExecutorService() {
        return executorService;
    }

    @Override
    public boolean hasSerializer() {
        return hasSerializer;
    }

    @Override
    public void setSerializer(boolean hasSerializer) {
        this.hasSerializer = hasSerializer;
    }

    @Override
    public Class<T> getLinkedClass() {
        return linkedClass;
    }

    @Override
    public InstanceFactoryManager getFactory() {
        return instanceFactoryManager;
    }
}

