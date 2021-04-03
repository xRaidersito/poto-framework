package me.raider.plib.commons.cmd;

public interface CommandSupplierManager {

    <T> ArgumentSupplier<T> getSupplier(Class<T> tClass);

    <T> void registerSupplier(Class<T> tClass, ArgumentSupplier<T> supplier);

}
