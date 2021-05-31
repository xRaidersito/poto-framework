package me.raider.plib.commons.cmd.resolved;

import me.raider.plib.commons.cmd.CommandArgument;

public interface ResolvedArgument {

    Object getInstance();

    CommandArgument<?> getArgument();

}
