package me.raider.poto.commons.cmd;

import java.util.List;

public class SimpleCommand implements Command {

    private final List<CommandArgument<?>> arguments;

    private final Action action;

    private final String permission;
    private final String name;
    private final String prefix;

    public SimpleCommand(List<CommandArgument<?>> arguments, Action action, String permission, String name, String prefix) {
        this.arguments = arguments;
        this.action = action;
        this.permission = permission;
        this.name = name;
        this.prefix = prefix;
    }

    @Override
    public List<CommandArgument<?>> getArguments() {
        return arguments;
    }

    @Override
    public Action getAction() {
        return action;
    }

    @Override
    public String getPermission() {
        return permission;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPrefix() {
        return prefix;
    }
}
