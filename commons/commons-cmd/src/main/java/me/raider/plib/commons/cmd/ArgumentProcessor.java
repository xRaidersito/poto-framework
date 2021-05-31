package me.raider.plib.commons.cmd;

import me.raider.plib.commons.cmd.resolved.ResolvedArgument;

import java.util.List;

public interface ArgumentProcessor<A extends CommandArgument<?>> {

    boolean process(A argument, Object value, List<ResolvedArgument> resolvedArguments);

}
