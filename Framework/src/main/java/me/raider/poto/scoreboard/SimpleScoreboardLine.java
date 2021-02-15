package me.raider.poto.scoreboard;

public class SimpleScoreboardLine implements ScoreboardLine {

    private final String text;
    private final int score;
    private final String name;

    public SimpleScoreboardLine(String text, int score, String name) {
        this.text=text;
        this.score=score;
        this.name=name;
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public String getName() {
        return name;
    }

    public static class Builder implements ScoreboardLine.Builder {

        private String text;
        private int score;
        private String name;

        @Override
        public Builder name(String name) {
            this.name=name;
            return this;
        }

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
            return new SimpleScoreboardLine(text, score, name);
        }
    }
}
