package me.raider.plib.commons.cmd.processor;

import me.raider.plib.commons.cmd.ArgumentProcessor;
import me.raider.plib.commons.cmd.SimpleCommandArgument;
import me.raider.plib.commons.cmd.resolved.ResolvedArgument;
import me.raider.plib.commons.cmd.resolved.SimpleResolvedArgument;

import java.util.List;

public class CommandArgumentProcessor implements ArgumentProcessor<SimpleCommandArgument<?>> {

    @Override
    public boolean process(SimpleCommandArgument<?> argument, Object value, List<ResolvedArgument> resolvedArguments) {
        Object resolved = argument.resolveArgument(value);
        if (resolved!=null) {
            resolvedArguments.add(new SimpleResolvedArgument(resolved, argument));
            return true;
        }
        return false;
    }
}
