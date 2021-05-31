package me.raider.plib.commons.cmd;

public class ArgumentNotRegisteredException extends RuntimeException {

    public ArgumentNotRegisteredException(Class<?> clazz) {
        super("The argument of class " + clazz.getName() + " is not registered.");
    }

}
