package me.raider.plib.commons.cmd;

public interface CommandExecutor {

    void execute(String[] args, Object[] injected, Object[] authorized, Object[] message);

}
