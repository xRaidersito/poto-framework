package me.raider.poto.arena.game.phase.defaultphases.lobby;

import me.raider.poto.arena.game.Game;
import me.raider.poto.arena.game.phase.defaultphases.GamePhase;
import org.bukkit.plugin.Plugin;

public abstract class LobbyPhase extends GamePhase {

    public LobbyPhase(Plugin plugin, Game game) {
        super(plugin, game, false, false, false, false, false, false, false, false);
    }
}
