package me.raider.plib.commons.cmd.tree.traverse;

import me.raider.plib.commons.cmd.tree.CommandNode;

import java.util.Map;
import java.util.Optional;

public interface TreeTraverser<T> {

    Map<String, Traverser<?>> getTraverser();

    void registerTraverser(String key, Traverser<?> traverser);

    CommandNode getRootNode();

    Optional<T> traverseAll(TraversionObjectsProvider objectsProvider);

}
