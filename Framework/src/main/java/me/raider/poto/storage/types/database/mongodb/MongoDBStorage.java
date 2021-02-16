package me.raider.poto.storage.types.database.mongodb;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.mongodb.client.model.UpdateOptions;
import me.raider.poto.serializer.SerializedObject;
import me.raider.poto.serializer.Serializer;
import me.raider.poto.storage.AbstractStorage;
import me.raider.poto.storage.StorageType;
import me.raider.poto.storage.types.Storable;
import org.bson.Document;

import java.util.HashMap;
import java.util.Map;

public abstract class MongoDBStorage<T extends Storable> extends AbstractStorage<T> {

    private final Class<? extends T> clazz;
    private final MongoDBDatabase database;

    public MongoDBStorage(String name, Serializer<T> serializer, ListeningExecutorService executorService, Class<? extends T> clazz, MongoDBDatabase database) {
        super(name, StorageType.MONGODB, serializer, executorService);
        this.clazz=clazz;
        this.database=database;
    }

    @Override
    public T load(String key) {

        String[] paths = getSerializer().getAllAnnotatedFields(clazz).getOrganizedPaths();

        Document document = new Document().append(paths[0], key);
        Document objectDoc = (Document) database.getMongoCollection().find(document).first();

        if (!database.dataExist(objectDoc)) {
            T newObject = createIfAbsent(key);
            get().put(key, newObject);
            return newObject;
        }

        Map<String, Object> dataMap = new HashMap<>();

        for (String path : paths) {
            dataMap.put(path, objectDoc.get(path));
        }

        SerializedObject<T> serializedObject = getSerializer().deserialize(dataMap);
        T object = serializedObject.createWithData();

        get().put(key, object);

        return object;
    }

    @Override
    public void save(String key) {

        T toSerialize = get().get(key);

        if (toSerialize==null) {
            return;
        }

        Map<String, Object> serializeMap = getSerializer().serialize(toSerialize).getLinkedMap();

        String[] paths = getSerializer().getAllAnnotatedFields(clazz).getOrganizedPaths();

        Document document = new Document().append(paths[0], key);
        Document oldDoc = (Document) database.getMongoCollection().find(document).first();

        if (database.dataExist(oldDoc)) {

            Document newDoc = new Document("$set", serializeMap);
            database.getMongoCollection().updateOne(oldDoc, newDoc, new UpdateOptions().upsert(true));

        } else {
            Document insertDoc = new Document(serializeMap);
            database.getMongoCollection().insertOne(insertDoc);
        }

        get().remove(key);
    }
}
