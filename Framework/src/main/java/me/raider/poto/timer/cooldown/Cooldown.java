package me.raider.poto.timer.cooldown;

import me.raider.poto.Nameable;
import me.raider.poto.timer.cooldown.user.CooldownUser;

public interface Cooldown extends Nameable {

    CooldownMeta getCooldownMeta();

    void createCooldown(CooldownUser cooldownUser);

    void addSeconds(CooldownUser cooldownUser, int seconds);

    void removeSeconds(CooldownUser cooldownUser, int seconds);

    void removeCooldown(CooldownUser cooldownUser);

    boolean inCooldown(CooldownUser cooldownUser);

}
