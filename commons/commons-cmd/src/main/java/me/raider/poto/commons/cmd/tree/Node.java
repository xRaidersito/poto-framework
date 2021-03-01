package me.raider.poto.commons.cmd.tree;

import java.util.List;

public interface Node<T> {

    T getData();

    List<Node<T>> getChildren();

    Node<T> getParent();

}
