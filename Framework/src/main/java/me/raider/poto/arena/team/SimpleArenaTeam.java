package me.raider.poto.arena.team;

import me.raider.poto.utils.Color;
import me.raider.poto.utils.Utils;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class SimpleArenaTeam implements ArenaTeam {

    private Location location;
    private final List<Player> players = new ArrayList<>();
    private final Color color;
    private final String name;

    public SimpleArenaTeam(String name, Color color) {
        this.name=name;
        this.color=color;
    }

    public SimpleArenaTeam(String name, Color color, Location location) {
        this.name=name;
        this.color=color;
        this.location=location;
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public void setLocation(Location location) {
        this.location=location;
    }

    @Override
    public List<Player> getPlayers() {
        return players;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void addPlayer(Player player) {
        if (!players.contains(player)) {
            players.add(player);
        }
    }

    @Override
    public void removePlayer(Player player) {
        players.remove(player);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + "," + color + "," + Utils.locationToString(location);
    }

}
