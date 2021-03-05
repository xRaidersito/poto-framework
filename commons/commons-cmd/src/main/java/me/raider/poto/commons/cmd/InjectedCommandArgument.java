package me.raider.poto.commons.cmd;

public class InjectedCommandArgument<T> extends SimpleCommandArgument<T> {

    public InjectedCommandArgument(Class<T> clazz, ArgumentSupplier<T> supplier) {
        super(clazz, supplier);
    }
}
