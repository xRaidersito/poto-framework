package me.raider.plib.commons.cmd;

import me.raider.plib.commons.Builder;

import java.util.List;

public interface CommandBuilder extends Builder<List<Command>> {

    <T> CommandBuilder injected(Class<T> clazz);

    CommandBuilder literal(String literal);

    <T> CommandBuilder argument(Class<T> clazz);

    CommandBuilder subcommand(CommandBuilder builder);

    CommandBuilder action(Action action);

    CommandBuilder permission(String permission);

    static CommandBuilder create(String name, String prefix,
                                 ArgumentProcessor<LiteralCommandArgument> argumentProcessor,
                                 CommandSupplierManager supplierManager) {

        return new CommandBuilderImpl(name, prefix, argumentProcessor, supplierManager);
    }

}
