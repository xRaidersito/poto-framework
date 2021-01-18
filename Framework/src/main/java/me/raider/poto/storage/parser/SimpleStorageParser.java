package me.raider.poto.storage.parser;

import me.raider.poto.storage.Storable;
import me.raider.poto.storage.Storage;
import me.raider.poto.storage.StorageType;

import java.util.List;

public class SimpleStorageParser<T extends Storable> implements StorageParser<T> {

    private Storage<T> storage;

    @Override
    public void parse(List<Storage<T>> storages, String key) {

        StorageType storageType = StorageType.valueOf(key.toUpperCase());

        for (Storage<T> storage : storages) {

            if (storage.getType() == storageType) {
                this.storage = storage;
            }
        }
    }

    @Override
    public Storage<T> get() {
        return storage;
    }
}
