package me.raider.poto.timer.cooldown;

import me.raider.poto.timer.cooldown.user.CooldownUser;

public class CooldownMetaImpl implements CooldownMeta {

    private final String name;
    private final int seconds;
    private final boolean persistent;

    public CooldownMetaImpl(String name, int seconds, boolean persistent) {
        this.name = name;
        this.seconds = seconds;
        this.persistent = persistent;
    }

    @Override
    public void addCooldown(CooldownUser cooldownUser) {
        if (cooldownUser.getActualCooldown()!=null) {
            return;
        }
        cooldownUser.setActualCooldown(seconds*1000 + System.currentTimeMillis());
    }

    @Override
    public void addCooldownSec(CooldownUser cooldownUser, int seconds) {

        if (cooldownUser.getActualCooldown()!=null) {

            long millisSeconds = seconds*1000 + cooldownUser.getActualCooldown();
            cooldownUser.setActualCooldown(millisSeconds);

        }
    }

    @Override
    public void removeCooldownSec(CooldownUser cooldownUser, int seconds) {

        if (cooldownUser.getActualCooldown()==null) {
            return;
        }

        long millisSeconds = seconds*1000;
        long oldMillis = cooldownUser.getActualCooldown();

        if (oldMillis-(millisSeconds+System.currentTimeMillis())<0) {
            removeCooldown(cooldownUser);
            return;
        }

        cooldownUser.setActualCooldown(oldMillis-millisSeconds);
    }

    @Override
    public void removeCooldown(CooldownUser cooldownUser) {
        cooldownUser.setActualCooldown(null);
    }

    @Override
    public boolean inCooldown(CooldownUser cooldownUser) {

        if (cooldownUser.getActualCooldown()==null) {
            return false;
        }

        if (cooldownUser.getActualCooldown()<System.currentTimeMillis()) {
            removeCooldown(cooldownUser);
            return false;
        }

        return true;
    }

    @Override
    public boolean isPersistent() {
        return persistent;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getSeconds() {
        return seconds;
    }

    public static class Builder implements CooldownMeta.Builder {

        private String name;
        private boolean persistent;
        private int seconds;

        public Builder(String name) {
            this.name=name;
        }

        @Override
        public CooldownMeta.Builder name(String name) {
            this.name=name;
            return this;
        }

        @Override
        public CooldownMeta.Builder persistent(boolean persistent) {
            this.persistent=persistent;
            return this;
        }

        @Override
        public CooldownMeta.Builder defaultSeconds(int seconds) {
            this.seconds=seconds;
            return this;
        }

        @Override
        public CooldownMeta build() {
            return new CooldownMetaImpl(name, seconds, persistent);
        }
    }


}