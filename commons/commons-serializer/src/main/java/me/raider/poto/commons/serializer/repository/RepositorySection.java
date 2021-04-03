package me.raider.poto.commons.serializer.repository;

import java.util.List;

public interface RepositorySection {

    List<String> getPath();

    RepositoryPath getRepositoryPath();

    RepositorySection getRoot();

    RepositorySection getChild(String key);

}
