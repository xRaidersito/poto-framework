package me.raider.poto.commons.serializer.bind;

import me.raider.poto.commons.serializer.annotated.SerializeAnnotationProcessor;

import java.util.HashMap;
import java.util.Map;

public class SimpleBinder implements Binder {

    private Map<BindingKey<?>, Class<?>> bindings = new HashMap<>();
    private final SerializeAnnotationProcessor serializeAnnotationProcessor;

    public SimpleBinder(SerializeAnnotationProcessor serializeAnnotationProcessor) {
        this.serializeAnnotationProcessor = serializeAnnotationProcessor;
    }

    @Override
    public Map<BindingKey<?>, Class<?>> getBindings() {
        return bindings;
    }

    @Override
    public SerializeAnnotationProcessor getProcessor() {
        return serializeAnnotationProcessor;
    }

    @Override
    public <T> NamedBindingBuilder<T> bind(Class<T> clazz) {
        return new NamedBindingBuilderImpl<>(this, clazz);
    }
}
