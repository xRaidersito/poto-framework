package me.raider.poto.storage;

import me.raider.poto.Storable;

import java.util.Map;

public interface Storage<T extends Storable> {

    Map<String, T> get();

}
