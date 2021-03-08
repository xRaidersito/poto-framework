package me.raider.poto.commons.cmd.resolved;

import me.raider.poto.commons.cmd.CommandArgument;

public interface ResolvedArgument {

    // This class will resolve all CommandArgument and will store in a list
    Object getInstance();

    static ResolvedArgument of(Object object, CommandArgument<?> argument) {
        return new SimpleResolvedArgument(object, argument);
    }


}
