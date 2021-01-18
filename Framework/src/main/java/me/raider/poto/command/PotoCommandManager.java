package me.raider.poto.command;

import java.util.Map;

public interface PotoCommandManager {

    Map<String, RegisteredCommand> getRegisteredCommands();

}
