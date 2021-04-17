package me.raider.plib.commons.storage.factory;

public interface InstanceFactory<T> {

    T create(String key);

}
