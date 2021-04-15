package me.raider.plib.bukkit.cmd.supplier;

import me.raider.plib.commons.cmd.ArgumentSupplier;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PlayerSupplier implements ArgumentSupplier<Player> {

    @Override
    public Player supply(Object object) {
        if (object instanceof Player) {
            return (Player) object;
        }
        if (object instanceof String) {
            return Bukkit.getPlayer((String) object);
        }

        return null;
    }
}
