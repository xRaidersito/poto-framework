package me.raider.poto.arena;

import me.raider.poto.arena.game.Game;
import me.raider.poto.channel.ChatChannel;
import me.raider.poto.cuboid.CuboidArea;
import me.raider.poto.storage.types.Storable;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Map;

public interface Arena extends Storable {

    int getMinPlayers();

    void setMinPlayers(int players);

    int getMaxPlayers();

    void setMaxPlayers(int players);

    ChatChannel getChatChannel();

    CuboidArea getWaitLobbyCuboid();

    void setWaitLobbyCuboid(CuboidArea waitLobbyCuboid);

    Location getSpectatorLocation();

    Location getWaitLobbyLocation();

    void setSpectatorLocation(Location spectatorLocation);

    void setWaitLobbyLocation(Location lobbyLocation);

    int getTeamSize();

    void setTeamSize(int teamSize);

    int getCountdownSeconds();

    void setCountdownSeconds(int seconds);

    int calculateMaxPlayers();

    Game getGame();

    List<Player> getPlayers();

    List<Player> getSpectators();

    void addPlayer(Player player);

    void addSpectator(Player player);

    ArenaState getArenaState();

    void setArenaState(ArenaState arenaState);

    Map<String, ArenaTeam> getTeamMap();

    void addTeam(ArenaTeam team);

    void addTeam(String color);

    void updateSigns();

    void enableArena();

    void disableArena();

    boolean isEnabled();

    void regenArena();

    void initAdditionalData(Map<String, Object> map);

    CuboidArea getArenaCuboid();

    void setArenaCuboid(CuboidArea arenaCuboid);

}
