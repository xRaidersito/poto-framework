package me.raider.poto.command;

import java.util.HashMap;
import java.util.Map;

public class SimplePotoCommandManager implements PotoCommandManager {

    private Map<String, RegisteredCommand> registeredCommands = new HashMap<>();

    @Override
    public Map<String, RegisteredCommand> getRegisteredCommands() {
        return registeredCommands;
    }
}
