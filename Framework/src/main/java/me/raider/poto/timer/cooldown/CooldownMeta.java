package me.raider.poto.timer.cooldown;

import me.raider.poto.Nameable;
import me.raider.poto.timer.Timeable;
import me.raider.poto.timer.cooldown.user.CooldownUser;

public interface CooldownMeta extends Timeable, Nameable {

    void addCooldown(CooldownUser cooldownUser);

    void addCooldownSec(CooldownUser cooldownUser, int seconds);

    void removeCooldownSec(CooldownUser cooldownUser, int seconds);

    void removeCooldown(CooldownUser cooldownUser);

    boolean inCooldown(CooldownUser cooldownUser);

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
