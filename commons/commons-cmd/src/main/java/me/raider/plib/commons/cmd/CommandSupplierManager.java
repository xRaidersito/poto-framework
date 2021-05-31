package me.raider.plib.commons.cmd;

public interface CommandSupplierManager {

    <T> ArgumentSupplier<T> getSupplier(Class<T> tClass);

    <T> ArgumentSupplier<T> getSupplier(SupplierKey<T> supplierKey);

    <T> void registerSupplier(Class<T> tClass, ArgumentSupplier<T> supplier);

    <T> void registerSupplier(SupplierKey<T> supplierKey, ArgumentSupplier<T> supplier);

}
