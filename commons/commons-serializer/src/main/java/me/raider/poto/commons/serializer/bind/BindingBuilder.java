package me.raider.poto.commons.serializer.bind;

public interface BindingBuilder<T> {

    void to(Class<? extends T> clazz);

}
