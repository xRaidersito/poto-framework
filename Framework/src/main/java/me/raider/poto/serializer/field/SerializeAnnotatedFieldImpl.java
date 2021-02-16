package me.raider.poto.serializer.field;

public class SerializeAnnotatedFieldImpl implements SerializeAnnotatedField {

    private boolean uniqueKey;
    private String name;

    public SerializeAnnotatedFieldImpl(String name, boolean uniqueKey) {
        this.uniqueKey = uniqueKey;
        this.name = name;
    }

    public SerializeAnnotatedFieldImpl() {}

    @Override
    public boolean isUniqueKey() {
        return uniqueKey;
    }

    @Override
    public void setName(String name) {
        this.name=name;
    }

    @Override
    public void setUniqueKey(boolean uniqueKey) {
        this.uniqueKey=uniqueKey;
    }

    @Override
    public String getName() {
        return name;
    }
}
