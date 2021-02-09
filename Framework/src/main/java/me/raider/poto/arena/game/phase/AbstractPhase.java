package me.raider.poto.arena.game.phase;

import me.raider.poto.arena.game.Game;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.Plugin;

public abstract class AbstractPhase implements Phase {

    private final Game game;
    private final Plugin plugin;
    private int taskId;

    public AbstractPhase(Plugin plugin, Game game) {
        this.game = game;
        this.plugin = plugin;
    }

    @Override
    public void startPhase() {
        Bukkit.getPluginManager().registerEvents(this, plugin);
        taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, this::update, 0L, 20L);
        onStart();
    }

    @Override
    public void endPhase() {
        onEnd();
        removeAll();
        game.nextPhase();
    }

    @Override
    public Game getGame() {
        return game;
    }

    @Override
    public void removeAll() {
        Bukkit.getScheduler().cancelTask(taskId);
        HandlerList.unregisterAll(this);
    }

    @Override
    public int getTaskId() {
        return taskId;
    }

    @Override
    public void onEnd() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void update() {

    }
}
