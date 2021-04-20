package me.raider.plib.bukkit.cmd;

import me.raider.plib.commons.cmd.CommandManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExtendedCommand extends Command {

    private final CommandManager commandManager;

    protected ExtendedCommand(String name, CommandManager commandManager) {
        super(name);
        this.commandManager = commandManager;
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        List<String> newArgs = new ArrayList<>();
        newArgs.add(getName());
        newArgs.addAll(Arrays.asList(args));
        Object[] senderArray = new Object[]{sender};
        commandManager.getExecutor().execute(newArgs.toArray(new String[0]), new Object[]{new BukkitSender(sender)},
                senderArray, senderArray);
        return true;
    }
}
