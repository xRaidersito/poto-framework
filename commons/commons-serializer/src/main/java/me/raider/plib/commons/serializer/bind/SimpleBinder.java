package me.raider.plib.commons.serializer.bind;

import java.util.HashMap;
import java.util.Map;

public class SimpleBinder implements Binder {

    private Map<BindingKey<?>, Class<?>> bindings = new HashMap<>();

    @Override
    public Map<BindingKey<?>, Class<?>> getBindings() {
        return bindings;
    }

    @Override
    public Class<?> getBinding(Class<?> clazz, String identifier) {
        for (BindingKey<?> key : getBindings().keySet()) {
            if (key.getLinkedClass().equals(clazz)) {
                if (key.isNamed() && !identifier.equalsIgnoreCase(key.getName())) {
                    return null;
                }
                return bindings.get(key);
            }
        }
        return null;
    }

    @Override
    public <T> NamedBindingBuilder<T> bind(Class<T> clazz) {
        return new NamedBindingBuilderImpl<>(this, clazz);
    }
}
