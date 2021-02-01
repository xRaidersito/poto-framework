package me.raider.poto.arena.game.phase;

import me.raider.poto.arena.game.Game;
import org.bukkit.event.Listener;

public interface Phase extends Listener {

    void onStart();

    void update();

    void onEnd();

    void removeAll();

    void startPhase();

    void endPhase();

    int getTaskId();

    Game getGame();

}
