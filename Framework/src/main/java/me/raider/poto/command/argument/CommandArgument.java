package me.raider.poto.command.argument;

import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public interface CommandArgument {

    String[] getArgs();

    Player getPlayer();

    ConsoleCommandSender getConsole();

    CommandSender getSender();

}
