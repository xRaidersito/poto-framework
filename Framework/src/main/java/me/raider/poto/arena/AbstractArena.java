package me.raider.poto.arena;

import me.raider.poto.arena.game.Game;
import me.raider.poto.arena.game.GameImpl;
import me.raider.poto.channel.ChatChannel;
import me.raider.poto.channel.ChatChannelBuilder;
import me.raider.poto.cuboid.CuboidArea;
import me.raider.poto.serializer.Serialize;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractArena implements Arena {

    @Serialize(path = "min-players")
    private int minPlayers;

    @Serialize(path = "max-players")
    private int maxPlayers;

    @Serialize(path = "wait-cuboid")
    private CuboidArea waitLobbyCuboid;

    @Serialize(path = "spectators-location")
    private Location spectatorLocation;

    @Serialize(path = "wait-location")
    private Location waitLobbyLocation;

    @Serialize(path = "team-size")
    private int teamSize;

    @Serialize(path = "countdown-secs")
    private int countdownSeconds;

    @Serialize(path = "teams")
    private Map<String, ArenaTeam> teams = new HashMap<>();

    @Serialize(path = "arena-cuboid")
    private CuboidArea arenaCuboid;

    @Serialize(path = "enabled")
    private boolean enabled;

    private final String name;
    private final Game game;
    private ArenaState arenaState;
    private final List<Player> players = new ArrayList<>();
    private final List<Player> spectators = new ArrayList<>();
    private Plugin plugin;
    private final ArenaWorld arenaWorld;
    private final ChatChannel arenaChannel;

    public AbstractArena(Plugin plugin, String name, ArenaType type) {
        this.plugin=plugin;
        this.name=name;
        this.game=new GameImpl(this);
        this.arenaWorld=new ArenaWorld();
        this.arenaChannel=ChatChannelBuilder.create("arena-channel").prefix("test").build();
        this.arenaState=ArenaState.DISABLED;
        this.teamSize=type.getTeamSize();
        this.enabled=false;
    }

    public AbstractArena(Map<String, Object> map) {

        this.name=(String) map.get("name");

        this.game=new GameImpl(this);
        this.arenaWorld=new ArenaWorld();
        this.arenaChannel=ChatChannelBuilder.create("arena-channel").prefix("test").build();





    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getMinPlayers() {
        return minPlayers;
    }

    @Override
    public void setMinPlayers(int players) {
        this.minPlayers=players;
    }

    @Override
    public int getMaxPlayers() {
        return maxPlayers;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void setMaxPlayers(int players) {
        this.maxPlayers=players;
    }

    @Override
    public ChatChannel getChatChannel() {
        return arenaChannel;
    }

    @Override
    public CuboidArea getWaitLobbyCuboid() {
        return waitLobbyCuboid;
    }

    @Override
    public void setWaitLobbyCuboid(CuboidArea waitLobbyCuboid) {
        this.waitLobbyCuboid=waitLobbyCuboid;
    }

    @Override
    public Location getSpectatorLocation() {
        return spectatorLocation;
    }

    @Override
    public Location getWaitLobbyLocation() {
        return waitLobbyLocation;
    }

    @Override
    public void setSpectatorLocation(Location spectatorLocation) {
        this.spectatorLocation=spectatorLocation;
    }

    @Override
    public void setWaitLobbyLocation(Location lobbyLocation) {
        this.waitLobbyLocation=lobbyLocation;
    }

    @Override
    public int getTeamSize() {
        return teamSize;
    }

    @Override
    public void setTeamSize(int teamSize) {
        this.teamSize=teamSize;
    }

    @Override
    public int getCountdownSeconds() {
        return countdownSeconds;
    }

    @Override
    public void setCountdownSeconds(int seconds) {
        this.countdownSeconds=seconds;
    }

    @Override
    public int calculateMaxPlayers() {
        return 0;
    }

    @Override
    public Game getGame() {
        return game;
    }

    @Override
    public List<Player> getPlayers() {
        return players;
    }

    @Override
    public List<Player> getSpectators() {
        return spectators;
    }

    @Override
    public void addPlayer(Player player) {
        this.players.add(player);
    }

    @Override
    public void addSpectator(Player player) {
        this.spectators.add(player);
    }

    @Override
    public ArenaState getArenaState() {
        return arenaState;
    }

    @Override
    public void setArenaState(ArenaState arenaState) {
        this.arenaState=arenaState;
    }

    @Override
    public Map<String, ArenaTeam> getTeamMap() {
        return teams;
    }

    @Override
    public void addTeam(ArenaTeam team) {
        this.teams.put("", team);
    }

    @Override
    public void addTeam(String color) {
        this.teams.put("", null);
    }

    @Override
    public void updateSigns() {

    }

    @Override
    public void enableArena() {


        arenaState=ArenaState.WAITING;
        enabled=true;
    }

    @Override
    public void disableArena() {

        enabled=false;
        arenaState=ArenaState.DISABLED;

    }

    @Override
    public void regenArena() {



    }

    @Override
    public CuboidArea getArenaCuboid() {
        return arenaCuboid;
    }

    @Override
    public void setArenaCuboid(CuboidArea arenaCuboid) {
        this.arenaCuboid=arenaCuboid;
    }
}
