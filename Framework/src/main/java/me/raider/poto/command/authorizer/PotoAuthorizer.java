package me.raider.poto.command.authorizer;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public interface PotoAuthorizer {
    boolean isAuthorized(CommandSender sender, String... permissions);

    default boolean isAuthorized(CommandSender sender, String permission) {
        return isAuthorized(sender, permission);
    }
}
