package me.raider.poto.timer.cooldown;

import me.raider.poto.Nameable;
import me.raider.poto.timer.cooldown.user.CooldownUserProvider;
import org.bukkit.entity.Player;

public interface Cooldown extends Nameable {

    CooldownMeta getCooldownMeta();

    CooldownUserProvider getUserProvider();

    void createCooldown(Player player);

    void addSeconds(Player player, int seconds);

    void removeSeconds(Player player, int seconds);

    void removeCooldown(Player player);

    boolean inCooldown(Player player);

}
