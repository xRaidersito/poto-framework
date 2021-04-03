package me.raider.plib.commons.serializer.bind;

import java.util.Map;

public interface Binder {

    Map<BindingKey<?>, Class<?>> getBindings();

    Class<?> getBinding(Class<?> clazz, String identifier);

    <T> NamedBindingBuilder<T> bind(Class<T> clazz);

}
