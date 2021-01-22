package me.raider.poto.storage;

import me.raider.poto.storage.parser.StorageParser;
import me.raider.poto.storage.types.Storable;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class StorageHandler {

    private Map<String, Storage<? extends Storable>> storageMap = new HashMap<>();

    public Map<String, Storage<? extends Storable>> getStorageMap() {
        return storageMap;
    }

    public void register(Storage<? extends Storable> storage) {
        this.storageMap.put(storage.getName(), storage);
    }

    public void registerAll(Storage<? extends Storable>... storages) {
        for (Storage<? extends Storable> storage : storages) {
            register(storage);
        }
    }

    public void registerParsed(StorageParser<? extends Storable> storageParser) {
        this.storageMap.put(storageParser.get().getName(), storageParser.get());
    }

    public Optional<Storage<? extends Storable>> getStorage(String name) {
        return Optional.of(storageMap.get(name));
    }


}
