package me.raider.poto.commons.cmd;

public interface SubCommandBuilder {

    SubCommandBuilder injected(Class<?> clazz);

    SubCommandBuilder literal(String literal);

    SubCommandBuilder argument(Class<?> clazz);

}
