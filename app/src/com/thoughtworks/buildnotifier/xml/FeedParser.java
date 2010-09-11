package com.thoughtworks.buildnotifier.xml;

import java.io.Reader;
import java.io.StringReader;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import com.thoughtworks.buildnotifier.domain.Pipelines;
import com.thoughtworks.buildnotifier.exceptions.XMLParseException;

public class FeedParser {
	public Pipelines parse(String cctray) throws XMLParseException {
		Pipelines pipelines = new Pipelines();
		try {
			Reader reader = new StringReader(cctray);
			InputSource inputSource = new InputSource(reader);
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser parser = factory.newSAXParser();
			XMLReader xmlreader = parser.getXMLReader();

			TrayFeedHandler feedHandler = new TrayFeedHandler();
			xmlreader.setContentHandler(feedHandler);
			xmlreader.parse(inputSource);
			pipelines = feedHandler.getResults();
		} catch (Exception e) {
			throw new XMLParseException(e, cctray);
		}

		return pipelines;
	}
}
