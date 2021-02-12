package me.raider.poto.timer.cooldown;

import me.raider.poto.Nameable;
import me.raider.poto.timer.cooldown.user.CooldownHolder;

public interface Cooldown extends Nameable {

    CooldownMeta getCooldownMeta();

    void createCooldown(CooldownHolder cooldownUser);

    void addSeconds(CooldownHolder cooldownUser, int seconds);

    void removeSeconds(CooldownHolder cooldownUser, int seconds);

    void removeCooldown(CooldownHolder cooldownUser);

    boolean inCooldown(CooldownHolder cooldownUser);

}
