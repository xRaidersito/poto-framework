package me.raider.plib.commons.cmd.tree;

import me.raider.plib.commons.cmd.*;
import me.raider.plib.commons.cmd.tree.traverse.CommandTreeTraverser;

public class CommandTree {

    private final CommandNode root;
    private final CommandTreeView treeView;

    public CommandTree(ArgumentRegistry argumentRegistry) {
        this.root = new CommandNodeImpl(null, null, null);
        this.treeView = new CommandTreeView(new CommandTreeTraverser(root), argumentRegistry);
    }

    public void addCommandToTree(Command command) {
        if (command==null) {
            return;
        }
        CommandNode parent = root;
        int index = 0;

        for (CommandArgument<?> argument : command.getArguments()) {
            index++;

            if (argument instanceof LiteralCommandArgument) {
                LiteralCommandArgument literalCommandArgument = (LiteralCommandArgument) argument;
                CommandNode find = parent.findData(literalCommandArgument.getRequiredLiteral(),
                        argument.getRequiredClass());

                if (find!=null) {
                    parent = find;
                } else {
                    parent = parent.addChild((index==command.getArguments().size())
                            ? new CommandNodeImpl(command, argument, parent)
                            : new CommandNodeImpl(null, argument, parent));
                }
                continue;
            }
            CommandNode find = parent.findData(null, argument.getRequiredClass());

            if (find!=null) {
                parent = find;
            } else {
                parent = parent.addChild((index==command.getArguments().size())
                        ? new CommandNodeImpl(command, argument, parent)
                        : new CommandNodeImpl(null, argument, parent));
            }
        }
    }

    public WrappedCommandResult traverseTree(String[] args, Object... injected) {
        return treeView.traverseCommandTree(args, injected);
    }

    public CommandNode getRoot() {
        return root;
    }
}
