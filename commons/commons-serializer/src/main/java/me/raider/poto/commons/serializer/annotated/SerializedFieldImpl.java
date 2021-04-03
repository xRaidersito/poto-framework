package me.raider.poto.commons.serializer.annotated;

import java.lang.reflect.Field;

public class SerializedFieldImpl implements SerializedField {

    private final Class<?> clazz;

    private boolean isInterface;
    private boolean serializable;
    private Field field;

    public SerializedFieldImpl(Class<?> clazz) {
        this.clazz = clazz;
    }

    @Override
    public Class<?> getClazz() {
        return clazz;
    }

    @Override
    public boolean isInterface() {
        return isInterface;
    }

    @Override
    public boolean isSerializable() {
        return serializable;
    }

    @Override
    public void setSerializable(boolean serializable) {
        this.serializable = serializable;
    }

    @Override
    public void setInterface(boolean isInterface) {
        this.isInterface = isInterface;
    }

    @Override
    public Field getField() {
        return field;
    }

    @Override
    public void setField(Field field) {
        this.field = field;
    }
}
