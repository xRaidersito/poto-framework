package me.raider.plib.commons.cmd.annotated;

import me.raider.plib.commons.cmd.Command;
import me.raider.plib.commons.cmd.PLibCommand;

import java.util.List;

public interface CommandAnnotationProcessor {

    List<Command> processAll(PLibCommand potoCommand);

}
