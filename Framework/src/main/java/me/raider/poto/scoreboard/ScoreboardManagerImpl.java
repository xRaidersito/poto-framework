package me.raider.poto.scoreboard;

import java.util.HashMap;
import java.util.Map;

public class ScoreboardManagerImpl implements ScoreboardManager {

    private final Map<String, PotoScoreboard> scoreboardMap = new HashMap<>();

    @Override
    public Map<String, PotoScoreboard> get() {
        return scoreboardMap;
    }

    @Override
    public void register(PotoScoreboard scoreboard) {
        this.scoreboardMap.put(scoreboard.getName(), scoreboard);
    }
}
