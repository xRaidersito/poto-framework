package me.raider.poto.storage.types.database.mongodb;

import com.google.common.util.concurrent.ListeningExecutorService;
import me.raider.poto.serializer.Serializer;
import me.raider.poto.storage.AbstractStorage;
import me.raider.poto.storage.StorageType;
import me.raider.poto.storage.types.Storable;

public class MongoDBStorage<T extends Storable> extends AbstractStorage<T> {

    public MongoDBStorage(String name, StorageType type, Serializer<T> serializer, ListeningExecutorService executorService) {
        super(name, type, serializer, executorService);
    }

    @Override
    public T load(String key) {
        return null;
    }

    @Override
    public void save(String key) {

    }

    @Override
    public T createIfAbsent(String key) {
        return null;
    }
}
