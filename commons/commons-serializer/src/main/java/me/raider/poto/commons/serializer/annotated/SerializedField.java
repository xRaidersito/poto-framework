package me.raider.poto.commons.serializer.annotated;

import java.lang.reflect.Field;

public interface SerializedField {

    Class<?> getClazz();

    boolean isInterface();

    void setInterface(boolean isInterface);

    Field getField();

    void setField(Field field);

}
