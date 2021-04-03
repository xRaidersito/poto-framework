package me.raider.plib.commons.serializer.factory;

import java.util.HashMap;
import java.util.Map;

public class SimpleInstanceFactoryManager implements InstanceFactoryManager {

    private final Map<Class<?>, InstanceFactory<?>> factories = new HashMap<>();

    @Override
    public Map<Class<?>, InstanceFactory<?>> getFactories() {
        return factories;
    }
}
