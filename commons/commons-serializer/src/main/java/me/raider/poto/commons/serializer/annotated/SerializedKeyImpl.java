package me.raider.poto.commons.serializer.annotated;

public class SerializedKeyImpl implements SerializedKey {

    private final String name;

    private boolean key;

    public SerializedKeyImpl(String name) {
        this.name = name;
    }

    @Override
    public boolean isKey() {
        return key;
    }

    @Override
    public void setKey(boolean key) {
        this.key = key;
    }

    @Override
    public String getName() {
        return name;
    }
}
