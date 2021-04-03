package me.raider.poto.commons.serializer.annotated;

public class SerializedKeyImpl implements SerializedKey {

    private final String name;
    private String named;

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
    public String getNamed() {
        return named;
    }

    @Override
    public void setNamed(String named) {
        this.named = named;
    }

    @Override
    public String getName() {
        return name;
    }
}
