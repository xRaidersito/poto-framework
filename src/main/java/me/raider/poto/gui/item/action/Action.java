package me.raider.poto.gui.item.action;

import me.raider.poto.gui.Gui;

@FunctionalInterface
public interface Action<T> {

    void start(T t, Gui gui);

}
