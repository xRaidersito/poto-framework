package me.raider.plib.commons.cmd;

public interface LiteralCommandArgument extends CommandArgument<String> {

    String getRequiredLiteral();

}
