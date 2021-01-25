package me.raider.poto.timer.cooldown;

import java.util.Optional;

public interface CooldownManager {

    void register(Cooldown cooldown);

    Optional<Cooldown> getCooldown(String name);

}
