package me.raider.poto.commons.serializer;

import me.raider.poto.commons.serializer.repository.FindableRepository;
import me.raider.poto.commons.serializer.repository.RepositorySection;

public class SimpleSerializer<T> implements Serializer<T> {

    private final FindableRepository findableRepository;
    private final SerializerManager manager;

    public SimpleSerializer(FindableRepository findableRepository, SerializerManager manager) {
        this.findableRepository = findableRepository;
        this.manager = manager;
    }

    @Override
    public SerializedObject<T> serialize(T instance, String key) {

        RepositorySection section = findableRepository.find(key);

        if (section==null) {
            return null;
        }

        return null;
    }

    @Override
    public SerializedObject<T> deserialize(Class<T> clazz, String key) {
        return null;
    }
}
