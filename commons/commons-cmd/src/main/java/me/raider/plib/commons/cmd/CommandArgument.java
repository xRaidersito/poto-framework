package me.raider.plib.commons.cmd;

public interface CommandArgument<T> {

    boolean hasRequiredLiteral();

    String getRequiredLiteral();

    Class<T> getRequiredClass();

    T resolveArgument(Object object);

}
