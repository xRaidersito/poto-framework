package me.raider.poto.timer.cooldown;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class SimpleCooldownManager implements CooldownManager {

    private final Map<String, Cooldown> cooldownMap = new HashMap<>();

    @Override
    public void register(Cooldown cooldown) {
        cooldownMap.put(cooldown.getName(), cooldown);
    }

    @Override
    public Optional<Cooldown> getCooldown(String name) {
        return Optional.ofNullable(cooldownMap.get(name));
    }
}
