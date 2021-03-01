package me.raider.poto.commons.cmd;

@FunctionalInterface
public interface ContextSupplier<T> {

    T supply(Object object);

}
