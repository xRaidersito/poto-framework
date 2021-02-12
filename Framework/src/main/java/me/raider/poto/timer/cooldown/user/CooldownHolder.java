package me.raider.poto.timer.cooldown.user;

public interface CooldownHolder {

    long getActualCooldown();

    void setActualCooldown(long cooldown);

}
