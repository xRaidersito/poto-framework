package me.raider.plib.commons.cmd;

import me.raider.plib.commons.cmd.resolved.ResolvedArgument;
import me.raider.plib.commons.cmd.tree.CommandTree;

import java.util.Arrays;

public class DefaultExecutor implements Executor {

    private final CommandTree commandTree;

    protected DefaultExecutor(CommandTree commandTree) {
        this.commandTree = commandTree;
    }

    @Override
    public void execute(String[] args, Object... injected) {

        WrappedCommandResult commandResult = commandTree.traverseTree(args, injected);

        switch (commandResult.getResult()) {
            case SUCCESSFUL:
                commandResult
                        .getCommand()
                        .getAction()
                        .start(commandResult.getResolvedArguments().toArray(new ResolvedArgument[0]));
                break;
            case INVALID:
                System.out.println("Invalid command");
                break;
            case INJECTED_FAILURE:
                System.out.println("Injection failure");
                break;

        }

    }




}
