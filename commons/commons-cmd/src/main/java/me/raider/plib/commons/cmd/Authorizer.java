package me.raider.plib.commons.cmd;

public interface Authorizer<T> extends CommandOption {

    boolean isAuthorized(T object, Command command);

}
