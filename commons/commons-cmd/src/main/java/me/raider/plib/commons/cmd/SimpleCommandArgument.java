package me.raider.plib.commons.cmd;

public class SimpleCommandArgument<T> implements CommandArgument<T> {

    private final Class<T> clazz;
    private final ArgumentSupplier<T> supplier;

    public SimpleCommandArgument(Class<T> clazz, ArgumentSupplier<T> supplier) {
        this.clazz = clazz;
        this.supplier = supplier;
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
