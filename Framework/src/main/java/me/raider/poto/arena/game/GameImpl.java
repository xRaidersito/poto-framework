package me.raider.poto.arena.game;

import me.raider.poto.arena.Arena;
import me.raider.poto.arena.ArenaState;
import me.raider.poto.arena.game.phase.Phase;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class GameImpl implements Game {

    private final List<Phase> phases = new ArrayList<>();
    private final Arena arena;

    private Phase actualPhase;

    private int index;

    public GameImpl(Arena arena) {
        this.arena=arena;
        this.index=0;
    }

    @Override
    public List<Phase> getPhases() {
        return phases;
    }

    @Override
    public Arena getArena() {
        return arena;
    }

    @Override
    public Phase getActualPhase() {
        return actualPhase;
    }

    @Override
    public void nextPhase() {
        index++;

        if (index > phases.size()) {
            endGame();
        }

        this.actualPhase=phases.get(index);
        actualPhase.startPhase();

    }

    @Override
    public void previousPhase() {

        if (index==0) {
            return;
        }

        index--;

        this.actualPhase=phases.get(index);
        actualPhase.startPhase();

    }

    @Override
    public int getActualIndex() {
        return index;
    }

    @Override
    public void addPhase(Phase phase) {
        phases.add(phase);
    }

    @Override
    public void playerJoin(Player player) {



    }

    @Override
    public void playerLeave(Player player) {


    }

    @Override
    public void removePlayer(Player player) {


    }

    @Override
    public void broadcastToAll(String message) {
        arena.getPlayers().forEach(player -> player.sendMessage(message));
        arena.getSpectators().forEach(player -> player.sendMessage(message));
    }

    @Override
    public void startGame() {

        arena.setArenaState(ArenaState.WAITING);
        arena.updateSigns();

        actualPhase.startPhase();

    }

    @Override
    public void endGame() {




    }
}
