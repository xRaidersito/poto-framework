package me.raider.poto.command.message;

import java.util.Map;

public interface MessageProvider {

    Map<String, String> getMessageMap();

    void register(String id, String message);

    String getMessage(String id);

}
