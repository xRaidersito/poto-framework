package me.raider.poto.timer.cooldown.holder;

import me.raider.poto.serializer.Serialize;

public abstract class AbstractCooldownHolder implements CooldownHolder {

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
