package me.raider.poto.storage;

import me.raider.poto.Storable;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractStorage<T extends Storable> implements Storage<T> {

    private Map<String, T> map = new HashMap<>();

    @Override
    public Map<String, T> get() {
        return map;
    }
}
