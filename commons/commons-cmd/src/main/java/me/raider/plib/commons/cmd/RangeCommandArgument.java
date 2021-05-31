package me.raider.plib.commons.cmd;

public interface RangeCommandArgument<T extends Number> extends CommandArgument<T> {

    boolean range(T number);

}
