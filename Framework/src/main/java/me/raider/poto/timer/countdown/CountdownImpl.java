package me.raider.poto.timer.countdown;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class CountdownImpl implements Countdown {
    @Override
    public int getTaskId() {
        return 0;
    }

    @Override
    public int getSecondsLeft() {
        return 0;
    }

    @Override
    public void start() {

    }

    @Override
    public void run() {

    }

    @Override
    public int getSeconds() {
        return 0;
    }

    public static class Builder implements Countdown.Builder {


        @Override
        public Countdown.Builder seconds(int seconds) {
            return null;
        }

        @Override
        public Countdown.Builder filter(Predicate<Countdown> predicate) {
            return null;
        }

        @Override
        public Countdown.Builder start(Consumer<Countdown> consumer) {
            return null;
        }

        @Override
        public Countdown.Builder everySecond(Consumer<Countdown> consumer) {
            return null;
        }

        @Override
        public Countdown.Builder finish(Consumer<Countdown> consumer) {
            return null;
        }

        @Override
        public Countdown build() {
            return null;
        }
    }
}
