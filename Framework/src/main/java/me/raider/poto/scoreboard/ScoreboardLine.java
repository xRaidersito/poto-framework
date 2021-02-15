package me.raider.poto.scoreboard;

public interface ScoreboardLine {

    int getScore();

    String getText();

    String getName();

    interface Builder {

        Builder name(String name);

        Builder text(String text);

        Builder score(int score);

        ScoreboardLine build();

        static Builder create() {
            return new SimpleScoreboardLine.Builder();
        }

    }

}
