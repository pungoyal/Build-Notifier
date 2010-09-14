package com.thoughtworks.buildnotifier.xml;

import com.thoughtworks.buildnotifier.model.domain.Pipelines;
import com.thoughtworks.buildnotifier.model.factories.PipelineFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class TrayFeedHandler extends DefaultHandler {
    private static final String PROJECT = "Project";
    private PipelineFactory factory;

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        factory = new PipelineFactory();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);

        if (localName.equalsIgnoreCase(PROJECT)) {
            factory.parseAndAdd(attributes);
        }
    }

    public Pipelines getResults() {
        return factory.getPipelines();
    }
}
