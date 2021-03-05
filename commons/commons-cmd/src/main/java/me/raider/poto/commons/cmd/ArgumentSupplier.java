package me.raider.poto.commons.cmd;

@FunctionalInterface
public interface ArgumentSupplier<T> {

    T supply(Object object);

}
