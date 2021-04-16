package me.raider.plib.commons.storage;

import com.google.common.util.concurrent.ListeningExecutorService;
import me.raider.plib.commons.serializer.SerializerManager;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractStorage<T> implements Storage<T> {

    private final Map<String, T> cache = new HashMap<>();

    private final String name;
    private final StorageType type;
    private final ListeningExecutorService executorService;

    private SerializerManager serializer;
    private final boolean hasSerializer;

    public AbstractStorage(String name, StorageType type, SerializerManager serializer,
                           ListeningExecutorService executorService, boolean hasSerializer) {
        this.name = name;
        this.type = type;
        this.executorService = executorService;
        this.serializer = serializer;
        this.hasSerializer = hasSerializer;
    }

    public AbstractStorage(String name, StorageType type, SerializerManager serializer,
                           ListeningExecutorService executorService) {
        this.name = name;
        this.type = type;
        this.executorService = executorService;
        this.hasSerializer = true;
    }

    public AbstractStorage(String name, StorageType type, ListeningExecutorService executorService) {
        this.name = name;
        this.type = type;
        this.executorService = executorService;
        this.hasSerializer = false;
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
        if (hasSerializer) {
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
}

