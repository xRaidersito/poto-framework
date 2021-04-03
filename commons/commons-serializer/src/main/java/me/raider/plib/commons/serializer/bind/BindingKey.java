package me.raider.plib.commons.serializer.bind;

import me.raider.plib.commons.serializer.Key;

public interface BindingKey<T> extends Key {

    boolean isNamed();

    Class<T> getLinkedClass();

}
