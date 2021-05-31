package me.raider.plib.commons.cmd.annotated;

import me.raider.plib.commons.cmd.*;
import me.raider.plib.commons.cmd.annotated.annotation.Default;
import me.raider.plib.commons.cmd.annotated.annotation.Injected;
import me.raider.plib.commons.cmd.annotated.annotation.Text;
import me.raider.plib.commons.cmd.builder.CommandBuilder;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandAnnotationProcessorImpl implements CommandAnnotationProcessor {

    private final CommandSupplierManager supplierManager;

    public CommandAnnotationProcessorImpl(CommandSupplierManager supplierManager) {
        this.supplierManager = supplierManager;
    }

    @Override
    public List<Command> processAll(PLibCommand command) {

        List<CommandBuilder> processedCommands = process(command);
        List<Command> commands = new ArrayList<>();

        for (CommandBuilder builder : processedCommands) {
            commands.addAll(builder.build());
        }
        return commands;
    }

    private List<CommandBuilder> process(PLibCommand command) {

        List<CommandBuilder> builders = new ArrayList<>();
        CommandBuilder builder = null;

        if (command.getClass().isAnnotationPresent(
                me.raider.plib.commons.cmd.annotated.annotation.Command.class)) {

            me.raider.plib.commons.cmd.annotated.annotation.Command commandAnnotation
                    = command.getClass().getAnnotation(me.raider.plib.commons.cmd.annotated.annotation.Command.class);

            builder = CommandBuilder.create(
                    commandAnnotation.name(),
                    commandAnnotation.prefix(),
                    supplierManager);

            builder.permission(commandAnnotation.permission());
        }
        for (Method method : command.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(me.raider.plib.commons.cmd.annotated.annotation.Command.class)) {
                me.raider.plib.commons.cmd.annotated.annotation.Command methodAnnotation
                        = method.getAnnotation(me.raider.plib.commons.cmd.annotated.annotation.Command.class);

                if (builder == null) {

                    CommandBuilder methodCommand = CommandBuilder.create(
                            methodAnnotation.name(),
                            methodAnnotation.prefix(),
                            supplierManager);

                    methodCommand.permission(methodAnnotation.permission());
                    applyAction(methodCommand, method, command);
                    applyArguments(methodCommand, method);
                    builders.add(methodCommand);
                    continue;
                }
                CommandBuilder subCommand = CommandBuilder.create(builder);
                if (!methodAnnotation.permission().isEmpty()) {
                    subCommand.permission(methodAnnotation.permission());
                }
                subCommand.literal(methodAnnotation.name());
                applyAction(subCommand, method, command);
                applyArguments(subCommand, method);
                builder.subcommand(subCommand);
                continue;
            }
            if (method.isAnnotationPresent(Default.class)) {
                if (builder==null) {
                    throw new CommandException("If default annotation, class needs annotation @Command");
                }
                Default defaultAnnotation = method.getAnnotation(Default.class);
                if (!defaultAnnotation.permission().isEmpty()) {
                    builder.permission(defaultAnnotation.permission());
                }
                applyAction(builder, method, command);
                applyArguments(builder, method);
            }
        }
        builders.add(builder);
        return builders;
    }

    private void applyAction(CommandBuilder builder, Method method, PLibCommand command) {
        builder.action(arguments -> {
            List<Object> resolved = new ArrayList<>();
            Arrays.stream(arguments).forEach(argument -> resolved.add(argument.getInstance()));
            try {
                method.invoke(command, resolved.toArray());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void applyArguments(CommandBuilder builder, Method method) {
        for (int i = 0 ; i < method.getParameterCount() ; i++) {
            Class<?> type = method.getParameterTypes()[i];
            Annotation[] annotations = method.getParameterAnnotations()[i];
            boolean find = false;
            for (Annotation annotation : annotations) {
                if (annotation instanceof Injected) {
                    builder.injected(type);
                    find = true;
                }
                if (annotation instanceof Text) {
                    builder.array();
                    find = true;
                }
                break;
            }
            if (!find) builder.argument(type);
        }
    }
}
