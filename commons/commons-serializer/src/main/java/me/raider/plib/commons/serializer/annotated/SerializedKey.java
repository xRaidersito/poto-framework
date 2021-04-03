package me.raider.plib.commons.serializer.annotated;

import me.raider.plib.commons.serializer.Key;

public interface SerializedKey extends Key {

    boolean isKey();

    void setKey(boolean key);

    String getNamed();

    void setNamed(String named);

}
