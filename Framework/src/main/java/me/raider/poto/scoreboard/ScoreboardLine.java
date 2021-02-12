package me.raider.poto.scoreboard;

public interface ScoreboardLine {

    int getScore();

    String getText();

    interface Builder {

        Builder text(String text);

        Builder score(int score);

        ScoreboardLine build();

        static Builder create() {
            return new SimpleScoreboardLine.Builder();
        }

    }

}
