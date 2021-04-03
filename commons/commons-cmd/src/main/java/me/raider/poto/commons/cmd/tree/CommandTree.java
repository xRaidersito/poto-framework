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

        InjectionResult result = checkInjected(root, injected);
        Node<CommandArgument<?>, Command> node = result.linkedNode;

        if (node==null) {
            return new WrappedCommandResult(CommandResult.INJECTED_FAILURE, null, null);
        }

        List<ResolvedArgument> resolvedArguments = new ArrayList<>();
        resolvedArguments.addAll(result.resolvedArguments);

        for (String arg : args) {
            for (Node<CommandArgument<?>, Command> children : node.getChildren()) {

                CommandArgument<?> argument = children.getData();

                if (argument instanceof LiteralCommandArgument) {

                    LiteralCommandArgument literal = (LiteralCommandArgument) argument;

                    if (literal.getRequiredLiteral().equalsIgnoreCase(arg)) {
                        node=children;
                    }
                    continue;
                }
                if (argument instanceof InjectedCommandArgument<?>) {
                    continue;
                }
                if (argument.resolveArgument(arg)!=null) {
                    resolvedArguments.add(ResolvedArgument.of(arg, argument));
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



    private InjectionResult checkInjected(Node<CommandArgument<?>, Command> root, Object... objects) {

        Node<CommandArgument<?>, Command> actual = root;
        List<ResolvedArgument> resolvedArguments = new ArrayList<>();

        if (objects==null) {
            return new InjectionResult(actual, resolvedArguments);
        }
        for (Object object : objects) {

            for (Node<CommandArgument<?>, Command> children : actual.getChildren()) {

                if (children != null && children.getData() instanceof InjectedCommandArgument<?>
                        && children.getData().getRequiredClass().equals(object.getClass())) {
                    actual = children;
                    resolvedArguments.add(ResolvedArgument.of(object, children.getData()));
                }
            }
        }
        return new InjectionResult(actual, resolvedArguments);
    }

    public Node<CommandArgument<?>, Command> getRoot() {
        return root;
    }

    private static class InjectionResult {

        private final Node<CommandArgument<?>, Command> linkedNode;
        private final List<ResolvedArgument> resolvedArguments;

        private InjectionResult(Node<CommandArgument<?>, Command> linkedNode, List<ResolvedArgument> resolvedArguments) {
            this.linkedNode = linkedNode;
            this.resolvedArguments = resolvedArguments;
        }
    }

}
