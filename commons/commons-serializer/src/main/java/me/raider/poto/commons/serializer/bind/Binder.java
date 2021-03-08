package me.raider.poto.commons.serializer.bind;

import java.util.Map;

public interface Binder {

    Map<Class<?>, Class<?>> getBindings();

    <T> BindingBuilder<T> bind(Class<T> clazz);

}
