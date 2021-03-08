package me.raider.poto.commons.cmd;

import java.util.HashMap;
import java.util.Map;

public class SimpleCommandSupplierManager implements CommandSupplierManager {

    private final Map<Class<?>, ArgumentSupplier<?>> supplierMap = new HashMap<>();

    public SimpleCommandSupplierManager() {
        registerSupplier(int.class, object -> Integer.valueOf(object.toString()) );
        registerSupplier(Integer.class, object -> Integer.valueOf(object.toString()) );
        registerSupplier(String.class, object -> object.toString() );
    }

    @Override
    public <T> ArgumentSupplier<T> getSupplier(Class<T> tClass) {
        return (ArgumentSupplier<T>) supplierMap.get(tClass);
    }

    @Override
    public <T> void registerSupplier(Class<T> tClass, ArgumentSupplier<T> supplier) {
        supplierMap.put(tClass, supplier);
    }
}
