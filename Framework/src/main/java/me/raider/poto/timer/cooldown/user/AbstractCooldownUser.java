package me.raider.poto.timer.cooldown.user;

import me.raider.poto.internal.Serialize;

public abstract class AbstractCooldownUser implements CooldownUser {

    @Serialize(path = "cooldown")
    private Long cooldownMillis;

    @Override
    public Long getActualCooldown() {
        return cooldownMillis;
    }

    @Override
    public void setActualCooldown(Long cooldown) {
        this.cooldownMillis=cooldown;
    }
}
