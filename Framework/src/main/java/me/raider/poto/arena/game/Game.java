package me.raider.poto.arena.game;

import me.raider.poto.arena.Arena;
import me.raider.poto.arena.game.phase.Phase;
import org.bukkit.entity.Player;

import java.util.List;

public interface Game {

    List<Phase> getPhases();

    Arena getArena();

    Phase getActualPhase();

    void nextPhase();

    void previousPhase();

    int getActualIndex();

    void addPhase(Phase phase);

    void playerJoin(Player player);

    void playerLeave(Player player);

    void removePlayer(Player player);

    void broadcastToAll(String message);

    void startGame();

    void endGame();

}
