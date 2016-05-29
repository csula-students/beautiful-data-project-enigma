package edu.csula.datascience.acquisition;

import org.bson.Document;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class StackOverflowSource extends DefaultHandler {
	// Initializing the database by calling the constructor

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
				org.bson.Document document = new Document();
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
				stackoverflowCollectorObject.collectionPosts.insertOne(document);
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
}
