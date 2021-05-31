package me.raider.plib.commons.cmd.tree.traverse;

import me.raider.plib.commons.cmd.CommandResult;
import me.raider.plib.commons.cmd.WrappedCommandResult;
import me.raider.plib.commons.cmd.resolved.ResolvedArgument;
import me.raider.plib.commons.cmd.tree.CommandNode;

import java.util.List;

public class CommandTreeTraverseResponse {

    private final WrappedCommandResult commandResult;
    private final CommandNode node;
    private final TraverseResponseType responseType;
    private final int index;

    private CommandTreeTraverseResponse(WrappedCommandResult commandResult, CommandNode node,
                                        TraverseResponseType responseType, int index) {
        this.commandResult = commandResult;
        this.node = node;
        this.responseType = responseType;
        this.index = index;
    }

    private CommandTreeTraverseResponse(WrappedCommandResult commandResult, CommandNode node,
                                        TraverseResponseType responseType) {
        this.commandResult = commandResult;
        this.node = node;
        this.responseType = responseType;
        this.index = 0;
    }

    public WrappedCommandResult getCommandResult() {
        return commandResult;
    }

    public CommandNode getNode() {
        return node;
    }

    public int getIndex() {
        return index;
    }

    public TraverseResponseType getResponseType() {
        return responseType;
    }

    public static CommandTreeTraverseResponse of(CommandResult result, List<ResolvedArgument> resolvedArguments,
                                                 CommandNode node, TraverseResponseType responseType) {
        return new CommandTreeTraverseResponse(new WrappedCommandResult(result, node.getCommand(), resolvedArguments),
                node, responseType);
    }

    public static CommandTreeTraverseResponse of(CommandResult result, List<ResolvedArgument> resolvedArguments,
                                                 CommandNode node, TraverseResponseType responseType, int index) {
        return new CommandTreeTraverseResponse(new WrappedCommandResult(result, node.getCommand(), resolvedArguments),
                node, responseType, index);
    }

}
