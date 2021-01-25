package me.raider.poto.timer.cooldown.user;

public interface CooldownUser {

    Long getActualCooldown();

    void setActualCooldown(Long cooldown);

}
