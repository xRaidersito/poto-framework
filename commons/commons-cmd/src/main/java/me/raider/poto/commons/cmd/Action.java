package me.raider.poto.commons.cmd;

import me.raider.poto.commons.cmd.resolved.ResolvedArgument;

@FunctionalInterface
public interface Action {

    void start(ResolvedArgument... resolvedArguments);

}
