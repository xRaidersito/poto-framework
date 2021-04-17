package me.raider.plib.bukkit.cmd;

import me.raider.plib.commons.cmd.Authorizer;
import me.raider.plib.commons.cmd.Command;
import org.bukkit.command.CommandSender;

public class BukkitAuthorizer implements Authorizer<CommandSender> {
    @Override
    public boolean isAuthorized(CommandSender object, Command command) {
        String permission = command.getPermission();
        if (permission.isEmpty()) {
            return true;
        }
        return object.hasPermission(permission);
    }
}
