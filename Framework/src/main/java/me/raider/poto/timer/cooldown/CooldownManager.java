package me.raider.poto.timer.cooldown;

public interface CooldownManager {

    void register(Cooldown cooldown);

    Cooldown getCooldown(String name);

}
