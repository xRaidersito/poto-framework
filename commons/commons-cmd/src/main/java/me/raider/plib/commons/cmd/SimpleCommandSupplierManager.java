package me.raider.plib.commons.cmd;

import java.util.*;

@SuppressWarnings("unchecked")
public class SimpleCommandSupplierManager implements CommandSupplierManager {

    private final Map<SupplierKey<?>, ArgumentSupplier<?>> supplierMap = new HashMap<>();

    public SimpleCommandSupplierManager() {
        registerSupplier(Integer.class, object -> Integer.valueOf(object.toString()) );
        registerSupplier(String.class, Object::toString);
        registerSupplier(Boolean.class, object -> Boolean.valueOf(object.toString()) );
        registerSupplier(new CommandSupplierKey<>(String.class, ArrayCommandArgument.class), object -> {
            if (!(object instanceof List<?>)) {
                return null;
            }
            List<Object> objects = (List<Object>) object;
            List<String> provide = new ArrayList<>();
            for (Object obj : objects) {
                provide.add(obj.toString());
            }
            return String.join(" ", provide);
        });
    }

    @Override
    public <T> ArgumentSupplier<T> getSupplier(Class<T> tClass) {
        return (ArgumentSupplier<T>) supplierMap.get(new CommandSupplierKey<>(tClass));
    }

    @Override
    public <T> ArgumentSupplier<T> getSupplier(SupplierKey<T> supplierKey) {
        return (ArgumentSupplier<T>) supplierMap.get(supplierKey);
    }

    @Override
    public <T> void registerSupplier(Class<T> tClass, ArgumentSupplier<T> supplier) {
        supplierMap.put(new CommandSupplierKey<>(tClass), supplier);
    }

    @Override
    public <T> void registerSupplier(SupplierKey<T> supplierKey, ArgumentSupplier<T> supplier) {
        supplierMap.put(supplierKey, supplier);
    }
}
