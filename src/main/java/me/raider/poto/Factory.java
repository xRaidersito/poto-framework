package me.raider.poto;

public interface Factory<T extends Nameable> {

    T create(String name);

}
