package me.raider.plib.commons.cmd.message;

import java.util.Map;

public interface MessageProvider {

    /**
     * Gets the instance of the map that storage the messages for commands.
     *
     * @return the {@link Map} of the {@link MessageProvider}.
     */
    Map<String, String> getMessageMap();

    /**
     * Register a new message.
     *
     * @param id The id the message belongs to.
     * @param message A message for the given id.
     */
    void register(String id, String message);

    /**
     * Returns a message based on the given id.
     *
     * @param id The key id.
     * @return A stored message based on the given key.
     */
    String getMessage(String id);

}
