package me.raider.poto.command;

import me.raider.poto.command.annotation.Command;
import me.raider.poto.command.argument.SimpleArgumentParser;
import me.raider.poto.command.message.MessageProvider;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandMap;
import org.bukkit.command.SimpleCommandMap;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class PotoCommandAPI {

    private PotoCommandManager commandManager;
    private CommandMap commandMap;
    private MessageProvider messageProvider;

    public void init(PotoCommandManager commandManager, MessageProvider messageProvider) {

        initCommandMap();

        this.commandManager=commandManager;
        this.messageProvider=messageProvider;
    }

    public void register(PotoCommand... potoCommands) {

        for (PotoCommand command : potoCommands) {
            register(command);
        }
    }

    public void register(PotoCommand potoCommand) {

        RegisteredCommand registered = registerCommand(potoCommand);

        if (registered==null) {
            throw new CommandException("The target class don't has the @Command annotation");
        }

        commandMap.register(registered.getCommand().name(), new ExtendedCommand(registered, messageProvider, new SimpleArgumentParser()));
    }

    private RegisteredCommand registerCommand(PotoCommand potoCommand) {

        Class<? extends PotoCommand> potoClass = potoCommand.getClass();

        RegisteredCommand registeredCommand = null;

        if (potoClass.isAnnotationPresent(Command.class)) {

            registeredCommand = new SimpleRegisteredCommand(potoClass.getMethods(), potoClass.getAnnotation(Command.class), potoCommand);
            commandManager.getRegisteredCommands().putIfAbsent(potoClass.getAnnotation(Command.class).name(), registeredCommand);

        }
        return registeredCommand;
    }


    private void initCommandMap() {

        CommandMap commandMap = null;

        try {
            final Server server = Bukkit.getServer();
            final Method getCommandMap = server.getClass().getDeclaredMethod("getCommandMap");
            getCommandMap.setAccessible(true);

            commandMap = (CommandMap) getCommandMap.invoke(server);

            final Field bukkitCommands = SimpleCommandMap.class.getDeclaredField("knownCommands");
            bukkitCommands.setAccessible(true);

        } catch (final Exception e) {
            e.printStackTrace();
        }

        this.commandMap=commandMap;
    }

}
