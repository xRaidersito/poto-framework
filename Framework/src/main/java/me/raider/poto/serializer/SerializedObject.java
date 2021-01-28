package me.raider.poto.serializer;

import me.raider.poto.storage.StorageType;
import me.raider.poto.storage.types.Storable;

import java.util.Map;

public interface SerializedObject<T extends Storable> {

    Map<String, Object> getLinkedMap();

    T createWithData();

    StorageType getStorageType();

}
