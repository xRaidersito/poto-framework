package me.raider.poto.commons.cmd;

import me.raider.poto.commons.BuildableObject;

import java.util.List;

public interface Command extends BuildableObject {

    List<CommandArgument<?>> getArguments();

    Action getAction();

    String getPermission();

    String getName();

    String getPrefix();

}
