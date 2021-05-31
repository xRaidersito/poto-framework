package me.raider.plib.commons.cmd;

import java.util.Objects;

public class CommandSupplierKey<T> implements SupplierKey<T> {

    private final Class<T> supplierClass;
    private final Class<?> argumentClass;

    public CommandSupplierKey(Class<T> supplierClass, Class<?> argumentClass) {
        this.supplierClass = supplierClass;
        this.argumentClass = argumentClass;
    }

    public CommandSupplierKey(Class<T> supplierClass) {
        this.supplierClass = supplierClass;
        this.argumentClass = CommandArgument.class;
    }

    @Override
    public Class<?> getArgumentClass() {
        return argumentClass;
    }

    @Override
    public Class<T> getSupplierClass() {
        return supplierClass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommandSupplierKey<?> supplierKey = (CommandSupplierKey<?>) o;
        return Objects.equals(supplierClass, supplierKey.supplierClass) &&
                Objects.equals(argumentClass, supplierKey.argumentClass);
    }

    @Override
    public int hashCode() {
        return Objects.hash(supplierClass, argumentClass);
    }

}
