package com.swaroopr.mongo.driver;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;

import static com.mongodb.client.model.Filters.*;

public class DriverMain {

	public static void main(String[] args) {
		try (MongoClient mongoClient = new MongoClient()) {
			MongoDatabase database = mongoClient.getDatabase("models");
			MongoCollection<Document> collection = database
					.getCollection("test");
			Document doc = new Document("name", "MongoDB")
					.append("type", "database").append("count", 1)
					.append("info", new Document("x", 203).append("y", 102));
			collection.insertOne(doc);
			List<Document> documents = new ArrayList<Document>();
			for (int i = 0; i < 10; i++) {
				documents.add(new Document("i", i));
			}
			collection.insertMany(documents);
			System.out.println(String.format("Collection count; %d",
					collection.count()));
			// query first
			Document myDoc = collection.find().first();
			System.out.println(myDoc.toJson());
			// find all documents in a collection
			System.out.println("Printing all documents in collection.");
			for (Document cur : collection.find()) {
				System.out.println(cur.toJson());
			}
			myDoc = collection.find(eq("i", 5)).first();
			System.out.println("Testing find - print documents gt 7.");
			System.out.println(myDoc.toJson());
			Block<Document> printBlock = new Block<Document>() {
				@Override
				public void apply(final Document document) {
					System.out.println(document.toJson());
				}
			};
			collection.find(gt("i", 7)).forEach(printBlock);
			System.out
					.println("Testing find - print documents between 4 and 8.");
			collection.find(and(gt("i", 4), lte("i", 8))).forEach(printBlock);
			System.out.println("Testing delete - doc 5.");
			collection.deleteOne(eq("i", 5));
			System.out.println("Printing collection count.");
			System.out.println(collection.count());
			DeleteResult deleteResult = collection.deleteMany(lte("i", 100));
			System.out.println(String.format("Delete count; %d",
					deleteResult.getDeletedCount()));
			System.out.println(String.format("Collection count; %d",
					collection.count()));
			for (Document cur : collection.find()) {
				System.out.println(cur.toJson());
			}
			collection.deleteMany(where("true"));
			System.out.println(String.format("Collection count; %d",
					collection.count()));
		}
	}
}
