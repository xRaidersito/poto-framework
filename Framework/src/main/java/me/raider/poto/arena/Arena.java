package me.raider.poto.arena;

import me.raider.poto.storage.types.Storable;

public interface Arena extends Storable {

    int getMinPlayers();

    void setMinPlayers(int players);




}
