package me.raider.poto.commons.serializer.bind;

public class NamedBindingBuilderImpl<T> extends BindingBuilderImpl<T> implements NamedBindingBuilder<T> {

    public NamedBindingBuilderImpl(Binder binder, Class<T> clazz) {
        super(binder, clazz);
    }

    @Override
    public BindingBuilder<T> named(String name) {
        return new BindingBuilderImpl<>(getBinder(), getClazz(), name);
    }
}
