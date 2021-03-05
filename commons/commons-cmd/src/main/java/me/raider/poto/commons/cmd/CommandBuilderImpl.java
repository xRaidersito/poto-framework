package me.raider.poto.commons.cmd;

import java.util.List;

public class CommandBuilderImpl implements CommandBuilder {




    @Override
    public CommandBuilder injected(Class<?> clazz) {
        return null;
    }

    @Override
    public CommandBuilder literal(String literal) {
        return null;
    }

    @Override
    public CommandBuilder argument(Class<?> clazz) {
        return null;
    }

    @Override
    public CommandBuilder subcommand(CommandBuilder builder) {
        return null;
    }

    @Override
    public CommandBuilder action(Action action) {
        return null;
    }

    @Override
    public CommandBuilder name(String name) {
        return null;
    }

    @Override
    public CommandBuilder prefix(String prefix) {
        return null;
    }

    @Override
    public List<Command> build() {
        return null;
    }
}
