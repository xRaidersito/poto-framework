package me.raider.poto.commons.cmd;

import java.util.HashMap;
import java.util.Map;

public class SimpleCommandArgumentManager implements CommandArgumentManager {

    private final Map<Class<?>, CommandArgument<?>> commandArgumentMap = new HashMap<>();

    @Override
    public <T> CommandArgument<T> getArgument(Class<T> tClass) {

        return null;
    }

    @Override
    public <T> void registerSupplier(Class<T> tClass, ArgumentSupplier<T> supplier) {

    }
}
