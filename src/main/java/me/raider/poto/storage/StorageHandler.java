package me.raider.poto.storage;

import me.raider.poto.Storable;
import me.raider.poto.storage.parser.StorageParser;

import java.util.ArrayList;
import java.util.List;

public class StorageHandler {

    private List<Storage<? extends Storable>> storageList = new ArrayList<>();

    private StorageParser<?> storageParser;

    public StorageHandler(StorageParser<? extends Storable> storageParser) {
        this.storageParser=storageParser;
    }

    public List<Storage<? extends Storable>> getStorageList() {
        return storageList;
    }

    public void add(Storage<? extends Storable> storage) {
        this.storageList.add(storage);
    }

    public void addParsed() {
        this.storageList.add(storageParser.get());
    }

    public void setStorageParser(StorageParser<? extends Storable> storageParser) {
        this.storageParser = storageParser;
    }

    public Storage<? extends Storable> getStorage(String name) {

        for (Storage<? extends Storable> storage : storageList) {

            if (storage.getName().equals(name)) {
                return storage;
            }
        }
        return null;
    }


}
