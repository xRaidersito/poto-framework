package me.raider.poto.timer.countdown;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class CountdownImpl implements Countdown {

    private final Plugin plugin;
    private final int seconds;
    private final List<Predicate<Countdown>> predicates;
    private final Consumer<Countdown> start;
    private final Consumer<Countdown> everySecond;
    private final Consumer<Countdown> finish;
    private Consumer<Countdown> cancel;
    private int secondsLeft;

    private int taskId;

    public CountdownImpl(Plugin plugin, int seconds, List<Predicate<Countdown>> predicates, Consumer<Countdown> start, Consumer<Countdown> everySecond, Consumer<Countdown> finish, Consumer<Countdown> cancel) {
        this.plugin = plugin;
        this.seconds = seconds;
        this.predicates = predicates;
        this.start = start;
        this.everySecond = everySecond;
        this.finish = finish;
        this.secondsLeft=seconds;
        this.cancel=cancel;
    }

    @Override
    public int getTaskId() {
        return taskId;
    }

    @Override
    public int getSecondsLeft() {
        return secondsLeft;
    }

    @Override
    public void start() {
        taskId= Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, this, 0L, 20L);
    }

    @Override
    public void cancel() {
        Bukkit.getScheduler().cancelTask(taskId);
    }

    @Override
    public void run() {

        if (secondsLeft < 1) {

            finish.accept(this);
            cancel();
            return;

        }

        for (Predicate<Countdown> predicate : predicates) {
            if (!predicate.test(this)) {
                cancel.accept(this);
                cancel();
                return;
            }
        }

        if (secondsLeft==seconds) {
            start.accept(this);
        }

        everySecond.accept(this);
        secondsLeft--;

    }

    @Override
    public int getSeconds() {
        return seconds;
    }

    public static class Builder implements Countdown.Builder {

        private final Plugin plugin;
        private int seconds;
        private final List<Predicate<Countdown>> predicates = new ArrayList<>();
        private Consumer<Countdown> start;
        private Consumer<Countdown> everySecond;
        private Consumer<Countdown> finish;
        private Consumer<Countdown> cancel;

        public Builder(Plugin plugin) {
            this.plugin=plugin;
        }

        @Override
        public Builder seconds(int seconds) {
            this.seconds=seconds;
            return this;
        }

        @Override
        public Builder filter(Predicate<Countdown> predicate) {
            predicates.add(predicate);
            return this;
        }

        @Override
        public Builder start(Consumer<Countdown> consumer) {
            this.start=consumer;
            return this;
        }

        @Override
        public Builder everySecond(Consumer<Countdown> consumer) {
            this.everySecond=consumer;
            return this;
        }

        @Override
        public Builder finish(Consumer<Countdown> consumer) {
            this.finish=consumer;
            return this;
        }

        @Override
        public Countdown.Builder onCancel(Consumer<Countdown> consumer) {
            this.cancel=consumer;
            return this;
        }

        @Override
        public Countdown build() {
            return new CountdownImpl(plugin, seconds, predicates, start, everySecond, finish, cancel);
        }
    }
}
