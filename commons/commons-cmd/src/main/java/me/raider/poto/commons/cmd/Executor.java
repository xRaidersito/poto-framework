package me.raider.poto.commons.cmd;

public interface Executor {

    void execute(String[] args, Object... injected);

}
