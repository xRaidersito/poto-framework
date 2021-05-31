package me.raider.plib.commons.cmd.tree;

import me.raider.plib.commons.cmd.Command;
import me.raider.plib.commons.cmd.CommandArgument;

import java.util.List;

public interface CommandNode {

    Command getCommand();

    CommandArgument<?> getData();

    List<CommandNode> getChildren();

    CommandNode addChild(CommandNode child);

    CommandNode getParent();

    CommandNode findData(String arg, Class<?> clazz);

    void print(StringBuilder buffer, String prefix, String childrenPrefix);

}
