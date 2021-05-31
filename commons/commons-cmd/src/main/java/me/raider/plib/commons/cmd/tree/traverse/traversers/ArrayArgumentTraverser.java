package me.raider.plib.commons.cmd.tree.traverse.traversers;

import me.raider.plib.commons.cmd.ArgumentRegistry;
import me.raider.plib.commons.cmd.ArrayCommandArgument;
import me.raider.plib.commons.cmd.CommandResult;
import me.raider.plib.commons.cmd.resolved.ResolvedArgument;
import me.raider.plib.commons.cmd.tree.CommandNode;
import me.raider.plib.commons.cmd.tree.traverse.*;

import java.util.ArrayList;
import java.util.List;

public class ArrayArgumentTraverser extends AbstractTraverser<List<Object>> {

    public ArrayArgumentTraverser(ArgumentRegistry argumentRegistry) {
        super(argumentRegistry);
    }

    @Override
    public TraverseResult<CommandTreeTraverseResponse> traverse(CommandNode node, List<Object> toTraverse) {
        List<ResolvedArgument> resolvedArguments = new ArrayList<>();
        ArrayCommandArgument arrayCommandArgument = (ArrayCommandArgument) node.getData();
        if (argumentRegistry.process(arrayCommandArgument, toTraverse, resolvedArguments)) {
            return new DefaultTraverseResult(CommandTreeTraverseResponse
                    .of(CommandResult.SUCCESSFUL, resolvedArguments, node, TraverseResponseType.FINISH));
        }
        return new DefaultTraverseResult(CommandTreeTraverseResponse
                .of(CommandResult.INVALID, resolvedArguments, node, TraverseResponseType.FINISH));
    }
}
