package me.raider.plib.commons.cmd;

public class SimpleCommandArgument<T> implements CommandArgument<T> {

    private final Class<T> clazz;
    private final ArgumentSupplier<T> supplier;

    private String requiredLiteral;
    private final boolean hasLiteral;

    public SimpleCommandArgument(Class<T> clazz, ArgumentSupplier<T> supplier, boolean hasLiteral, String requiredLiteral) {
        this.clazz = clazz;
        this.supplier = supplier;
        this.hasLiteral = hasLiteral;
        this.requiredLiteral = requiredLiteral;
    }

    public SimpleCommandArgument(Class<T> clazz, ArgumentSupplier<T> supplier) {
        this.clazz = clazz;
        this.supplier = supplier;
        this.hasLiteral = false;
    }

    @Override
    public boolean hasRequiredLiteral() {
        return hasLiteral;
    }

    @Override
    public String getRequiredLiteral() {
        return requiredLiteral;
    }

    @Override
    public Class<T> getRequiredClass() {
        return clazz;
    }

    @Override
    public T resolveArgument(Object object) {
        return supplier.supply(object);
    }
}
