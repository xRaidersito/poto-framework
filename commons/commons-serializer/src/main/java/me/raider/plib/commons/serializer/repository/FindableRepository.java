package me.raider.plib.commons.serializer.repository;

public interface FindableRepository<R> {

    RepositorySection<R> find(String key);

}
