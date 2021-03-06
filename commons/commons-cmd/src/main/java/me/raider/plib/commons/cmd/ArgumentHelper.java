package me.raider.plib.commons.cmd;

import java.util.List;

public interface ArgumentHelper<T extends CommandArgument<?>> {

    List<T> toArguments(String[] args);

    default List<T> toArguments(List<String> list) {
        return toArguments(list.toArray(new String[0]));
    }

    default List<T> toArguments(String string, String separator) {
        return toArguments(string.split(separator));
    }

}
