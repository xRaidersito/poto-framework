package me.raider.poto.commons.serializer.repository;

public interface RepositorySection {

    String[] getPath();

    <T> T get(String key);

    <T> void set(String key, T instance);

    boolean contains(String key);

    RepositorySection getChildSection(String key, RepositorySection parent);



}
