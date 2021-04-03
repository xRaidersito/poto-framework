package me.raider.plib.commons.cmd;

import me.raider.plib.commons.cmd.resolved.ResolvedArgument;

@FunctionalInterface
public interface Action {

    void start(ResolvedArgument... resolvedArguments);

}
