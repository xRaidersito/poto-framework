package me.raider.plib.commons.storage.factory;

import me.raider.plib.commons.storage.StorageException;

import java.util.Map;

public interface InstanceFactoryManager {

    Map<Class<?>, InstanceFactory<?>> getFactories();

    default void createFactory(Class<?> clazz, InstanceFactory<?> factory) {
        getFactories().put(clazz, factory);
    }

    default <T> InstanceFactory<T> getFactory(Class<T> clazz) {
        InstanceFactory<T> factory = (InstanceFactory<T>) getFactories().get(clazz);
        if (factory==null) {
            throw new StorageException("The factory of " + clazz.getName() + " is not defined");
        }
        return factory;
    }

}
