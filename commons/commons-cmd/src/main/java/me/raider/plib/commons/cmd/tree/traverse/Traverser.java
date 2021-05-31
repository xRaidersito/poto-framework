package me.raider.plib.commons.cmd.tree.traverse;

import me.raider.plib.commons.cmd.tree.CommandNode;

public interface Traverser<T> {

    TraverseResult<CommandTreeTraverseResponse> traverse(CommandNode node, T toTraverse);

}
