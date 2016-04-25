package edu.csula.datascience.acquisition;
import org.bson.Document;


import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class StackOverflowCollector {
	MongoClient mongoClient;
	MongoDatabase database;
	MongoCollection<Document> collectionTags;
	MongoCollection<Document> collectionPosts;
	
	public StackOverflowCollector() {
		// establish database connection to MongoDB
		mongoClient = new MongoClient();

		// select `Enigma-database` as database
		database = mongoClient.getDatabase("Enigma-database");

		// select collection by name `Tags`
		collectionTags = database.getCollection("Tags");
		// collection for posts
		collectionPosts = database.getCollection("Posts");
	}

	
	}
