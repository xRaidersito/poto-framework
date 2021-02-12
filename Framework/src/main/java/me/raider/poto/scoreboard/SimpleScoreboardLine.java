package me.raider.poto.scoreboard;

public class SimpleScoreboardLine implements ScoreboardLine {

    private final String text;
    private final int score;

    public SimpleScoreboardLine(String text, int score) {
        this.text=text;
        this.score=score;
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public String getText() {
        return text;
    }

    public static class Builder implements ScoreboardLine.Builder {

        private String text;
        private int score;

        @Override
        public Builder text(String text) {
            this.text=text;
            return this;
        }

        @Override
        public Builder score(int score) {
            this.score=score;
            return this;
        }

        @Override
        public ScoreboardLine build() {
            return new SimpleScoreboardLine(text, score);
        }
    }
}
