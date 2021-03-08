package me.raider.poto.commons.serializer.bind;

import java.util.HashMap;
import java.util.Map;

public class SimpleBinder implements Binder {

    private Map<Class<?>, Class<?>> bindings = new HashMap<>();

    @Override
    public Map<Class<?>, Class<?>> getBindings() {
        return bindings;
    }

    @Override
    public <T> BindingBuilder<T> bind(Class<T> clazz) {
        return new BindingBuilderImpl<>(this, clazz);
    }
}
