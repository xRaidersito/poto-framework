package me.raider.plib.commons.cmd;

import me.raider.plib.commons.cmd.resolved.ResolvedArgument;

import java.util.List;

public class WrappedCommandResult {

    private CommandResult result;
    private Command command;
    private final List<ResolvedArgument> resolvedArguments;

    public WrappedCommandResult(List<ResolvedArgument> resolvedArguments) {
        this.resolvedArguments = resolvedArguments;
    }

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

    public void setResult(CommandResult result) {
        this.result = result;
    }

    public void setCommand(Command command) {
        this.command = command;
    }
}
