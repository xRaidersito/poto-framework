package me.raider.poto.commons.serializer.bind;

public interface BindingBuilder<T> {

    Binder getBinder();

    Class<T> getClazz();

    void to(Class<? extends T> clazz);

}
