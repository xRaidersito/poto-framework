package me.raider.poto.lang;

@FunctionalInterface
public interface LangReplacementValueCreator<T> {

    String create(T t);

}
