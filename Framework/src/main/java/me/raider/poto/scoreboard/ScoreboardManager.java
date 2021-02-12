package me.raider.poto.scoreboard;

import java.util.Map;
import java.util.Optional;

public interface ScoreboardManager {

    Map<String, PotoScoreboard> get();

    void register(PotoScoreboard scoreboard);

    default void register(PotoScoreboard... scoreboards) {
        for (PotoScoreboard scoreboard : scoreboards) {
            register(scoreboard);
        }
    }

    default Optional<PotoScoreboard> getScoreboard(String key) {
        return Optional.ofNullable(get().get(key));
    }


}
