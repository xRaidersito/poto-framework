package me.raider.poto.timer.cooldown;

import me.raider.poto.timer.cooldown.user.CooldownUserProvider;
import org.bukkit.entity.Player;

public class CooldownImpl implements Cooldown {

    private final CooldownMeta cooldownMeta;
    private final CooldownUserProvider provider;

    public CooldownImpl(CooldownMeta cooldownMeta, CooldownUserProvider provider) {
        this.cooldownMeta = cooldownMeta;
        this.provider = provider;
    }

    @Override
    public CooldownMeta getCooldownMeta() {
        return cooldownMeta;
    }

    @Override
    public CooldownUserProvider getUserProvider() {
        return provider;
    }

    @Override
    public void createCooldown(Player player) {
        cooldownMeta.addCooldown(provider.getCooldownUser(player.getUniqueId().toString()));
    }

    @Override
    public void addSeconds(Player player, int seconds) {
        cooldownMeta.addCooldownSec(provider.getCooldownUser(player.getUniqueId().toString()), seconds);
    }

    @Override
    public void removeSeconds(Player player, int seconds) {
        cooldownMeta.removeCooldownSec(provider.getCooldownUser(player.getUniqueId().toString()), seconds);
    }

    @Override
    public void removeCooldown(Player player) {
        cooldownMeta.removeCooldown(provider.getCooldownUser(player.getUniqueId().toString()));
    }

    @Override
    public boolean inCooldown(Player player) {
        return cooldownMeta.inCooldown(provider.getCooldownUser(player.getUniqueId().toString()));
    }

    @Override
    public String getName() {
        return cooldownMeta.getName();
    }
}
