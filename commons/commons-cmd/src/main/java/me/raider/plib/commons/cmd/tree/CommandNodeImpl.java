package me.raider.plib.commons.cmd.tree;

import me.raider.plib.commons.cmd.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CommandNodeImpl implements CommandNode {

    private final Command command;
    private final CommandArgument<?> data;

    private final List<CommandNode> children = new ArrayList<>();

    private final CommandNode parent;

    public CommandNodeImpl(Command command, CommandArgument<?> data, CommandNode parent) {
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
    public List<CommandNode> getChildren() {
        return children;
    }

    @Override
    public CommandNode addChild(CommandNode child) {
        if (children.contains(child)) {
            return null;
        }
        children.add(child);
        return child;
    }

    @Override
    public CommandNode getParent() {
        return parent;
    }

    @Override
    public CommandNode findData(String arg, Class<?> clazz) {
        for (CommandNode child : children) {
            CommandArgument<?> argument = child.getData();
            if (argument instanceof LiteralCommandArgument && arg!=null) {

                LiteralCommandArgument literal = (LiteralCommandArgument) argument;

                if (literal.getRequiredLiteral().equalsIgnoreCase(arg)) {
                    return child;
                }
            }
            else if (argument.getRequiredClass().equals(clazz) && arg==null) {
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
            if (data instanceof SimpleLiteralCommandArgument) {
                SimpleLiteralCommandArgument literal = (SimpleLiteralCommandArgument) data;
                buffer.append(literal.getRequiredLiteral());
            } else {
                buffer.append(data.getRequiredClass());
        }
        } else {
            buffer.append("null");
        }
        buffer.append('\n');
        for (Iterator<CommandNode> it = children.iterator(); it.hasNext();) {
            CommandNode next = it.next();
            if (it.hasNext()) {
                next.print(buffer, childrenPrefix + "├── ", childrenPrefix + "│   ");
            } else {
                next.print(buffer, childrenPrefix + "└── ", childrenPrefix + "    ");
            }
        }
    }
}
