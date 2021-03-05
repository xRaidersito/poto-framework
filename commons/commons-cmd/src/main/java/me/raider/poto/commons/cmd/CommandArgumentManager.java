package me.raider.poto.commons.cmd;

public interface CommandArgumentManager {

    <T> CommandArgument<T> getArgument(Class<T> tClass);

    <T> void registerSupplier(Class<T> tClass, ArgumentSupplier<T> supplier);

}
