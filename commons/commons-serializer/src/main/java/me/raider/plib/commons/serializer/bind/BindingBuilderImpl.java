package me.raider.plib.commons.serializer.bind;

public class BindingBuilderImpl<T> implements BindingBuilder<T> {

    private final Binder binder;
    private final Class<T> clazz;
    private String name;

    public BindingBuilderImpl(Binder binder, Class<T> clazz, String name) {
        this.binder = binder;
        this.clazz = clazz;
        this.name = name;
    }

    public BindingBuilderImpl(Binder binder, Class<T> clazz) {
        this.binder = binder;
        this.clazz = clazz;
    }

    @Override
    public Binder getBinder() {
        return binder;
    }

    @Override
    public Class<T> getClazz() {
        return clazz;
    }

    @Override
    public void to(Class<? extends T> clazz) {
        BindingKey<T> bindingKey = new SimpleBindingKey<>(name!=null, name, this.clazz);
        binder.getBindings().put(bindingKey, clazz);
    }
}
