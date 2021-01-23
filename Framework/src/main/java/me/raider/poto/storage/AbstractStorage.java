package me.raider.poto.storage;

import me.raider.poto.internal.SerializableObject;
import me.raider.poto.storage.types.Storable;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractStorage<T extends Storable> implements Storage<T> {

    private final Map<String, T> map = new HashMap<>();

    private final String name;
    private final StorageType type;
    private final SerializableObject<T> serializableObject;

    public AbstractStorage(String name, StorageType type, SerializableObject<T> serializableObject) {
        this.name=name;
        this.serializableObject=serializableObject;
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
    public SerializableObject<T> getSerializable() {
        return serializableObject;
    }
}

