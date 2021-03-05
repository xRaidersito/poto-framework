package me.raider.poto.commons.cmd.tree;

import me.raider.poto.commons.cmd.*;
import me.raider.poto.commons.cmd.resolved.ResolvedArgument;

import java.util.ArrayList;
import java.util.List;

public class CommandTree {

    private final Node<CommandArgument<?>, Command> root;

    public CommandTree() {
        this.root = new CommandNodeImpl(null, null, null);
    }

    public void addCommandToTree(Command command) {

        if (command==null) {
            return;
        }

        Node<CommandArgument<?>, Command> parent = root;

        int index = 0;

        for (CommandArgument<?> argument : command.getArguments()) {

            index++;

            if (argument instanceof LiteralCommandArgument) {

                LiteralCommandArgument literal = (LiteralCommandArgument) argument;
                Node<CommandArgument<?>, Command> find = parent.findData(literal.getRequiredLiteral(), literal.getClass());

                if (find!=null) {
                    parent = find;
                } else {
                    parent = parent.addChild((index==command.getArguments().size())
                            ? new CommandNodeImpl(command, argument, parent)
                            : new CommandNodeImpl(null, argument, parent));
                }
                continue;
            }

            Node<CommandArgument<?>, Command> find = parent.findData(null, argument.getClass());

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

        Node<CommandArgument<?>, Command> node = checkInjected(root, injected);

        if (node==null) {
            return new WrappedCommandResult(CommandResult.INJECTED_FAILURE, null, null);
        }

        List<ResolvedArgument> resolvedArguments = new ArrayList<>();

        for (String arg : args) {

            for (Node<CommandArgument<?>, Command> children : node.getChildren()) {

                if (children.getData() instanceof LiteralCommandArgument) {

                    LiteralCommandArgument literal = (LiteralCommandArgument) children.getData();

                    if (literal.getRequiredLiteral().equalsIgnoreCase(arg)) {
                        node=children;
                        break;
                    }
                    continue;
                }

                if (children.getData().resolveArgument(arg)!=null) {
                    resolvedArguments.add(ResolvedArgument.of(arg, children.getData()));
                    node=children;
                    break;
                }
            }
        }

        if (node.getCommand()!=null) {
            return new WrappedCommandResult(CommandResult.SUCCESSFUL, node.getCommand(), resolvedArguments);
        }

        return new WrappedCommandResult(CommandResult.INVALID, null, null);
    }



    private Node<CommandArgument<?>, Command> checkInjected(Node<CommandArgument<?>, Command> root, Object... objects) {

        Node<CommandArgument<?>, Command> actual = root;

        for (Object object : objects) {

            Node<CommandArgument<?>, Command> next = actual.findData(null, object.getClass());

            if (next!=null) {
                actual = next;
                continue;
            }
            return actual;
        }

        return null;
    }

}
