package me.raider.poto.storage;

import me.raider.poto.Storable;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractStorage<T extends Storable> implements Storage<T> {

    private Map<String, T> map = new HashMap<>();

    private String name;
    private StorageType type;

    public AbstractStorage(String name, StorageType type) {
        this.name=name;
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
}

