package me.raider.poto.command.parameter;

import me.raider.poto.command.exception.CommandException;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class ParameterHandlerImpl implements ParameterHandler {

    private final Map<Class<?>, ParameterCreator<Object>> parameterMap = new HashMap<>();

    public ParameterHandlerImpl() {
        
        register(String.class, arg -> arg);
        register(Boolean.class, Boolean::valueOf);
        register(Integer.class, Integer::valueOf);
        register(Double.class, Double::valueOf);
        register(Long.class, Long::valueOf);
        register(Player.class, Bukkit::getPlayer);

    }
    
    @Override
    public Map<Class<?>, ParameterCreator<Object>> get() {
        return parameterMap;
    }

    @Override
    public void register(Class<?> clazz, ParameterCreator<Object> parameterCreator) {
        parameterMap.put(clazz, parameterCreator);
    }

    @Override
    public ParameterCreator<Object> getParameter(Class<?> clazz) {
        
        for (Class<?> key : parameterMap.keySet()) {
            if (key.equals(clazz)) {
                return parameterMap.get(key);
            }
        }
        throw new CommandException("The parameter is not registered");
    }
}
