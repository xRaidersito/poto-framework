package me.raider.poto.arena;

import me.raider.poto.arena.game.Game;
import me.raider.poto.arena.game.GameImpl;
import me.raider.poto.arena.team.ArenaTeam;
import me.raider.poto.arena.team.SimpleArenaTeam;
import me.raider.poto.channel.ChatChannel;
import me.raider.poto.channel.ChatChannelBuilder;
import me.raider.poto.cuboid.CuboidArea;
import me.raider.poto.serializer.Serialize;
import me.raider.poto.utils.Color;
import me.raider.poto.utils.StorableLocation;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.MemorySection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractArena implements Arena {

    @Serialize(path = "min-players")
    private int minPlayers;

    @Serialize(path = "wait-cuboid")
    private CuboidArea waitLobbyCuboid;

    @Serialize(path = "spectators-location")
    private StorableLocation spectatorLocation;

    @Serialize(path = "wait-location")
    private StorableLocation waitLobbyLocation;

    @Serialize(path = "team-size")
    private int teamSize;

    @Serialize(path = "countdown-secs")
    private int countdownSeconds;

    @Serialize(path = "teams")
    private final Map<String, ArenaTeam> teams = new HashMap<>();

    @Serialize(path = "arena-cuboid")
    private CuboidArea arenaCuboid;

    @Serialize(path = "enabled")
    private boolean enabled;

    private final String name;
    private final Game game;
    private ArenaState arenaState;
    private final List<Player> players = new ArrayList<>();
    private final List<Player> spectators = new ArrayList<>();
    private final Plugin plugin;
    private final ArenaWorld arenaWorld;
    private final ChatChannel arenaChannel;

    public AbstractArena(Plugin plugin, String name, ArenaType type) {
        this.plugin=plugin;
        this.name=name;
        this.game=new GameImpl(this);
        this.arenaWorld=new ArenaWorld(this);
        this.arenaChannel=ChatChannelBuilder.create("arena-channel").prefix("test").build();
        this.arenaState=ArenaState.DISABLED;
        this.teamSize=type.getTeamSize();
        this.enabled=false;
    }

    public void loadNecessaryData(Map<String, Object> map) {

        if (map.get("wait-cuboid")!=null) {
            this.waitLobbyCuboid = CuboidArea.deserialize(((MemorySection) map.get("wait-cuboid")).getValues(true));
        }

        if (map.get("arena-cuboid")!=null) {
            this.arenaCuboid = CuboidArea.deserialize(((MemorySection) map.get("arena-cuboid")).getValues(true));
        }

        if (map.get("wait-location")!=null) {
            this.waitLobbyLocation = StorableLocation.deserialize(((MemorySection) map.get("wait-location")).getValues(true));
        }

        if (map.get("spectators-location")!=null) {
            this.spectatorLocation = StorableLocation.deserialize(((MemorySection) map.get("spectators-location")).getValues(true));
        }

        if (map.get("min-players")!=null) {
            this.minPlayers=(int) map.get("min-players");
        }

        if (map.get("countdown-secs")!=null) {
            this.countdownSeconds=(int) map.get("countdown-secs");
        }

        if (map.get("team-size")!=null) {
            this.teamSize=(int) map.get("team-size");
        }

        if (map.get("teams")!=null) {

            Map<String, Object> teamsMap = ((MemorySection)map.get("teams")).getValues(true);

            teamsMap.keySet().forEach(key -> {
                ArenaTeam team = ArenaTeam.fromString((String) teamsMap.get(key));
                this.teams.put(key, team);
            });
        }

        if (map.get("enabled")!=null) {

            this.enabled=(boolean) map.get("enabled");

            if (enabled) {
                enableArena();
            } else {
                disableArena();
            }

        } else {
            disableArena();
        }

        initAdditionalData(map);
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
    public boolean isEnabled() {
        return enabled;
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
        return spectatorLocation.toLocation();
    }

    @Override
    public Location getWaitLobbyLocation() {
        return waitLobbyLocation.toLocation();
    }

    @Override
    public void setSpectatorLocation(Location spectatorLocation) {
        this.spectatorLocation=new StorableLocation(spectatorLocation);
    }

    @Override
    public void setWaitLobbyLocation(Location lobbyLocation) {
        this.waitLobbyLocation=new StorableLocation(lobbyLocation);
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
        return teamSize*teams.size();
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
        this.teams.put(team.getName(), team);
    }

    @Override
    public void addTeam(Color color, String name) {
        this.teams.put(name, new SimpleArenaTeam(name, color));
    }

    @Override
    public void addPlayerToTeam(Player player, ArenaTeam team) {

        if (team == null) {
            return;
        }

        if (team.getPlayers().size()==teamSize) {
            return;
        }

        team.addPlayer(player);
    }

    @Override
    public ArenaTeam randomizeTeam() {

        ArenaTeam betterTeam = null;

        for (ArenaTeam team : this.teams.values()) {
            if (team.getPlayers().size() > this.teamSize) {
                continue;
            }

            if (betterTeam == null) {
                betterTeam = team;

                continue;
            }

            if (betterTeam.getPlayers().size() > team.getPlayers().size()) {
                continue;
            }

            betterTeam = team;
        }

        return betterTeam;
    }

    @Override
    public void updateSigns() {

    }

    @Override
    public void enableArena() {

        for (Entity entity : arenaWorld.getWorld().getEntities()) {
            if (entity instanceof Player) {
                ((Player) entity).kickPlayer("Enabling arena!");
            }
            entity.remove();
        }

        arenaWorld.saveWorld();

        game.startGame();

        arenaState=ArenaState.WAITING;
        enabled=true;

        updateSigns();
    }

    @Override
    public void disableArena() {

        if (!arenaWorld.loadFromDirectory()) {
            Bukkit.getLogger().info("The map was not find in directory, creating map");
        }
        arenaWorld.loadWorld();

        game.endGame();

        enabled=false;
        arenaState=ArenaState.DISABLED;

        updateSigns();

    }

    @Override
    public void regenArena() {
        arenaWorld.deleteWorld();
    }

    @Override
    public CuboidArea getArenaCuboid() {
        return arenaCuboid;
    }

    @Override
    public void setArenaCuboid(CuboidArea arenaCuboid) {
        this.arenaCuboid=arenaCuboid;
    }

    @Override
    public Plugin getPlugin() {
        return plugin;
    }
}
