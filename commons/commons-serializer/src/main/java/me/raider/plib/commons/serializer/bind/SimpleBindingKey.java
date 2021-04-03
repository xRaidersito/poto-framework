package me.raider.plib.commons.serializer.bind;

public class SimpleBindingKey<T> implements BindingKey<T> {

    private final boolean named;
    private final String name;
    private final Class<T> tClass;

    public SimpleBindingKey(boolean named, String name, Class<T> tClass) {
        this.named = named;
        this.name = named ? name : null;
        this.tClass = tClass;
    }

    @Override
    public boolean isNamed() {
        return named;
    }

    @Override
    public Class<T> getLinkedClass() {
        return tClass;
    }

    @Override
    public String getName() {
        return name;
    }
}
