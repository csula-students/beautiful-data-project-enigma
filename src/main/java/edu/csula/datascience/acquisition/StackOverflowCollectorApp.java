package edu.csula.datascience.acquisition;

import java.io.File;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class StackOverflowCollectorApp {
	public static void main(String[] args) {
		// file path
		String filePath = "D:/csula/Assignments/Spring-BigData-CS594/Project/data-sets/stackoverflow/Posts.xml";

		try {
			/*
			 * SAX (the Simple API for XML) is an event-based parser for xml
			 * documents.Unlike a DOM parser, a SAX parser creates no parse
			 * tree. SAX is a streaming interface for XML, which means that
			 * applications using SAX receive event notifications about the XML
			 * document being processed an element, and attribute, at a time in
			 * sequential order starting at the top of the document, and ending
			 * with the closing of the ROOT element.
			 */
			File inputFile = new File(filePath);

			// Defines a factory API that enables applications to configure and
			// obtain a SAX based parser to parse XML documents.
			SAXParserFactory factory = SAXParserFactory.newInstance();// Obtain
																		// a new
																		// instance
																		// of a
																		// SAXParserFactory.

			SAXParser saxParser = factory.newSAXParser();

			StackOverflowSource userhandler = new StackOverflowSource();
			saxParser.parse(inputFile, userhandler);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
