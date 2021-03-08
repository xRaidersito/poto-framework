package me.raider.poto.commons.cmd.tree;

import me.raider.poto.commons.cmd.Command;
import me.raider.poto.commons.cmd.CommandArgument;
import me.raider.poto.commons.cmd.LiteralCommandArgument;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CommandNodeImpl implements CommandNode {

    // When a Command is null in a node is because not good!!! (Si, lo entiendo a mi manera)
    private final Command command;
    private final CommandArgument<?> data;

    private final List<Node<CommandArgument<?>, Command>> children = new ArrayList<>();

    private final Node<CommandArgument<?>, Command> parent;

    public CommandNodeImpl(Command command, CommandArgument data, Node<CommandArgument<?>, Command> parent) {
        this.command = command;
        this.data = data;
        this.parent = parent;
    }

    @Override
    public Command getCommand() {
        return command;
    }

    @Override
    public CommandArgument<?> getData() {
        return data;
    }

    @Override
    public List<Node<CommandArgument<?>, Command>> getChildren() {
        return children;
    }

    @Override
    public Node<CommandArgument<?>, Command> addChild(Node<CommandArgument<?>, Command> child) {

        if (children.contains(child)) {
            return null;
        }
        children.add(child);
        return child;
    }

    @Override
    public Node<CommandArgument<?>, Command> getParent() {
        return parent;
    }

    @Override
    public Node<CommandArgument<?>, Command> findData(String arg, Class<?> clazz) {

        for (Node<CommandArgument<?>, Command> child : children) {

            CommandArgument<?> argument = child.getData();

            if (argument instanceof LiteralCommandArgument && arg!=null) {

                LiteralCommandArgument literal = (LiteralCommandArgument) argument;
                if (literal.getRequiredLiteral().equalsIgnoreCase(arg)) {
                    return child;
                }
            }

            if (argument.getRequiredClass().equals(clazz)) {
                return child;
            }
        }
        return null;
    }

    public String toString() {
        StringBuilder buffer = new StringBuilder(50);
        print(buffer, "", "");
        return buffer.toString();
    }

    public void print(StringBuilder buffer, String prefix, String childrenPrefix) {
        buffer.append(prefix);
        if (data!=null) {
            if (data instanceof LiteralCommandArgument) {
                LiteralCommandArgument literal = (LiteralCommandArgument) data;
                buffer.append(literal.getRequiredLiteral());
            } else {
                buffer.append(data.getRequiredClass());
        }
        } else {
            buffer.append("null");
        }
        buffer.append('\n');
        for (Iterator<Node<CommandArgument<?>, Command>> it = children.iterator(); it.hasNext();) {
            Node<CommandArgument<?>, Command> next = it.next();
            if (it.hasNext()) {
                next.print(buffer, childrenPrefix + "├── ", childrenPrefix + "│   ");
            } else {
                next.print(buffer, childrenPrefix + "└── ", childrenPrefix + "    ");
            }
        }
    }
}
