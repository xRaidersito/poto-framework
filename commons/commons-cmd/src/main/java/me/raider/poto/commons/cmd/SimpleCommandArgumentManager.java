package me.raider.poto.commons.cmd;

public class SimpleCommandArgumentManager implements CommandArgumentManager {



    @Override
    public <T> ContextSupplier<T> getSupplier(Class<T> tClass) {
        return null;
    }

    @Override
    public <T> void registerSupplier(Class<T> tClass, ContextSupplier<T> supplier) {

    }
}
