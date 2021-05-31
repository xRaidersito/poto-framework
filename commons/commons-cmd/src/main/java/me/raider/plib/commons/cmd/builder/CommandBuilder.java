package me.raider.plib.commons.cmd.builder;

import me.raider.commons.utils.Builder;
import me.raider.commons.utils.Nameable;
import me.raider.plib.commons.cmd.*;

import java.util.List;

public interface CommandBuilder extends Builder<List<Command>>, Nameable {

    <T> CommandBuilder injected(Class<T> clazz);

    CommandBuilder literal(String literal);

    <T> CommandBuilder argument(Class<T> clazz);

    CommandBuilder array();

    CommandBuilder subcommand(CommandBuilder builder);

    CommandBuilder action(Action action);

    CommandBuilder permission(String permission);

    String getPrefix();

    List<LiteralCommandArgument> getLiteral();

    String getPermission();

    ArgumentHelper<LiteralCommandArgument> getArgumentProcessor();

    CommandSupplierManager getSupplierManager();

    static CommandBuilder create(String name, String prefix, CommandSupplierManager supplierManager) {

        return new CommandBuilderImpl(name, prefix, new LiteralArgumentHelper(supplierManager), supplierManager);
    }

    static CommandBuilder create(CommandBuilder builder) {
        return new CommandBuilderImpl(builder);
    }

}
