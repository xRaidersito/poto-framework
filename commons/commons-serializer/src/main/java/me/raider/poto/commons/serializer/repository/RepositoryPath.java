package me.raider.poto.commons.serializer.repository;

public interface RepositoryPath {

    <T> T get(String key);

    <T> void set(String key, T instance);

    boolean contains(String key);

}
