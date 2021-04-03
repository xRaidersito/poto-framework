package me.raider.poto.commons.serializer.annotated;

import me.raider.poto.commons.serializer.Key;

public interface SerializedKey extends Key {

    boolean isKey();

    void setKey(boolean key);

    String getNamed();

    void setNamed(String named);

}
