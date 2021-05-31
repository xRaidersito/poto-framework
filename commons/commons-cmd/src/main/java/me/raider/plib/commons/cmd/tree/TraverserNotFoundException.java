package me.raider.plib.commons.cmd.tree;

public class TraverserNotFoundException extends RuntimeException {

    public TraverserNotFoundException() {
        super("Cant find a traverser registered in tree traverser");
    }

}
