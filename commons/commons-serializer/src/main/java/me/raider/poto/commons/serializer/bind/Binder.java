package me.raider.poto.commons.serializer.bind;

import me.raider.poto.commons.serializer.annotated.SerializeAnnotationProcessor;

import java.util.Map;

public interface Binder {

    Map<BindingKey<?>, Class<?>> getBindings();

    SerializeAnnotationProcessor getProcessor();

    <T> NamedBindingBuilder<T> bind(Class<T> clazz);

}
