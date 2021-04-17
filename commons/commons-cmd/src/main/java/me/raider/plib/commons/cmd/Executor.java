package me.raider.plib.commons.cmd;

public interface Executor {

    void execute(String[] args, Object[] injected, Object[] authorized, Object[] message);

}
