package me.raider.plib.commons.cmd.processor;

import me.raider.plib.commons.cmd.ArgumentProcessor;
import me.raider.plib.commons.cmd.LiteralCommandArgument;
import me.raider.plib.commons.cmd.resolved.ResolvedArgument;

import java.util.List;

public class LiteralCommandArgumentProcessor implements ArgumentProcessor<LiteralCommandArgument> {

    @Override
    public boolean process(LiteralCommandArgument argument, Object value, List<ResolvedArgument> resolvedArguments) {
        return argument.getRequiredLiteral().equalsIgnoreCase(value.toString());
    }
}
