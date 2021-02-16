package me.raider.poto.serializer.field;

import me.raider.poto.Nameable;

public interface SerializeAnnotatedField extends Nameable {

    boolean isUniqueKey();

    void setName(String name);

    void setUniqueKey(boolean uniqueKey);

}
