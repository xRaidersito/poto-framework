package me.raider.plib.commons.cmd.builder;

import me.raider.plib.commons.cmd.*;

import java.util.ArrayList;
import java.util.List;

public class CommandBuilderImpl implements CommandBuilder {

    private final String name;
    private final String prefix;
    private String permission;

    private Action action;

    private final ArgumentHelper<LiteralCommandArgument> argumentProcessor;
    private final CommandSupplierManager supplierManager;

    private ArrayCommandArgument arrayCommandArgument;
    private List<InjectedCommandArgument<?>> injected = new ArrayList<>();
    private List<LiteralCommandArgument> literal = new ArrayList<>();
    private List<CommandArgument<?>> argument = new ArrayList<>();
    private List<CommandBuilder> builders = new ArrayList<>();

    protected CommandBuilderImpl(String name, String prefix, ArgumentHelper<LiteralCommandArgument> argumentProcessor,
                              CommandSupplierManager supplierManager) {
        this.name = name;
        this.prefix = prefix;
        this.argumentProcessor = argumentProcessor;
        this.supplierManager = supplierManager;
    }

    protected CommandBuilderImpl(CommandBuilder builder) {
        this.name = builder.getName();
        this.prefix = builder.getPrefix();
        this.argumentProcessor = builder.getArgumentProcessor();
        this.supplierManager = builder.getSupplierManager();
        this.injected = new ArrayList<>();
        this.literal = new ArrayList<>(builder.getLiteral());
        this.argument = new ArrayList<>();
        this.permission = builder.getPermission();
        this.builders = new ArrayList<>();
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
    public CommandBuilder array() {
        SupplierKey<String> supplierKey = new CommandSupplierKey<>(String.class, ArrayCommandArgument.class);
        this.arrayCommandArgument = new SimpleArrayCommandArgument(supplierManager.getSupplier(supplierKey));
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
    public String getPrefix() {
        return prefix;
    }

    @Override
    public List<LiteralCommandArgument> getLiteral() {
        return literal;
    }

    @Override
    public String getPermission() {
        return permission;
    }

    @Override
    public ArgumentHelper<LiteralCommandArgument> getArgumentProcessor() {
        return argumentProcessor;
    }

    @Override
    public CommandSupplierManager getSupplierManager() {
        return supplierManager;
    }

    @Override
    public List<Command> build() {

        List<CommandArgument<?>> arguments = new ArrayList<>(injected);

        if(prefix != null && !prefix.trim().isEmpty())  {
            arguments.add(new SimpleLiteralCommandArgument(supplierManager.getSupplier(String.class), prefix));
        }
        if(name != null && !name.trim().isEmpty())  {
            arguments.add(new SimpleLiteralCommandArgument(supplierManager.getSupplier(String.class), name));
        }
        arguments.addAll(literal);
        arguments.addAll(argument);
        if (arrayCommandArgument!=null) arguments.add(arrayCommandArgument);

        List<Command> commands = new ArrayList<>();
        commands.add(new SimpleCommand(arguments, action, permission, name, prefix));

        for (CommandBuilder commandBuilder : builders) {
            commands.addAll(commandBuilder.build());
        }
        return commands;
    }

    @Override
    public String getName() {
        return name;
    }
}
