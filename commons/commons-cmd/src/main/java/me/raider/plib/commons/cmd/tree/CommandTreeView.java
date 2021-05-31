package me.raider.plib.commons.cmd.tree;

import me.raider.plib.commons.cmd.ArgumentRegistry;
import me.raider.plib.commons.cmd.WrappedCommandResult;
import me.raider.plib.commons.cmd.tree.traverse.TraversionObjectsProvider;
import me.raider.plib.commons.cmd.tree.traverse.TreeTraverser;
import me.raider.plib.commons.cmd.tree.traverse.traversers.ArgumentsTraverser;
import me.raider.plib.commons.cmd.tree.traverse.traversers.ArrayArgumentTraverser;

import java.util.*;

public class CommandTreeView {

    private final TreeTraverser<WrappedCommandResult> treeTraverser;
    private final ArgumentRegistry argumentRegistry;

    public CommandTreeView(TreeTraverser<WrappedCommandResult> treeTraverser, ArgumentRegistry argumentRegistry) {
        this.treeTraverser = treeTraverser;
        this.argumentRegistry = argumentRegistry;
        installDefaultTraversers();
    }

    private void installDefaultTraversers() {
        treeTraverser.registerTraverser("arguments", new ArgumentsTraverser(argumentRegistry));
        treeTraverser.registerTraverser("array", new ArrayArgumentTraverser(argumentRegistry));
    }

    public WrappedCommandResult traverseCommandTree(String[] args, Object... injected) {
        TraversionObjectsProvider objects = new TraversionObjectsProvider();
        List<Object> arguments = new LinkedList<>();
        arguments.addAll(Arrays.asList(injected));
        arguments.addAll(Arrays.asList(args));
        objects.addProvider("arguments", arguments);
        Optional<WrappedCommandResult> commandResult = treeTraverser.traverseAll(objects);
        if (!commandResult.isPresent()) {
            throw new TraverserNotFoundException();
        }
        return commandResult.get();
    }
}
