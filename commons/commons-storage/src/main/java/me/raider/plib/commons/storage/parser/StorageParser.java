package me.raider.plib.commons.storage.parser;

import me.raider.plib.commons.storage.Storage;
import me.raider.plib.commons.storage.StorageType;

import java.util.List;

public interface StorageParser<T> {

    /**
     * This method analyzes the {@link StorageType} of each of the {@link Storage} given in the list.
     * If the {@link StorageType} of a storage is equal to the one given in the parameter it will store it in a variable.
     *
     * @param storageList a {@link List} of storages with same name.
     * @param type the {@link StorageType} required.
     */
    void parse(List<Storage<T>> storageList, StorageType type);

    /**
     * Gets the parsed storage.
     *
     * @return the parsed {@link Storage} instance.
     */
    Storage<T> get();

}
