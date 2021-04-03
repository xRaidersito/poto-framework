package me.raider.plib.commons.serializer.repository;

public interface RepositoryPath<R> {

    <T> T get(String key);

    <T> void set(String key, T instance);

    boolean contains(String key);

    RepositorySection<R> getSection();

}
