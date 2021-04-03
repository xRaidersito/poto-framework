package me.raider.plib.commons.cmd;

import me.raider.plib.commons.cmd.resolved.ResolvedArgument;

import java.util.List;

public class WrappedCommandResult {

    private final CommandResult result;
    private final Command command;
    private final List<ResolvedArgument> resolvedArguments;

    public WrappedCommandResult(CommandResult result, Command command, List<ResolvedArgument> resolvedArguments) {
        this.result = result;
        this.command = command;
        this.resolvedArguments = resolvedArguments;
    }

    public CommandResult getResult() {
        return result;
    }

    public Command getCommand() {
        return command;
    }

    public List<ResolvedArgument> getResolvedArguments() {
        return resolvedArguments;
    }
}
