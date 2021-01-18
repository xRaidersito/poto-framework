package me.raider.poto.command;

import me.raider.poto.command.annotation.Command;

import java.lang.reflect.Method;

public interface RegisteredCommand {

    Method[] getMethods();

    Command getCommand();

    PotoCommand getInstance();
}
