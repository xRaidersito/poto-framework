package me.raider.poto.timer.countdown;

import me.raider.poto.timer.Timeable;
import org.bukkit.plugin.Plugin;

import java.util.function.Consumer;
import java.util.function.Predicate;

public interface Countdown extends Timeable, Runnable {

    int getTaskId();

    int getSecondsLeft();

    void start();

    void cancel();

    interface Builder {

        Builder seconds(int seconds);

        Builder filter(Predicate<Countdown> predicate);

        Builder start(Consumer<Countdown> consumer);

        Builder everySecond(Consumer<Countdown> consumer);

        Builder finish(Consumer<Countdown> consumer);

        Builder onCancel(Consumer<Countdown> consumer);

        Countdown build();

        static Builder create(Plugin plugin) {
            return new CountdownImpl.Builder(plugin);
        }

    }


}
