package me.raider.poto.timer.cooldown;

import me.raider.poto.timer.cooldown.user.CooldownHolder;

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
    public void createCooldown(CooldownHolder cooldownUser) {
        cooldownMeta.addCooldown(cooldownUser);
    }

    @Override
    public void addSeconds(CooldownHolder cooldownUser, int seconds) {
        cooldownMeta.addCooldownSec(cooldownUser, seconds);
    }

    @Override
    public void removeSeconds(CooldownHolder cooldownUser, int seconds) {
        cooldownMeta.removeCooldownSec(cooldownUser, seconds);
    }

    @Override
    public void removeCooldown(CooldownHolder cooldownUser) {
        cooldownMeta.removeCooldown(cooldownUser);
    }

    @Override
    public boolean inCooldown(CooldownHolder cooldownUser) {
        return cooldownMeta.inCooldown(cooldownUser);
    }

    @Override
    public String getName() {
        return cooldownMeta.getName();
    }
}
