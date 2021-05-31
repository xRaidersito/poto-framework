package me.raider.plib.commons.cmd;

import me.raider.plib.commons.cmd.resolved.ResolvedArgument;

import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
public interface ArgumentRegistry {

    Map<Class<?>, ArgumentProcessor<CommandArgument<?>>> getArguments();

    default boolean isRegistered(Class<?> clazz) {
        return getArguments().containsKey(clazz);
    }

    default <T extends CommandArgument<?>> void register(Class<T> clazz, ArgumentProcessor<T> processor) {
        getArguments().put(clazz, (ArgumentProcessor<CommandArgument<?>>) processor);
    }

    default void registerRaw(Class<?> clazz, ArgumentProcessor<?> processor) {
        getArguments().put(clazz, (ArgumentProcessor<CommandArgument<?>>) processor);
    }

    default <T extends CommandArgument<?>> void unregister(Class<T> clazz) {
        getArguments().remove(clazz);
    }

    default <T extends CommandArgument<?>> boolean process(T argument, Object value,
                                                           List<ResolvedArgument> resolvedArguments) {
        if (argument==null) {
            return false;
        }
        if (!isRegistered(argument.getClass())) {
            throw new ArgumentNotRegisteredException(argument.getClass());
        }
        return getArguments().get(argument.getClass()).process(argument, value, resolvedArguments);
    }

}
