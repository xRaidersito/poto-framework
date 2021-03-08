package me.raider.poto.commons.cmd;

import java.util.List;

public interface Command {

    List<CommandArgument<?>> getArguments();

    Action getAction();

    String getPermission();

    String getName();

    String getPrefix();

}
