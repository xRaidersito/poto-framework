package me.raider.plib.commons.cmd;

public interface CommandArgument<T> {

    Class<T> getRequiredClass();

    T resolveArgument(Object object);

}
