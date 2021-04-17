package me.raider.plib.commons.cmd.tree;

import me.raider.plib.commons.cmd.*;
import me.raider.plib.commons.cmd.resolved.ResolvedArgument;

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

            if (argument.hasRequiredLiteral()) {
                Node<CommandArgument<?>, Command> find = parent.findData(argument.getRequiredLiteral(),
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
            Node<CommandArgument<?>, Command> find = parent.findData(null, argument.getRequiredClass());

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

        List<ResolvedArgument> resolvedArguments = new ArrayList<>(result.resolvedArguments);

        for (String arg : args) {
            for (Node<CommandArgument<?>, Command> children : node.getChildren()) {

                CommandArgument<?> argument = children.getData();

                if (argument.hasRequiredLiteral()) {
                    if (argument.getRequiredLiteral().equalsIgnoreCase(arg)) {
                        node=children;
                        break;
                    }
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
                if (children != null) {
                    Class<?> requiredClass = children.getData().getRequiredClass();
                    Object newObj = object;
                    if (requiredClass.isAssignableFrom(object.getClass())) {
                        newObj = cast(requiredClass, object);
                    }
                    if (children.getData() instanceof InjectedCommandArgument<?>
                            && children.getData().getRequiredClass().isAssignableFrom(newObj.getClass())) {
                        actual = children;
                        resolvedArguments.add(ResolvedArgument.of(newObj, children.getData()));
                    }
                }
            }
        }
        return new InjectionResult(actual, resolvedArguments);
    }

    private <T> T cast(Class<T> clazz, Object value) {
        return clazz.cast(value);
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
