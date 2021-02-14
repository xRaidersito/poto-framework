package me.raider.poto.command.parameter;

import me.raider.poto.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class ParameterHandlerImpl implements ParameterHandler {

    private final Map<Class<?>, ParameterCreator<Object>> parameterMap = new HashMap<>();

    public ParameterHandlerImpl() {
        
        register(String.class, arg -> arg);
        register(boolean.class, arg -> Boolean.valueOf(arg));

        register(int.class, arg -> {
            if (Utils.isNumeric(arg)) {
                return Integer.valueOf(arg);
            }
            return null;
        });

        register(double.class, arg -> {
            if (Utils.isNumeric(arg)) {
                return Double.valueOf(arg);
            }
            return null;
        });

        register(long.class, arg -> {
            if (Utils.isNumeric(arg)) {
                return Long.valueOf(arg);
            }
            return null;
        });

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
        return null;
    }
}
