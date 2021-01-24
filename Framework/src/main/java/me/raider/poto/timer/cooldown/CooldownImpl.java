package me.raider.poto.timer.cooldown;

import org.bukkit.entity.Player;

public class CooldownImpl implements Cooldown {

    private final CooldownMap cooldownMap = new SimpleCooldownMap();
    private final String name;
    private final int seconds;
    private final boolean persistent;

    public CooldownImpl(String name, int seconds, boolean persistent) {
        this.name = name;
        this.seconds = seconds;
        this.persistent = persistent;
    }


    @Override
    public void addCooldown(Player player) {

    }

    @Override
    public void addCooldownSec(Player player, int seconds) {

    }

    @Override
    public void removeCooldownSec(Player player, int seconds) {

    }

    @Override
    public void removeCooldown(Player player) {

    }

    @Override
    public boolean inCooldown(Player player) {
        return false;
    }

    @Override
    public boolean isPersistent() {
        return persistent;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getSecondsLeft(Player player) {
        return cooldownMap.get().get(player.getUniqueId().toString()) / 1000;
    }

    @Override
    public int getSeconds() {
        return seconds;
    }
}
