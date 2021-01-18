package me.raider.poto.command;

import me.raider.poto.command.annotation.SubCommand;
import me.raider.poto.command.argument.ArgumentParser;
import me.raider.poto.command.argument.SimpleCommandArgument;
import me.raider.poto.command.message.MessageProvider;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class ExtendedCommand extends Command {

    private final RegisteredCommand registeredCommand;
    private final MessageProvider messageProvider;
    private final ArgumentParser argumentParser;

    public ExtendedCommand(RegisteredCommand registeredCommand, MessageProvider messageProvider, ArgumentParser argumentParser) {
        super(registeredCommand.getCommand().name());

        setAliases(Arrays.asList(registeredCommand.getCommand().aliases()));
        setDescription(registeredCommand.getCommand().description());

        this.registeredCommand = registeredCommand;
        this.messageProvider=messageProvider;
        this.argumentParser=argumentParser;
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {

        for (Method method : registeredCommand.getMethods()) {

            if (args.length==0 && !method.isAnnotationPresent(SubCommand.class)) {

                if (!checkType(registeredCommand.getCommand().type(), sender)) {
                    return true;
                }

                if (!sender.hasPermission(registeredCommand.getCommand().permission())) {
                    sender.sendMessage(messageProvider.getMessage("no-permission"));
                    return true;
                }
                try {
                    method.invoke(registeredCommand.getInstance(), new SimpleCommandArgument(args, sender));
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
                return true;
            }

            if (method.isAnnotationPresent(SubCommand.class) && args.length!=0) {

                SubCommand subCommand = method.getAnnotation(SubCommand.class);

                for (int i = 0 ; i < subCommand.subcommand().length ; i++) {

                    int spaces = checkSpaces(subCommand.subcommand()[i]);

                    StringBuilder sb = new StringBuilder();

                    if (sb.length()==0) {
                        sb.append(args[0]);
                    }

                    if (spaces > 0) {

                        for (int x = 1 ; x < spaces + 1 ; x++) {

                            sb.append(' ' + args[i]);
                        }
                    }

                    if (!sb.toString().equalsIgnoreCase(subCommand.subcommand()[i])) {
                        continue;
                    }

                    String type = registeredCommand.getCommand().type();

                    if (subCommand.type().equalsIgnoreCase("")) {
                        type=subCommand.type();
                    }

                    if (!checkType(type, sender)) {
                        return true;
                    }

                    if (subCommand.permission()!=null && !sender.hasPermission(subCommand.permission())
                            || subCommand.permission()==null && !sender.hasPermission(registeredCommand.getCommand().permission())) {
                        sender.sendMessage(messageProvider.getMessage("no-permission"));
                        return true;
                    }

                    String[] subCmdSizeArray = sb.toString().split(" ");

                    String[] newArgs = argumentParser.parse(args, subCmdSizeArray.length);

                    if (newArgs.length == method.getAnnotation(SubCommand.class).args()) {

                        try {
                            method.invoke(registeredCommand.getInstance(), new SimpleCommandArgument(newArgs, sender));
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            e.printStackTrace();
                        }
                        return true;
                    }

                    sender.sendMessage(messageProvider.getMessage("usage"));
                    return true;
                }
            }
        }
        return false;
    }


    private int checkSpaces(String string) {

        int spaces = 0;

        for (int i = 0 ; i < string.length() ; i++) {

            if (string.charAt(i)==' ') {
                spaces++;
            }

        }
        return spaces;
    }

    private boolean checkType(String type, CommandSender sender) {

        System.out.println(type + "+");

        if (type.equalsIgnoreCase("player")
                && !(sender instanceof Player)) {

            sender.sendMessage(messageProvider.getMessage("only-players"));
            return false;
        }
        else if (type.equalsIgnoreCase("console")
                && !(sender instanceof ConsoleCommandSender)) {

            sender.sendMessage(messageProvider.getMessage("only-console"));
            return false;
        }

        return true;
    }

}
