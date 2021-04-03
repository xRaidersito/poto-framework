package me.raider.plib.commons.cmd.resolved;

import me.raider.plib.commons.cmd.CommandArgument;

public class SimpleResolvedArgument implements ResolvedArgument {

    private final Object object;
    private final CommandArgument<?> argument;

    public SimpleResolvedArgument(Object object, CommandArgument<?> argument) {
        this.object = object;
        this.argument = argument;
    }

    @Override
    public Object getInstance() {
        return argument.resolveArgument(object);
    }
}
