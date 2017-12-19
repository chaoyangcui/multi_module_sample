package com.sssarm.util;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.sssarm.model.RoomUserInfo;
import org.bson.Document;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Created by cuiguiyang on 2017/2/7 22:18.
 * Desc
 */
public class MongoUtil {

    private static final String MONGO_HOST = "localhost:27017";
    private static final String DB_NAME = "sssarm";
    private static final String ROOM_USER_COLLECTION = "room_user_info";
    private static final String ROOM_USER_DEFORMITY_COLLECTION = "room_user_info_deformity";
    private static final String DEFAULT_COLLECTION = "room_user_info";

    private static MongoDatabase mongoDatabase;
    static {
        MongoClient client = new MongoClient(MONGO_HOST);
        mongoDatabase = client.getDatabase(DB_NAME);
    }

    public static void main(String[] args) {
        connection();
    }

    private static void connection() {
        MongoCollection<Document> collection = mongoDatabase.getCollection(ROOM_USER_COLLECTION);
        if (collection == null) {
            mongoDatabase.createCollection(ROOM_USER_COLLECTION);
            collection = mongoDatabase.getCollection(ROOM_USER_COLLECTION);
        }
        collection.insertOne(new Document().append("_id", "first"));
    }

    static void insertOne(String collection, Document document) {
        MongoCollection<Document> mongoCollection = mongoDatabase.getCollection(collection);
        Assert.notNull(mongoCollection, String.format("can not find current collection[%s]", collection));
        mongoCollection.insertOne(document);
    }

    public static void insertMany(String collection, List<Document> documents) {
        MongoCollection<Document> mongoCollection = mongoDatabase.getCollection(collection);
        Assert.notNull(mongoCollection, String.format("can not find current collection[%s]", collection));
        mongoCollection.insertMany(documents);
    }

    static void insertOne(Document document) {
        MongoCollection<Document> mongoCollection = mongoDatabase.getCollection(DEFAULT_COLLECTION);
        Assert.notNull(mongoCollection, String.format("can not find current collection[%s]", DEFAULT_COLLECTION));
        mongoCollection.insertOne(document);
    }

    public static void insertMany(List<Document> documents) {
        MongoCollection<Document> mongoCollection = mongoDatabase.getCollection(DEFAULT_COLLECTION);
        Assert.notNull(mongoCollection, String.format("can not find current collection[%s]", DEFAULT_COLLECTION));
        mongoCollection.insertMany(documents);
    }

}
