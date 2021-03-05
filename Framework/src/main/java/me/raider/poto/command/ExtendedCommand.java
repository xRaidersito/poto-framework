package me.raider.poto.command;

import me.raider.poto.command.annotation.SubCommand;
import me.raider.poto.command.argument.ArgumentParser;
import me.raider.poto.command.authorizer.PotoAuthorizer;
import me.raider.poto.command.message.MessageProvider;
import me.raider.poto.command.parameter.ParameterCreator;
import me.raider.poto.command.parameter.ParameterHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExtendedCommand extends Command {

    private final RegisteredCommand registeredCommand;
    private final MessageProvider messageProvider;
    private final ArgumentParser argumentParser;
    private final ParameterHandler parameterHandler;
    private final PotoAuthorizer potoAuthorizer;

    public ExtendedCommand(RegisteredCommand registeredCommand, MessageProvider messageProvider,
                           ArgumentParser argumentParser, ParameterHandler parameterHandler, PotoAuthorizer potoAuthorizer) {
        super(registeredCommand.getCommand().name());
        this.parameterHandler = parameterHandler;

        setAliases(Arrays.asList(registeredCommand.getCommand().aliases()));
        setDescription(registeredCommand.getCommand().description());

        this.registeredCommand = registeredCommand;
        this.messageProvider=messageProvider;
        this.argumentParser=argumentParser;
        this.potoAuthorizer = potoAuthorizer;
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {

        for (Method method : registeredCommand.getMethods()) {

            if (args.length==0 && !method.isAnnotationPresent(SubCommand.class) && method.getParameterCount()==1) {

                Class<?> parameterType = method.getParameterTypes()[0];

                if (!checkType(parameterType, sender)) {
                    return true;
                }

                if (!potoAuthorizer.isAuthorized(sender, registeredCommand.getCommand().permissions())) {
                    sender.sendMessage(messageProvider.getMessage("no-permission"));
                    return true;
                }
                try {
                    method.invoke(registeredCommand.getInstance(), sender);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
                return true;
            }

            if (method.isAnnotationPresent(SubCommand.class) && args.length!=0 && method.getParameterCount()>0) {

                SubCommand subCommand = method.getAnnotation(SubCommand.class);

                for (int i = 0 ; i <= subCommand.subcommand().length-1 ; i++) {

                    int spaces = checkSpaces(subCommand.subcommand()[i]);

                    StringBuilder sb = new StringBuilder();

                    if (spaces>1) {
                        sb = buildNewArgs(args, spaces);
                    } else {
                        sb.append(args[0]);
                    }

                    if (!sb.toString().equalsIgnoreCase(subCommand.subcommand()[i])) {
                        continue;
                    }

                    Class<?> senderType = method.getParameterTypes()[0];

                    if (!checkType(senderType, sender)) {
                        return true;
                    }

                    if (!subCommand.permission().equalsIgnoreCase("no-permission")) {

                        if (!subCommand.permission().equalsIgnoreCase("command-permission") && !sender.hasPermission(subCommand.permission())) {
                            sender.sendMessage(messageProvider.getMessage("no-permission"));
                            return true;
                        } else {
                            if (!potoAuthorizer.isAuthorized(sender, registeredCommand.getCommand().permissions())) {
                                sender.sendMessage(messageProvider.getMessage("no-permission"));
                                return true;
                            }
                        }
                    }
                    String[] subCmdSizeArray = sb.toString().split(" ");

                    String[] newArgs = argumentParser.parse(args, subCmdSizeArray.length);

                    if (newArgs.length == method.getParameterCount()-1) {

                        List<Object> paramList = new ArrayList<>();

                        paramList.add(sender);

                        for (int x = 1; x <= method.getParameterCount()-1 ; x++) {

                            ParameterCreator<Object> parameterCreator = parameterHandler.getParameter(method.getParameterTypes()[x]);

                            if (parameterCreator==null) {
                                sender.sendMessage(messageProvider.getMessage("not-registered-argument"));
                                return true;
                            }

                            if (!parameterCreator.isPresent(newArgs[x-1])) {
                                sender.sendMessage(messageProvider.getMessage("invalid-argument"));
                                return true;
                            }
                            paramList.add(parameterCreator.create(newArgs[x-1]));
                        }

                        try {
                            method.invoke(registeredCommand.getInstance(), paramList.toArray());
                            return true;
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            e.printStackTrace();
                        }

                    } else {
                        sender.sendMessage(messageProvider.getMessage("usage"));
                        return true;
                    }
                }
            }
        }
        return false;
    }


    /**
     * A method that will check all spaces in a string.
     *
     * @param string The string to be checked.
     * @return The number of spaces present.
     */
    private int checkSpaces(String string) {

        int spaces = 0;

        for (int i = 0 ; i < string.length() ; i++) {

            if (string.charAt(i)==' ') {
                spaces++;
            }

        }
        return spaces;
    }

    /**
     * A method that will check the type of the {@link CommandSender}
     *
     * @param clazz The type that has to be equal.
     * @param sender The sender of the command.
     * @return False if don't equals the sender, whatever true.
     */
    private boolean checkType(Class<?> clazz, CommandSender sender) {

        if (clazz.equals(Player.class) && !(sender instanceof Player)) {

            sender.sendMessage(messageProvider.getMessage("only-players"));
            return true;
        }
        else if (clazz.equals(ConsoleCommandSender.class)
                && !(sender instanceof ConsoleCommandSender)) {

            sender.sendMessage(messageProvider.getMessage("only-console"));
            return true;
        }

        return false;
    }

    /**
     * A method that will build a new {@link StringBuilder} based in subcommand args.
     *
     * @param args The args that will build the new string.
     * @param spaces The total spaces that has the args.
     * @return The new {@link StringBuilder}.
     */
    private StringBuilder buildNewArgs(String[] args, int spaces) {

        StringBuilder sb = new StringBuilder();

        sb.append(args[0]);

        if (spaces > 0) {

            for (int x = 1 ; x < spaces + 1 ; x++) {

                sb.append(' ').append(args[x]);
            }
        }
        return sb;
    }

}
