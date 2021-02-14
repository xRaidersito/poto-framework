package me.raider.poto.command.message;

import java.util.HashMap;
import java.util.Map;

public class DefaultMessageProvider implements MessageProvider {

    private final Map<String, String> messageMap = new HashMap<>();

    public DefaultMessageProvider() {
        register("only-players", "Only players can use this command");
        register("only-console", "Only console can use this command");
        register("usage", "Bad usage!");
        register("bad-subcommand", "That's not a valid subcommand!");
        register("no-permission", "You don't have permissions!");
        register("invalid-argument", "Invalid argument!");
        register("not-registered-argument", "Not registered argument");
    }

    @Override
    public Map<String, String> getMessageMap() {
        return messageMap;
    }

    @Override
    public void register(String id, String message) {
        messageMap.put(id, message);
    }

    @Override
    public String getMessage(String id) {
        return messageMap.get(id);
    }


}
