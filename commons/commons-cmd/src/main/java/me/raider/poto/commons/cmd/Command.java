package me.raider.poto.commons.cmd;

import java.util.List;

public interface Command {

    Executor<Command> getExecutor();

    List<CommandArgument> getContext();






}
