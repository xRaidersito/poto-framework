package me.raider.poto.command;

import java.util.Map;

public interface PotoCommandManager {

    /**
     * Gets the instance of the map that storage the commands.
     *
     * @return the {@link Map} of the {@link PotoCommandManager}.
     */
    Map<String, RegisteredCommand> getRegisteredCommands();

}
