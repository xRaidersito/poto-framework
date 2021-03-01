package me.raider.poto.commons.cmd.tree;

import me.raider.poto.commons.cmd.CommandArgument;

import java.util.ArrayList;
import java.util.List;

public class CommandNodeImpl implements CommandNode {

    private final CommandArgument data;
    private final List<Node<CommandArgument>> children = new ArrayList<>();

    private final Node<CommandArgument> parent;

    public CommandNodeImpl(CommandArgument data, Node<CommandArgument> parent) {
        this.data = data;
        this.parent = parent;
    }

    @Override
    public CommandArgument getData() {
        return data;
    }

    @Override
    public List<Node<CommandArgument>> getChildren() {
        return children;
    }

    @Override
    public Node<CommandArgument> getParent() {
        return parent;
    }
}
