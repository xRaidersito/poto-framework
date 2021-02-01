package me.raider.poto.arena;

import me.raider.poto.arena.game.Game;
import me.raider.poto.arena.game.GameImpl;
import me.raider.poto.channel.ChatChannel;
import me.raider.poto.channel.ChatChannelBuilder;
import me.raider.poto.cuboid.CuboidArea;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractArena implements Arena {

    private final String name;
    private int minPlayers;
    private int maxPlayers;
    private final ChatChannel arenaChannel;
    private CuboidArea waitLobbyCuboid;
    private Location spectatorLocation;
    private int teamSize;
    private int countdownSeconds;
    private int requiredPlayers;
    private final Game game;
    private ArenaState arenaState;
    private final List<Player> players = new ArrayList<>();
    private final List<Player> spectators = new ArrayList<>();
    private Map<String, ArenaTeam> teams = new HashMap<>();
    private final ArenaWorld arenaWorld;
    private CuboidArea arenaCuboid;
    private boolean enabled;
    private Plugin plugin;

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
        return 0;
    }

    @Override
    public void setMaxPlayers(int players) {

    }

    @Override
    public ChatChannel getChatChannel() {
        return null;
    }

    @Override
    public CuboidArea getWaitLobbyCuboid() {
        return null;
    }

    @Override
    public void setWaitLobbyCuboid(CuboidArea waitLobbyCuboid) {

    }

    @Override
    public Location getSpectatorLocation() {
        return null;
    }

    @Override
    public Location getWaitLobbyLocation() {
        return null;
    }

    @Override
    public void setSpectatorLocation(Location spectatorLocation) {

    }

    @Override
    public void setWaitLobbyLocation(Location lobbyLocation) {

    }

    @Override
    public int getTeamSize() {
        return 0;
    }

    @Override
    public void setTeamSize(int teamSize) {

    }

    @Override
    public int getCountdownSeconds() {
        return 0;
    }

    @Override
    public void setCountdownSeconds(int seconds) {

    }

    @Override
    public int getRequiredPlayers() {
        return 0;
    }

    @Override
    public void setRequiredPlayers(int requiredPlayers) {

    }

    @Override
    public int calculateMaxPlayers() {
        return 0;
    }

    @Override
    public Game getGame() {
        return null;
    }

    @Override
    public List<Player> getPlayers() {
        return null;
    }

    @Override
    public List<Player> getSpectators() {
        return null;
    }

    @Override
    public void addPlayer(Player player) {

    }

    @Override
    public void addSpectator(Player player) {

    }

    @Override
    public ArenaState getArenaState() {
        return null;
    }

    @Override
    public void setArenaState(ArenaState arenaState) {

    }

    @Override
    public Map<String, ArenaTeam> getTeamMap() {
        return null;
    }

    @Override
    public void addTeam(ArenaTeam team) {

    }

    @Override
    public void addTeam(String color) {

    }

    @Override
    public void updateSigns() {

    }

    @Override
    public void enableArena() {

    }

    @Override
    public void disableArena() {

    }

    @Override
    public void regenArena() {

    }

    @Override
    public CuboidArea getArenaCuboid() {
        return null;
    }

    @Override
    public void setArenaCuboid(CuboidArea arenaCuboid) {

    }
}
