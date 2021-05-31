package me.raider.plib.commons.cmd.tree.traverse.traversers;

import me.raider.plib.commons.cmd.*;
import me.raider.plib.commons.cmd.resolved.ResolvedArgument;
import me.raider.plib.commons.cmd.tree.CommandNode;
import me.raider.plib.commons.cmd.tree.traverse.*;

import java.util.ArrayList;
import java.util.List;

public class ArgumentsTraverser extends AbstractTraverser<List<Object>> {

    public ArgumentsTraverser(ArgumentRegistry argumentRegistry) {
        super(argumentRegistry);
    }

    @Override
    public TraverseResult<CommandTreeTraverseResponse> traverse(CommandNode node, List<Object> toTraverse) {
        List<ResolvedArgument> resolvedArguments = new ArrayList<>();
        int index = 0;
        for (Object arg : toTraverse) {
            for (CommandNode children : node.getChildren()) {
                CommandArgument<?> argument = children.getData();
                if (argument instanceof ArrayCommandArgument) {
                    return new DefaultTraverseResult(CommandTreeTraverseResponse
                            .of(CommandResult.SUCCESSFUL, resolvedArguments, children, TraverseResponseType.NEXT, index));
                }
                if (argumentRegistry.process(argument, arg, resolvedArguments)) {
                    node = children;
                    break;
                }
            }
            index++;
        }
        if (node.getCommand()!=null) {
            return new DefaultTraverseResult(CommandTreeTraverseResponse
                    .of(CommandResult.SUCCESSFUL, resolvedArguments, node, TraverseResponseType.FINISH));
        }
        return new DefaultTraverseResult(CommandTreeTraverseResponse
                .of(CommandResult.INVALID, resolvedArguments, node, TraverseResponseType.FINISH));
    }
}
