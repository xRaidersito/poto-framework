package me.raider.poto.timer.cooldown;

import me.raider.poto.timer.cooldown.user.CooldownUser;
import me.raider.poto.timer.cooldown.user.CooldownUserProvider;
import org.bukkit.entity.Player;

public class CooldownImpl implements Cooldown {

    private final CooldownMeta cooldownMeta;

    public CooldownImpl(CooldownMeta cooldownMeta) {
        this.cooldownMeta = cooldownMeta;
    }

    @Override
    public CooldownMeta getCooldownMeta() {
        return cooldownMeta;
    }

    @Override
    public void createCooldown(CooldownUser cooldownUser) {
        cooldownMeta.addCooldown(cooldownUser);
    }

    @Override
    public void addSeconds(CooldownUser cooldownUser, int seconds) {
        cooldownMeta.addCooldownSec(cooldownUser, seconds);
    }

    @Override
    public void removeSeconds(CooldownUser cooldownUser, int seconds) {
        cooldownMeta.removeCooldownSec(cooldownUser, seconds);
    }

    @Override
    public void removeCooldown(CooldownUser cooldownUser) {
        cooldownMeta.removeCooldown(cooldownUser);
    }

    @Override
    public boolean inCooldown(CooldownUser cooldownUser) {
        return cooldownMeta.inCooldown(cooldownUser);
    }

    @Override
    public String getName() {
        return cooldownMeta.getName();
    }
}
