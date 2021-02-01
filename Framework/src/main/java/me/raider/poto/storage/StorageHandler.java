package me.raider.poto.storage;

import me.raider.poto.storage.parser.StorageParser;
import me.raider.poto.storage.types.Storable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StorageHandler {

    private final Map<String, Storage<? extends Storable>> storageMap = new HashMap<>();

    public Map<String, Storage<? extends Storable>> getStorageMap() {
        return storageMap;
    }

    public void register(Storage<? extends Storable> storage) {
        this.storageMap.put(storage.getName(), storage);
    }

    public final void registerAll(List<Storage<? extends Storable>> storages) {
        for (Storage<? extends Storable> storage : storages) {
            register(storage);
        }
    }

    public void registerParsed(StorageParser<? extends Storable> storageParser) {
        this.storageMap.put(storageParser.get().getName(), storageParser.get());
    }

    public <T extends Storable> Storage<T> getStorage(String name) {
        return (Storage<T>) storageMap.get(name);
    }


}
