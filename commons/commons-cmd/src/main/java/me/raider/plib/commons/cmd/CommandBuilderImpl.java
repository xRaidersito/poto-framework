package me.raider.plib.commons.cmd;

import java.util.ArrayList;
import java.util.List;

public class CommandBuilderImpl implements CommandBuilder {

    private final List<Command> commands = new ArrayList<>();

    private final String name;
    private final String prefix;
    private String permission;

    private Action action;

    private final ArgumentProcessor<LiteralCommandArgument> argumentProcessor;
    private final CommandSupplierManager supplierManager;

    private List<InjectedCommandArgument<?>> injected = new ArrayList<>();
    private List<LiteralCommandArgument> literal = new ArrayList<>();
    private List<CommandArgument<?>> argument = new ArrayList<>();
    private List<CommandBuilder> builders = new ArrayList<>();

    public CommandBuilderImpl(String name, String prefix, ArgumentProcessor<LiteralCommandArgument> argumentProcessor,
                              CommandSupplierManager supplierManager) {
        this.name = name;
        this.prefix = prefix;
        this.argumentProcessor = argumentProcessor;
        this.supplierManager = supplierManager;
    }

    public CommandBuilderImpl(CommandBuilderImpl builder) {
        this.name = builder.name;
        this.prefix = builder.prefix;
        this.argumentProcessor = builder.argumentProcessor;
        this.supplierManager = builder.supplierManager;
        this.injected = new ArrayList<>();
        this.literal = builder.literal;
        this.argument = new ArrayList<>();
    }

    @Override
    public <T> CommandBuilder injected(Class<T> clazz) {
        this.injected.add(new InjectedCommandArgument<>(clazz, supplierManager.getSupplier(clazz)));
        return this;
    }

    @Override
    public CommandBuilder literal(String literal) {
        this.literal.addAll(argumentProcessor.toArguments(literal, " "));
        return this;
    }

    @Override
    public <T> CommandBuilder argument(Class<T> clazz) {
        this.argument.add(new SimpleCommandArgument<>(clazz, supplierManager.getSupplier(clazz)));
        return this;
    }

    @Override
    public CommandBuilder subcommand(CommandBuilder builder) {
        this.builders.add(builder);
        return this;
    }

    @Override
    public CommandBuilder action(Action action) {
        this.action = action;
        return this;
    }

    @Override
    public CommandBuilder permission(String permission) {
        this.permission = permission;
        return this;
    }

    @Override
    public List<Command> build() {

        List<CommandArgument<?>> arguments = new ArrayList<>();

        arguments.addAll(injected);
        arguments.add(new LiteralCommandArgument(supplierManager.getSupplier(String.class), prefix));
        arguments.add(new LiteralCommandArgument(supplierManager.getSupplier(String.class), name));
        arguments.addAll(literal);
        arguments.addAll(argument);

        commands.add(new SimpleCommand(arguments, action, permission, name, prefix));

        for (CommandBuilder commandBuilder : builders) {
            commands.addAll(commandBuilder.build());
        }

        return commands;
    }
}
