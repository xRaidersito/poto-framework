package me.raider.poto.timer.countdown;

import me.raider.poto.timer.Timeable;

import java.util.function.Consumer;
import java.util.function.Predicate;

public interface Countdown extends Timeable, Runnable {

    int getTaskId();




    void start();

    interface Builder {

        Builder seconds(int seconds);

        Builder filter(Predicate<Countdown> predicate);

        Builder start(Consumer<Countdown> consumer);

        Builder everySecond(Consumer<Countdown> consumer);

        Builder finish(Consumer<Countdown> consumer);

        Countdown build();

    }


}
