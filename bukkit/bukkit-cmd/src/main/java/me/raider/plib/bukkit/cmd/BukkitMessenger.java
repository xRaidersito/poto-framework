package me.raider.plib.bukkit.cmd;

import me.raider.plib.commons.cmd.message.Messenger;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class BukkitMessenger implements Messenger<CommandSender> {
    @Override
    public void sendMessage(String message, CommandSender holder) {
        holder.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }
}
