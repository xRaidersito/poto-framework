package me.raider.poto.commons.serializer.bind;

public class BindingBuilderImpl<T> implements BindingBuilder<T> {

    private final Binder binder;
    private final Class<T> clazz;

    public BindingBuilderImpl(Binder binder, Class<T> clazz) {
        this.binder = binder;
        this.clazz = clazz;
    }

    @Override
    public void to(Class<? extends T> clazz) {
        binder.getBindings().put(this.clazz, clazz);
    }
}
