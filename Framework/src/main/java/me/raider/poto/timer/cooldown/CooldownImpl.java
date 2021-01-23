package me.raider.poto.timer.cooldown;

import org.bukkit.entity.Player;

public class CooldownImpl implements Cooldown {

    private final CooldownMap cooldownMap = new SimpleCooldownMap();
    private final String name;
    private final int seconds;

    public CooldownImpl(String name, int seconds) {
        this.name = name;
        this.seconds = seconds;

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
    public String getName() {
        return name;
    }

    @Override
    public int getSecondsLeft() {
        return 0;
    }

    @Override
    public int getSeconds() {
        return seconds;
    }
}
