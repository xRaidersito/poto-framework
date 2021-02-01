package me.raider.poto.arena;

public enum ArenaType {

    SOLO(1), DUO(2), TEAM(4);

    private final int teamSize;

    ArenaType(int teamSize) {
        this.teamSize=teamSize;
    }

    public int getTeamSize() {
        return teamSize;
    }
}
