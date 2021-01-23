package me.raider.poto.timer.cooldown;

import java.util.Map;

public interface CooldownMap {

    Map<String, Long> get();

    default void put(String uuid, long millis) {
        get().put(uuid, millis);
    }

    default void remove(String uuid) {
        get().remove(uuid);
    }
}
