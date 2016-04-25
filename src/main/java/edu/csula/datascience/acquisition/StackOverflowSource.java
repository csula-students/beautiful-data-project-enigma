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
			Integer id = Integer.parseInt(attributes.getValue("Id"));
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
				document.put("Id", id);
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
	/*
	 * public static void tagsCollector() { // Initializing the database by
	 * calling the constructor StackOverflowCollector
	 * stackoverflowCollectorObject = new StackOverflowCollector(); String
	 * filePath =
	 * "D:/csula/Assignments/Spring-BigData-CS594/Project/data-sets/stackoverflow/stackoverflow.com-Tags/Tags.xml";
	 * try { File inputFile = new File(filePath);
	 * 
	 * DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	 * DocumentBuilder dBuilder = dbFactory.newDocumentBuilder(); // Parse the
	 * file (using DOM) org.w3c.dom.Document doc = dBuilder.parse(inputFile);
	 * 
	 * doc.getDocumentElement().normalize(); System.out.println("Root element :"
	 * + doc.getDocumentElement().getNodeName()); NodeList nList =
	 * doc.getElementsByTagName("row");
	 * System.out.println("----------------------------");
	 * 
	 * for (int temp = 0; temp < nList.getLength(); temp++) { Node nNode =
	 * nList.item(temp); System.out.println("\nCurrent Element :" +
	 * nNode.getNodeName()); if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	 * Element eElement = (Element) nNode; Integer id =
	 * Integer.parseInt(eElement.getAttribute("Id")); String TagName =
	 * eElement.getAttribute("TagName"); String Count =
	 * eElement.getAttribute("Count"); String ExcerptPostId =
	 * eElement.getAttribute("ExcerptPostId"); String WikiPostId =
	 * eElement.getAttribute("WikiPostId"); // inserting the parsed data into
	 * the database org.bson.Document document = new Document();
	 * document.put("Id", id); document.put("TagName", TagName);
	 * document.put("Count", Count); document.put("ExcerptPostId",
	 * ExcerptPostId); document.put("WikiPostId", WikiPostId);
	 * stackoverflowCollectorObject.collectionTags.insertOne(document); } } }
	 * catch (Exception e) { e.printStackTrace(); }
	 * 
	 * }
	 * 
	 */}
