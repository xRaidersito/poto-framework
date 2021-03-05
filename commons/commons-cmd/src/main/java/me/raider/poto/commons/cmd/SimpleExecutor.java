package me.raider.poto.commons.cmd;

import me.raider.poto.commons.cmd.resolved.ResolvedArgument;
import me.raider.poto.commons.cmd.tree.CommandTree;

public class SimpleExecutor implements Executor {

    private final CommandTree commandTree;

    protected SimpleExecutor(CommandTree commandTree) {
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
