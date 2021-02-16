package me.raider.poto.arena.team;

import me.raider.poto.storage.types.Storable;
import me.raider.poto.utils.Color;
import me.raider.poto.utils.Utils;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.List;

public interface ArenaTeam extends Storable {

    Location getLocation();

    void setLocation(Location location);

    List<Player> getPlayers();

    Color getColor();

    void addPlayer(Player player);

    void removePlayer(Player player);

    static ArenaTeam fromString(String stringTeam) {

        if (stringTeam == null || stringTeam.trim().equals("")) {
            return null;
        }
        String[] args = stringTeam.split(",");

        if (args.length==3) {

            String name = args[0];
            Color color = Color.valueOf(args[1]);
            Location location = Utils.locationFromString(args[2]);

            return new SimpleArenaTeam(name, color, location);
        }
        return null;
    }

}
