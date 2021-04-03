package me.raider.poto.commons.serializer.repository;

import java.util.List;

public interface RepositorySection<R> {

    List<String> getPath();

    RepositorySection<R> getRoot();

    RepositorySection<R> getChild(String key);

    RepositoryPath<R> getRepositoryPath();

    void setRepositoryPath(RepositoryPath<R> repositoryPath);

    R getRepository();

}
