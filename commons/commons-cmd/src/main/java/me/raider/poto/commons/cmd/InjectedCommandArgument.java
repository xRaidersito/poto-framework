package me.raider.poto.commons.cmd;

public class InjectedCommandArgument<T> extends SimpleCommandArgument<T> {

    private final T object;

    public InjectedCommandArgument(Class<T> clazz, CommandArgumentManager manager, T object) {
        super(clazz, manager);
        this.object = object;
    }

    public T getObject() {
        return object;
    }
}
