package me.raider.plib.commons.cmd;

@FunctionalInterface
public interface ArgumentSupplier<T> {

    T supply(Object object);

}
