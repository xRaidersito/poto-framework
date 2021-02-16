package me.raider.poto.scoreboard;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.ArrayList;
import java.util.List;

public class SimplePotoScoreboard implements PotoScoreboard {

    private final String title;
    private final List<ScoreboardLine> lines;

    private final Scoreboard scoreboard;

    private final Objective objective;
    private final String name;

    public SimplePotoScoreboard(String title, List<ScoreboardLine> lines, Scoreboard scoreboard, String name) {

        this.title=title;
        this.lines=lines;
        this.scoreboard=scoreboard;
        this.name=name;

        this.objective = scoreboard.registerNewObjective("sb", "dummy");
        setDefaults();
    }

    @Override
    public void setToPlayer(Player player) {
        player.setScoreboard(scoreboard);
    }

    @Override
    public void setDefaults() {

        objective.setDisplayName(title);
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        for (int i = 0 ; i < lines.size() ; i++) {

            Team team = scoreboard.registerNewTeam(lines.get(i).getName());

            team.addEntry(ChatColor.values()[i] + "");
            team.setPrefix(lines.get(i).getText());

            objective.getScore(ChatColor.values()[i] + "").setScore(lines.get(i).getScore());

        }
    }

    @Override
    public void update(Player player, boolean placeholderApi) {

        Scoreboard scoreboard = player.getScoreboard();

        if (!scoreboard.equals(this.scoreboard)) {
            return;
        }

        for (ScoreboardLine line : lines) {

            Team team = scoreboard.getTeam(line.getName());
            if (placeholderApi) {
                team.setPrefix(PlaceholderAPI.setPlaceholders(player, line.getText()));
            } else {
                team.setPrefix(line.getText());
            }
        }
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public List<ScoreboardLine> getLines() {
        return lines;
    }

    @Override
    public Scoreboard getLinkedScoreboard() {
        return scoreboard;
    }

    @Override
    public String getName() {
        return name;
    }

    public static class Builder implements PotoScoreboard.Builder {

        private String title;
        private final List<ScoreboardLine> lines = new ArrayList<>();
        private Scoreboard scoreboard;
        private String name;

        @Override
        public Builder name(String name) {
            this.name=name;
            return this;
        }

        @Override
        public Builder title(String title) {
            this.title=title;
            return this;
        }

        @Override
        public Builder addLine(ScoreboardLine line) {
            lines.add(line);
            return this;
        }

        @Override
        public Builder addLine(String name, String text, int score) {
            addLine(ScoreboardLine.Builder.create().name(name).text(text).score(score).build());
            return this;
        }

        @Override
        public Builder addLines(List<ScoreboardLine> lines) {
            for (ScoreboardLine line : lines) {
                addLine(line);
            }
            return this;
        }

        @Override
        public Builder scoreboard(Scoreboard scoreboard) {
            if (scoreboard==null) {
                this.scoreboard=Bukkit.getScoreboardManager().getNewScoreboard();
                return this;
            }
            this.scoreboard=scoreboard;
            return this;
        }

        @Override
        public PotoScoreboard build() {
            return new SimplePotoScoreboard(title, lines, scoreboard, name);
        }
    }
}
