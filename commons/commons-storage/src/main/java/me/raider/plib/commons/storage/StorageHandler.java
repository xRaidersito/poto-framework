package me.raider.plib.commons.storage;

import me.raider.plib.commons.storage.parser.StorageParser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StorageHandler {

    private final Map<String, Storage<?>> storageMap = new HashMap<>();

    public Map<String, Storage<?>> getStorageMap() {
        return storageMap;
    }

    public void register(Storage<?> storage) {
        this.storageMap.put(storage.getName(), storage);
    }

    public final void registerAll(List<Storage<?>> storages) {
        for (Storage<?> storage : storages) {
            register(storage);
        }
    }

    public void registerParsed(StorageParser<?> storageParser) {
        this.storageMap.put(storageParser.get().getName(), storageParser.get());
    }

    public <T> Storage<T> getStorage(String name) {
        return (Storage<T>) storageMap.get(name);
    }

}
