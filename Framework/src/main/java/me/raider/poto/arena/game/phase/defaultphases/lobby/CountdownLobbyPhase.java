package me.raider.poto.arena.game.phase.defaultphases.lobby;

import me.raider.poto.arena.ArenaState;
import me.raider.poto.arena.game.Game;
import me.raider.poto.file.YamlFile;
import me.raider.poto.timer.countdown.Countdown;
import org.bukkit.plugin.Plugin;

public class CountdownLobbyPhase extends LobbyPhase {

    private final YamlFile lang;

    public CountdownLobbyPhase(Plugin plugin, Game game, YamlFile lang) {
        super(plugin, game);
        this.lang=lang;
    }

    @Override
    public void onStart() {

        getGame().getArena().setArenaState(ArenaState.COUNTDOWN);

        Countdown.Builder.create(getGame().getArena().getPlugin())
                .start(countdown -> getGame().broadcastToAll(lang.getColorizedString("message.countdown.start")))
                .filter(countdown -> getGame().getArena().getPlayers().size()<getGame().getArena().getMinPlayers())
                .everySecond(countdown -> {
                    if (shouldAnnounceTime(countdown.getSecondsLeft())) {
                        getGame().broadcastToAll(lang.getColorizedString("message.countdown.announce")
                                .replace("%seconds%", String.valueOf(countdown.getSecondsLeft())));
                    }
                })
                .finish(countdown -> endPhase())
                .onCancel(countdown -> getGame().previousPhase())
                .seconds(getGame().getArena().getCountdownSeconds()).build().start();
    }

    private boolean shouldAnnounceTime(int i)
    {
        return (i <= 20 && i % 5 == 0 && i != 15) || i <= 5;
    }

}
