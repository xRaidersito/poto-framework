package me.raider.poto.command.parameter;

@FunctionalInterface
public interface ParameterCreator<T> {

    T create(String arg);

    default boolean isPresent(String arg) {
        return create(arg)!=null;
    }

}
