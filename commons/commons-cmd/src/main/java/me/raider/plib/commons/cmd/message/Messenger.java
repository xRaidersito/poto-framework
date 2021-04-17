package me.raider.plib.commons.cmd.message;

import me.raider.plib.commons.cmd.CommandOption;

public interface Messenger<T> extends CommandOption {

    void sendMessage(String message, T holder);

}
