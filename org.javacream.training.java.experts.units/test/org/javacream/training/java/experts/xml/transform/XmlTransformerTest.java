package org.javacream.training.java.experts.xml.transform;

import java.io.File;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;

public class XmlTransformerTest {
	
	private String xmlFilename = "../publisher.xml";
	private String xslFilename = "../publisher_html.xsl";
	private String systemId = "file:test/org/javacream/training/java/experts/xml/publisher.dtd";
	private InputStream xmlInputStream;
	private InputStream xslInputStream;

	@Before
	public void init() {
		xmlInputStream = getClass().getResourceAsStream(xmlFilename);
		xslInputStream = getClass().getResourceAsStream(xslFilename);

	}


	@Test
	public void testXslTransformer() throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(xmlInputStream, systemId);
		TransformerFactory tFactory = TransformerFactory.newInstance();
		Transformer transformer = tFactory.newTransformer(new StreamSource(xslInputStream));
		DOMSource source = new DOMSource(document);
		StreamResult result = new StreamResult(System.out);
		StreamResult targetHtmlFile = new StreamResult(new File("target/publishing.html"));
		transformer.transform(source, result);
		transformer.transform(source, targetHtmlFile);

	}
}
