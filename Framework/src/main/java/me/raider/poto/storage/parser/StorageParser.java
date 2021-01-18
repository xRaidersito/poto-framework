package me.raider.poto.storage.parser;

import me.raider.poto.storage.Storable;
import me.raider.poto.storage.Storage;

import java.util.List;

public interface StorageParser<T extends Storable> {

    void parse(List<Storage<T>> storageList, String key);

    Storage<T> get();

}
