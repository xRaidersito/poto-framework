package me.raider.poto.arena;

import me.raider.poto.arena.game.Game;
import me.raider.poto.arena.team.ArenaTeam;
import me.raider.poto.channel.ChatChannel;
import me.raider.poto.cuboid.CuboidArea;
import me.raider.poto.storage.types.Storable;
import me.raider.poto.utils.Color;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.List;
import java.util.Map;

public interface Arena extends Storable {

    void loadNecessaryData(Map<String, Object> serializedData);

    int getMinPlayers();

    void setMinPlayers(int players);

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

    ArenaTeam randomizeTeam();

    void addPlayerToTeam(Player player, ArenaTeam team);

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

    void addTeam(Color color, String name);

    void updateSigns();

    ArenaTeam getTeamByPlayer(Player player);

    void enableArena();

    void disableArena();

    void clearTeams();

    boolean isEnabled();

    void regenArena();

    void initAdditionalData(Map<String, Object> map);

    CuboidArea getArenaCuboid();

    void setArenaCuboid(CuboidArea arenaCuboid);

    Plugin getPlugin();

    ArenaWorld getWorld();

    void setWinners(Player... players);

    void playerDeath(Player player);

    boolean isFull();

}
