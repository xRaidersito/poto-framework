package me.raider.plib.commons.cmd.tree.traverse;

import me.raider.plib.commons.cmd.ArgumentRegistry;

public abstract class AbstractTraverser<T> implements Traverser<T> {

    protected final ArgumentRegistry argumentRegistry;

    public AbstractTraverser(ArgumentRegistry argumentRegistry) {
        this.argumentRegistry = argumentRegistry;
    }

}
