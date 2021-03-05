package me.raider.poto.commons.cmd.annotated;

import me.raider.poto.commons.cmd.Command;
import me.raider.poto.commons.cmd.PotoCommand;

import java.lang.reflect.Method;
import java.util.List;

public interface CommandAnnotationProcessor {

    // Aca procesare una clase con comandos y luego la pasare a un tree

    List<Command> processAll(PotoCommand potoCommand);

    Command process(Method method);

}
