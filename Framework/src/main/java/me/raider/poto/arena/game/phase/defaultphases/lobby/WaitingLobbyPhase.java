package me.raider.poto.arena.game.phase.defaultphases.lobby;

import me.raider.poto.arena.ArenaState;
import me.raider.poto.arena.game.Game;
import org.bukkit.plugin.Plugin;

public class WaitingLobbyPhase extends LobbyPhase {

    public WaitingLobbyPhase(Plugin plugin, Game game) {
        super(plugin, game);
    }

    @Override
    public void onStart() {
        getGame().getArena().setArenaState(ArenaState.WAITING);
    }

    @Override
    public void update() {

        if (getGame().getArena().getPlayers().size()>=getGame().getArena().getMinPlayers()) {
            endPhase();
        }

    }
}
