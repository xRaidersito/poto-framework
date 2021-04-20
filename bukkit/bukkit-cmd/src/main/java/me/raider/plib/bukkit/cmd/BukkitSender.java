package me.raider.plib.bukkit.cmd;

import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.Optional;

public class BukkitSender {

    private final CommandSender sender;

    public BukkitSender(CommandSender sender) {
        this.sender = sender;
    }

    public boolean isPlayerSender() {
        return sender instanceof Player;
    }

    public boolean isConsoleSender() {
        return sender instanceof ConsoleCommandSender;
    }

    public CommandSender getSender() {
        return sender;
    }

    public <T> Optional<T> getSender(Class<T> tClass) {
        if (tClass.isAssignableFrom(sender.getClass())) {
            return Optional.of((T) sender);
        }
        return Optional.empty();
    }

}
