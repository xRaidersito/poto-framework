package me.raider.poto.commons.cmd.tree;

import java.util.List;

public interface Node<T, S> {

    S getCommand();

    T getData();

    List<Node<T, S>> getChildren();

    Node<T, S> addChild(Node<T, S> child);

    Node<T, S> getParent();

    Node<T, S> findData(String arg, Class<?> clazz);

    void print(StringBuilder buffer, String prefix, String childrenPrefix);
}
