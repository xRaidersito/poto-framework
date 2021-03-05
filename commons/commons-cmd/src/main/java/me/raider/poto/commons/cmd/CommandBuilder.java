package me.raider.poto.commons.cmd;

import me.raider.poto.commons.Builder;

import java.util.List;

public interface CommandBuilder extends Builder<List<Command>> {

    CommandBuilder injected(Class<?> clazz);

    CommandBuilder literal(String literal);

    CommandBuilder argument(Class<?> clazz);

    CommandBuilder subcommand(CommandBuilder builder);

    CommandBuilder action(Action action);

    CommandBuilder name(String name);

    CommandBuilder prefix(String prefix);

}
