package me.raider.poto.storage.types.flat.yaml;

import me.raider.poto.storage.AbstractStorage;
import me.raider.poto.storage.types.Storable;
import me.raider.poto.storage.StorageType;

public class YamlStorage<T extends Storable> extends AbstractStorage<T> {

    public YamlStorage(String name) {
        super(name, StorageType.YAML);
    }

    @Override
    public void load(String key) {

    }

    @Override
    public void save(String key) {

    }
}
