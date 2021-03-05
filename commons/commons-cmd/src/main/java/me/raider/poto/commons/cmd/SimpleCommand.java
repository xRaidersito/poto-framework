package me.raider.poto.commons.cmd;

import java.util.List;

public class SimpleCommand implements Command {

    @Override
    public List<CommandArgument<?>> getArguments() {
        return null;
    }

    @Override
    public Action getAction() {
        return null;
    }

    @Override
    public String getPermission() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getPrefix() {
        return null;
    }
}
