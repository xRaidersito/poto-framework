package me.raider.plib.commons.cmd;

public interface SupplierKey<T> {

    Class<T> getSupplierClass();

    Class<?> getArgumentClass();

}
