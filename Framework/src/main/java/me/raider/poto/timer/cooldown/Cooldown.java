package me.raider.poto.timer.cooldown;

import me.raider.poto.Nameable;
import me.raider.poto.timer.Timeable;
import org.bukkit.entity.Player;

public interface Cooldown extends Timeable, Nameable {


    void addCooldown(Player player);

    void addCooldownSec(Player player, int seconds);

    void removeCooldownSec(Player player, int seconds);

    void removeCooldown(Player player);

    double getSecondsLeft(Player player);

    boolean inCooldown(Player player);

    boolean isPersistent();

    interface Builder {

        Builder name(String name);

        Builder persistent(boolean persistent);

        Builder defaultSeconds(int seconds);

        Cooldown build();

    }


}
