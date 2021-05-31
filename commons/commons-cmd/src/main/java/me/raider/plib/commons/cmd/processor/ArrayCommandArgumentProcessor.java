package me.raider.plib.commons.cmd.processor;

import me.raider.plib.commons.cmd.ArgumentProcessor;
import me.raider.plib.commons.cmd.ArrayCommandArgument;
import me.raider.plib.commons.cmd.resolved.ResolvedArgument;
import me.raider.plib.commons.cmd.resolved.SimpleResolvedArgument;

import java.util.List;

public class ArrayCommandArgumentProcessor implements ArgumentProcessor<ArrayCommandArgument> {

    @Override
    public boolean process(ArrayCommandArgument argument, Object value, List<ResolvedArgument> resolvedArguments) {
        String resolved = argument.resolveArgument(value);
        if (resolved!=null) {
            resolvedArguments.add(new SimpleResolvedArgument(resolved, argument));
            return true;
        }
        return false;
    }
}
