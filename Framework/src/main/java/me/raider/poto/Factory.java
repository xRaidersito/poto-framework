package me.raider.poto;

import java.util.Map;

public interface Factory<T extends Nameable> {

    /**
     * Create a new instance of the provided generic with the {@link Map} data.
     *
     * @param serializeMap A map including all the necessary data for the creation of the object.
     * @return A new instance of the provided generic.
     */
    T create(Map<String, Object> serializeMap);

}
