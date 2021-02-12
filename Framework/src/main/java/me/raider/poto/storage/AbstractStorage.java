package me.raider.poto.storage;

import com.google.common.util.concurrent.ListeningExecutorService;
import me.raider.poto.serializer.Serializer;
import me.raider.poto.storage.types.Storable;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractStorage<T extends Storable> implements Storage<T> {

    private final Map<String, T> map = new HashMap<>();

    private final String name;
    private final StorageType type;
    private final Serializer<T> serializer;
    private final ListeningExecutorService executorService;

    public AbstractStorage(String name, StorageType type, Serializer<T> serializer, ListeningExecutorService executorService) {
        this.name=name;
        this.serializer=serializer;
        this.type=type;
        this.executorService=executorService;
    }

    @Override
    public Map<String, T> get() {
        return map;
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
    public Serializer<T> getSerializer() {
        return serializer;
    }

    @Override
    public ListeningExecutorService getExecutorService() {
        return executorService;
    }
}

