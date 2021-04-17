package me.raider.plib.commons.cmd.message;

import java.util.HashMap;
import java.util.Map;

public class DefaultMessageProvider implements MessageProvider{

    private final Map<String, String> messageMap = new HashMap<>();

    public DefaultMessageProvider() {
        register("usage", "Bad usage!");
        register("no-permission", "You don't have permissions!");
        register("invalid-argument", "Invalid argument!");
        register("invalid-injection", "Invalid injection parameter!");
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
