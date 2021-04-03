package me.raider.plib.commons.serializer.bind;

public interface NamedBindingBuilder<T> extends BindingBuilder<T> {

    BindingBuilder<T> named(String name);

}
