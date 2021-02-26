package me.raider.poto.command.authorizer;

import org.bukkit.command.CommandSender;

public class PotoAuthorizerImpl implements PotoAuthorizer {

    @Override
    public boolean isAuthorized(CommandSender sender, String... permissions) {
        if(permissions.length == 0) {
            return true;
        }

        for (String permission : permissions) {
            if(sender.hasPermission(permission)) {
                return true;
            }
        }
        return false;
    }
}
