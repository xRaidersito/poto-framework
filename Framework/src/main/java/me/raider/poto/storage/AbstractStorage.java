package me.raider.poto.storage;

import me.raider.poto.serializer.Serializer;
import me.raider.poto.storage.types.Storable;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractStorage<T extends Storable> implements Storage<T> {

    private final Map<String, T> map = new HashMap<>();

    private final String name;
    private final StorageType type;
    private final Serializer<T> serializer;

    public AbstractStorage(String name, StorageType type, Serializer<T> serializer) {
        this.name=name;
        this.serializer=serializer;
        this.type=type;
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
}

