package me.raider.poto.commons.serializer.bind;

import me.raider.poto.commons.serializer.Key;

public interface BindingKey<T> extends Key {

    boolean isNamed();

    Class<T> getLinkedClass();

}
