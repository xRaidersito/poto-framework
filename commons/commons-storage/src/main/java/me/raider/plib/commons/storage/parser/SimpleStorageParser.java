package me.raider.plib.commons.storage.parser;

import me.raider.plib.commons.storage.Storage;
import me.raider.plib.commons.storage.StorageType;

import java.util.List;

public class SimpleStorageParser<T> implements StorageParser<T> {

    private Storage<T> storage;

    @Override
    public void parse(List<Storage<T>> storages, StorageType type) {

        for (Storage<T> storage : storages) {

            if (storage.getType() == type) {
                this.storage = storage;
            }
        }
    }

    @Override
    public Storage<T> get() {
        return storage;
    }
}
