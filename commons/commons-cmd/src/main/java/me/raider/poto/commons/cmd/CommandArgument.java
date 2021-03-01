package me.raider.poto.commons.cmd;

public interface CommandArgument<T> {

    Class<T> getRequiredClass();

    T resolveArgument(Object object);

}
