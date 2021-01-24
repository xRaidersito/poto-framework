package me.raider.poto.command.message;

import java.util.HashMap;
import java.util.Map;

public class DefaultMessageProvider implements MessageProvider {

    private final Map<String, String> messageMap = new HashMap<>();

    public DefaultMessageProvider() {
        register("only-players", "&cOnly players can use this command");
        register("only-console", "&cOnly console can use this command");
        register("usage", "&cBad usage!");
        register("bad-subcommand", "&cThat's not a valid subcommand!");
        register("no-permission", "&cYou don't have permissions!");
        register("invalid-argument", "&cInvalid argument!");
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
