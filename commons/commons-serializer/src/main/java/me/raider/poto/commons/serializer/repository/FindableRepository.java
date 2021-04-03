package me.raider.poto.commons.serializer.repository;

public interface FindableRepository<R> {

    RepositorySection<R> find(String key);

}
