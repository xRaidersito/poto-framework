package me.raider.poto.commons.cmd;

public class SimpleCommandArgument<T> implements CommandArgument<T> {

    private final Class<T> clazz;
    private final CommandArgumentManager manager;

    public SimpleCommandArgument(Class<T> clazz, CommandArgumentManager manager) {
        this.clazz = clazz;
        this.manager = manager;
    }

    @Override
    public Class<T> getRequiredClass() {
        return clazz;
    }

    @Override
    public T resolveArgument(Object object) {
        return manager.getSupplier(clazz).supply(object);
    }
}
