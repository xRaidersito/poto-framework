package me.raider.plib.commons.cmd;

import me.raider.plib.commons.cmd.processor.ArrayCommandArgumentProcessor;
import me.raider.plib.commons.cmd.processor.CommandArgumentProcessor;
import me.raider.plib.commons.cmd.processor.InjectedCommandArgumentProcessor;
import me.raider.plib.commons.cmd.processor.LiteralCommandArgumentProcessor;

import java.util.HashMap;
import java.util.Map;

public class DefaultArgumentRegistry implements ArgumentRegistry {

    private final Map<Class<?>, ArgumentProcessor<CommandArgument<?>>> arguments = new HashMap<>();

    public DefaultArgumentRegistry() {
        registerRaw(SimpleLiteralCommandArgument.class, new LiteralCommandArgumentProcessor());
        registerRaw(SimpleArrayCommandArgument.class, new ArrayCommandArgumentProcessor());
        registerRaw(InjectedCommandArgument.class, new InjectedCommandArgumentProcessor());
        registerRaw(SimpleCommandArgument.class, new CommandArgumentProcessor());
    }

    @Override
    public Map<Class<?>, ArgumentProcessor<CommandArgument<?>>> getArguments() {
        return arguments;
    }
}