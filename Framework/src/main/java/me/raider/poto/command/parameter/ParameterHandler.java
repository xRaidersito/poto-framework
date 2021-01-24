package me.raider.poto.command.parameter;

import java.util.Map;

public interface ParameterHandler {

    /**
     * Gets the instance of the map that storage the registered parameters for commands.
     *
     * @return the {@link Map} of the {@link ParameterHandler}.
     */
    Map<Class<?>, ParameterCreator<Object>> get();

    /**
     * Register a new parameter for arguments.
     *
     * @param clazz The class the argument belongs to.
     * @param parameterCreator A implementation of {@link ParameterCreator}
     */
    void register(Class<?> clazz, ParameterCreator<Object> parameterCreator);

    /**
     * Returns a {@link ParameterCreator} based on the given class.
     *
     * @param clazz The key class.
     * @return A stored instance of {@link ParameterCreator}
     */
    ParameterCreator<Object> getParameter(Class<?> clazz);

}
