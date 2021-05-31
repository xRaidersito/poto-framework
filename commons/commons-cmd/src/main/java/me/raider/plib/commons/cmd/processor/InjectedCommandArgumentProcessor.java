package me.raider.plib.commons.cmd.processor;

import me.raider.plib.commons.cmd.ArgumentProcessor;
import me.raider.plib.commons.cmd.InjectedCommandArgument;
import me.raider.plib.commons.cmd.resolved.ResolvedArgument;
import me.raider.plib.commons.cmd.resolved.SimpleResolvedArgument;

import java.util.List;

public class InjectedCommandArgumentProcessor implements ArgumentProcessor<InjectedCommandArgument<?>> {

    @Override
    public boolean process(InjectedCommandArgument<?> argument, Object value, List<ResolvedArgument> resolvedArguments) {
        Object resolved = argument.resolveArgument(value);
        if (resolved!=null) {
            resolvedArguments.add(new SimpleResolvedArgument(resolved, argument));
            return true;
        }
        return false;
    }

}
