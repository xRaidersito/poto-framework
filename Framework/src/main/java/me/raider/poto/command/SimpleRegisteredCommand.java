package me.raider.poto.command;

import me.raider.poto.command.annotation.Command;

import java.lang.reflect.Method;

public class SimpleRegisteredCommand implements RegisteredCommand {

    private final Method[] methods;
    private final Command command;
    private final PotoCommand potoCommand;

    public SimpleRegisteredCommand(Method[] methods, Command command, PotoCommand potoCommand) {
        this.methods = methods;
        this.command = command;
        this.potoCommand = potoCommand;
    }

    @Override
    public Command getCommand() {
        return command;
    }

    @Override
    public PotoCommand getInstance() {
        return potoCommand;
    }

    @Override
    public Method[] getMethods() {
        return methods;
    }
}
