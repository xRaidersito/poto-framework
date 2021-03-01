package me.raider.poto.commons.cmd;

public interface CommandArgumentManager {

    <T> ContextSupplier<T> getSupplier(Class<T> tClass);

    <T> void registerSupplier(Class<T> tClass, ContextSupplier<T> supplier);

}
