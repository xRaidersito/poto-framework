package me.raider.poto.storage;

import me.raider.poto.Nameable;
import me.raider.poto.Storable;
import me.raider.poto.Typeable;

import java.util.Map;

public interface Storage<T extends Storable> extends Nameable {

    Map<String, T> get();

    void load(String key);

    void save(String key);

    StorageType getType();

}
