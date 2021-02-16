package me.raider.poto.storage.types.database.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoDBDatabase {

    private final MongoDatabase mongoDatabase;
    private final MongoCollection mongoCollection;

    public MongoDBDatabase(String uri, String database, String collectionName) {

        MongoClient mongoClient = new MongoClient(new MongoClientURI(uri));

        mongoDatabase = mongoClient.getDatabase(database);
        mongoCollection = mongoDatabase.getCollection(collectionName);

    }

    public MongoCollection getMongoCollection() {
        return mongoCollection;
    }

    public MongoDatabase getMongoDatabase() {
        return mongoDatabase;
    }

    public boolean dataExist(Document document) {
        return document!=null;
    }

}
