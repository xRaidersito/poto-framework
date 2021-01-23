package me.raider.poto.command.argument;

import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class SimpleCommandArgument implements CommandArgument {

    private final String[] arguments;
    private final CommandSender sender;

    public SimpleCommandArgument(String[] arguments, CommandSender sender) {
        this.arguments=arguments;
        this.sender=sender;
    }

    @Override
    public String[] getArgs() {
        return arguments;
    }

    @Override
    public Player getPlayer() {
        if (sender instanceof Player) {
            return (Player) sender;
        }
        return null;
    }

    @Override
    public ConsoleCommandSender getConsole() {
        if (sender instanceof ConsoleCommandSender) {
            return (ConsoleCommandSender) sender;
        }
        return null;
    }

    @Override
    public CommandSender getSender() {
        return sender;
    }


}
