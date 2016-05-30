package edu.csula.datascience.acquisition;

import static org.elasticsearch.node.NodeBuilder.nodeBuilder;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.bson.Document;
import org.elasticsearch.action.bulk.BackoffPolicy;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.unit.ByteSizeUnit;
import org.elasticsearch.common.unit.ByteSizeValue;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.node.Node;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.google.gson.Gson;

public class StackOverflowSource extends DefaultHandler {
	// Initializing the database by calling the constructor
	private final static String indexName = "stack-data";
	private final static String typeName = "stack-posts";

	Node node = nodeBuilder()
			.settings(Settings.builder().put("cluster.name", "ayushtak").put("path.home", "elasticsearch-data")).node();
	Client client = node.client();
	// create bulk processor
	BulkProcessor bulkProcessor = BulkProcessor.builder(client, new BulkProcessor.Listener() {
		@Override
		public void beforeBulk(long executionId, BulkRequest request) {
		}

		@Override
		public void afterBulk(long executionId, BulkRequest request, BulkResponse response) {
		}

		@Override
		public void afterBulk(long executionId, BulkRequest request, Throwable failure) {
			System.out.println("Facing error while importing data to elastic search");
			failure.printStackTrace();
		}
	}).setBulkActions(10000).setBulkSize(new ByteSizeValue(1, ByteSizeUnit.GB))
			.setFlushInterval(TimeValue.timeValueSeconds(5)).setConcurrentRequests(1)
			.setBackoffPolicy(BackoffPolicy.exponentialBackoff(TimeValue.timeValueMillis(100), 3)).build();

	// Gson library for sending json to elastic search
	Gson gson = new Gson();

	StackOverflowCollector stackoverflowCollectorObject = new StackOverflowCollector();

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

		if (qName.equalsIgnoreCase("row")) {
			Integer Id = Integer.parseInt(attributes.getValue("Id"));
			String PostTypeId = attributes.getValue("PostTypeId");
			String AcceptedAnswerId = attributes.getValue("AcceptedAnswerId");
			String ExcerptPostId = attributes.getValue("ExcerptPostId");
			String CreationDate = attributes.getValue("CreationDate");
			String Score = attributes.getValue("Score");
			String ViewCount = attributes.getValue("ViewCount");
			String Body = attributes.getValue("Body");
			String OwnerUserId = attributes.getValue("OwnerUserId");
			String LastEditorUserId = attributes.getValue("LastEditorUserId");
			String LastEditorDisplayName = attributes.getValue("LastEditorDisplayName");
			String LastEditDate = attributes.getValue("LastEditDate");
			String LastActivityDate = attributes.getValue("LastActivityDate");
			String Title = attributes.getValue("Title");
			String Tags = attributes.getValue("Tags");
			String AnswerCount = attributes.getValue("AnswerCount");
			String CommentCount = attributes.getValue("CommentCount");
			String FavoriteCount = attributes.getValue("FavoriteCount");
			String CommunityOwnedDate = attributes.getValue("CommunityOwnedDate");

			// inserting the parsed data into the database
			try {

				/*
				 * if (!check) { tc.save(username, profileLocation, tweetId,
				 * content);
				 */
				System.out.println("SAVING!!!!!!!!");
				{
					StackOverflowData temp = new StackOverflowData(Id, PostTypeId, AcceptedAnswerId, ExcerptPostId,
							CreationDate, Score, ViewCount, Body, OwnerUserId, LastEditorUserId, LastEditorDisplayName,
							LastEditDate, LastActivityDate, Title, Tags, AnswerCount, CommentCount, FavoriteCount,
							CommunityOwnedDate);

					bulkProcessor.add(new IndexRequest(indexName, typeName).source(gson.toJson(temp)));
				}
				// }

			/*	org.bson.Document document = new Document();
				document.put("Id", Id);
				document.put("PostTypeId", PostTypeId);
				document.put("AcceptedAnswerId", AcceptedAnswerId);
				document.put("ExcerptPostId", ExcerptPostId);
				document.put("CreationDate", CreationDate);
				document.put("Score", Score);
				document.put("ViewCount", ViewCount);
				document.put("Body", Body);
				document.put("OwnerUserId", OwnerUserId);
				document.put("LastEditorUserId", LastEditorUserId);
				document.put("LastEditorDisplayName", LastEditorDisplayName);
				document.put("LastEditDate", LastEditDate);
				document.put("LastActivityDate", LastActivityDate);
				document.put("Title", Title);
				document.put("Tags", Tags);
				document.put("AnswerCount", AnswerCount);
				document.put("CommentCount", CommentCount);
				document.put("FavoriteCount", FavoriteCount);
				document.put("CommunityOwnedDate", CommunityOwnedDate);
				stackoverflowCollectorObject.collectionPosts.insertOne(document);*/
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (qName.equalsIgnoreCase("row")) {
			System.out.println("End Element :" + qName);
		}
	}

	static class StackOverflowData {
		Integer Id;
		String PostTypeId;
		String AcceptedAnswerId;
		String ExcerptPostId;
		String CreationDate;
		String Score;
		String ViewCount;
		String Body;
		String OwnerUserId;
		String LastEditorUserId;
		String LastEditorDisplayName;
		String LastEditDate;
		String LastActivityDate;
		String Title;
		String Tags;
		String AnswerCount;
		String CommentCount;
		String FavoriteCount;
		String CommunityOwnedDate;

		public StackOverflowData(Integer Id, String PostTypeId, String AcceptedAnswerId, String ExcerptPostId,
				String CreationDate, String Score, String ViewCount, String Body, String OwnerUserId,
				String LastEditorUserId, String LastEditorDisplayName, String LastEditDate, String LastActivityDate,
				String Title, String Tags, String AnswerCount, String CommentCount, String FavoriteCount,
				String CommunityOwnedDate) {
			// Date date = new Date();
			// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			Date myDate = new Date();
			this.Id = Id;
			this.PostTypeId = PostTypeId;
			this.AcceptedAnswerId = AcceptedAnswerId;
			this.ExcerptPostId = ExcerptPostId;
			this.CreationDate = CreationDate;
			this.Score = Score;
			this.ViewCount = ViewCount;
			this.Body = Body;
			this.OwnerUserId = OwnerUserId;
			this.LastEditorUserId = LastEditorUserId;
			this.LastEditorDisplayName = LastEditorDisplayName;
			this.LastEditDate = LastEditDate;
			this.LastActivityDate = LastActivityDate;
			this.Title = Title;
			this.Tags = Tags;
			this.AnswerCount = AnswerCount;
			this.CommentCount = CommentCount;
			this.FavoriteCount = FavoriteCount;
			this.CommunityOwnedDate = CommunityOwnedDate;
			// this.date = new SimpleDateFormat("yyyy-MM-dd").format(myDate);

			System.out.println("-------------");
			// System.out.println(date);

		}
	}
}
