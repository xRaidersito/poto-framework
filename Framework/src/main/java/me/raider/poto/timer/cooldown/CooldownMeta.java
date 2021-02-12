package me.raider.poto.timer.cooldown;

import me.raider.poto.Nameable;
import me.raider.poto.timer.Timeable;
import me.raider.poto.timer.cooldown.user.CooldownHolder;

public interface CooldownMeta extends Timeable, Nameable {

    void addCooldown(CooldownHolder cooldownUser);

    void addCooldownSec(CooldownHolder cooldownUser, int seconds);

    void removeCooldownSec(CooldownHolder cooldownUser, int seconds);

    void removeCooldown(CooldownHolder cooldownUser);

    boolean inCooldown(CooldownHolder cooldownUser);

    boolean isPersistent();

    interface Builder {

        Builder name(String name);

        Builder persistent(boolean persistent);

        Builder defaultSeconds(int seconds);

        static Builder create(String name) {
            return new CooldownMetaImpl.Builder(name);
        }

        CooldownMeta build();

    }


}
