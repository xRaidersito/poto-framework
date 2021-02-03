package me.raider.poto.timer.cooldown.user;

import me.raider.poto.serializer.Serialize;

public abstract class AbstractCooldownUser implements CooldownUser {

    @Serialize(path = "cooldown")
    private long cooldownMillis;

    @Override
    public long getActualCooldown() {
        return cooldownMillis;
    }

    @Override
    public void setActualCooldown(long cooldown) {
        this.cooldownMillis=cooldown;
    }
}
