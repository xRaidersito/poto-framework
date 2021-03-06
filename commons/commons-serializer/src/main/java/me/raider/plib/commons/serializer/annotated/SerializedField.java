package me.raider.plib.commons.serializer.annotated;

import java.lang.reflect.Field;

public interface SerializedField {

    Class<?> getClazz();

    boolean isInterface();

    boolean isSerializable();

    void setSerializable(boolean serializable);

    void setInterface(boolean isInterface);

    Field getField();

    void setField(Field field);

}
