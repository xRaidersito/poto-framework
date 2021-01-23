package me.raider.poto.timer.cooldown;

import java.util.HashMap;
import java.util.Map;

public class SimpleCooldownMap implements CooldownMap {

    private final Map<String, Long> cooldownMap = new HashMap<>();

    @Override
    public Map<String, Long> get() {
        return cooldownMap;
    }
}
