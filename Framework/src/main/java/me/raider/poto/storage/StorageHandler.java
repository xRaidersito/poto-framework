package me.raider.poto.storage;

import me.raider.poto.storage.parser.StorageParser;

import java.util.HashMap;
import java.util.Map;

public class StorageHandler {

    private Map<String, Storage<? extends Storable>> storageMap = new HashMap<>();

    public Map<String, Storage<? extends Storable>> getStorageMap() {
        return storageMap;
    }

    public void add(Storage<? extends Storable> storage) {
        this.storageMap.put(storage.getName(), storage);
    }

    public void addParsed(StorageParser<? extends Storable> storageParser) {
        this.storageMap.put(storageParser.get().getName(), storageParser.get());
    }

    public Storage<? extends Storable> getStorage(String name) {

        for (String key : storageMap.keySet()) {

            Storage<? extends Storable> storage = storageMap.get(key);

            if (storage.getName().equals(name)) {
                return storage;
            }
        }
        return null;
    }


}
