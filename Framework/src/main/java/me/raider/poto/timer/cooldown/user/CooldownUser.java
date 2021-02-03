package me.raider.poto.timer.cooldown.user;

public interface CooldownUser {

    long getActualCooldown();

    void setActualCooldown(long cooldown);

}
