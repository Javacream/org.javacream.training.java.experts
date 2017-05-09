package org.javacream.training.java.experts.xml;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;

import org.javacream.training.java.experts.xml.dom.DomUtil;
import org.javacream.training.java.experts.xml.sax.PublisherBuilderHandler;
import org.javacream.training.java.experts.xml.sax.SaxPrintHandler;
import org.javacream.training.java.experts.xml.stax.StaxUtil;
import org.javacream.training.java.experts.xml.stax.XScanner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.helpers.DefaultHandler;

public class XmlBasicsTest {
	private String filename = "publisher.xml";
	private String systemId = "file:test/org/javacream/training/java/experts/xml/publisher.xsd";
	private InputStream in;

	@Before
	public void init() {
		in = getClass().getResourceAsStream(filename);

	}

	@Test
	public void testSax() throws Exception {

		DefaultHandler handler = new SaxPrintHandler();

		InputStream in = getClass().getResourceAsStream(filename);
		SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
		// parser.setProperty("http://xml.org/sax/features/validation", true);
		System.out.println("start testSax: ##############################");
		parser.parse(in, handler, systemId);
		System.out.println("stop testSax: ##############################");

	}

	@Test
	public void testSaxPublisherBuilder() throws Exception {
		PublisherBuilderHandler handler = new PublisherBuilderHandler();
		InputStream in = getClass().getResourceAsStream(filename);
		SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
		parser.parse(in, handler, systemId);
		Publisher publisher = handler.getPublisher();
		System.out.println("start testSaxPublisherBuilder: ##############################");
		System.out.println(publisher);
		System.out.println("stop testSaxPublisherBuilder: ##############################");

	}

	@Test
	public void testDom() throws Exception {
		DocumentBuilder parser = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document doc = parser.parse(in, systemId);
		System.out.println("start testDom: ##############################");
		DomUtil.print(doc, 0);
		System.out.println("stop testDom: ##############################");

	}

	@Test
	public void testDomPublisher() throws Exception {
		DocumentBuilder parser = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document doc = parser.parse(in, systemId);
		System.out.println("start testDomPublisher: ##############################");
		System.out.println(DomUtil.buildPublisher(doc));
		System.out.println("stop testDomPublisher: ##############################");

	}

	@Test
	public void testStax() throws Exception {
		XMLInputFactory factory = XMLInputFactory.newInstance();
		XMLStreamReader reader = factory.createXMLStreamReader(in);
		System.out.println("start testStax: ##############################");
		StaxUtil.print(reader);
		System.out.println("stop testStax: ##############################");

	}
	
	@Test public void testStaxPublisher() throws Exception{
		XMLInputFactory factory = XMLInputFactory.newInstance();
		XMLStreamReader reader = factory.createXMLStreamReader(in);
		XScanner scanner = new XScanner(reader);
		Assert.assertTrue(scanner.isStart("publisher"));
		Publisher Publisher = new Publisher(scanner.getAttributes().get("city"));
		scanner.start("publisher"); 
		while (scanner.isStart("book")) {
			scanner.start("book");
			String isbn = scanner.startTextEnd("isbn");
			String title = scanner.startTextEnd("title");
			double price = Double.parseDouble(scanner.startTextEnd("price"));
			Book book = new Book(isbn, title, price);
			Publisher.add(book);
			scanner.end("book");
		}
//		scanner.end("publisher");
		System.out.println("start testStaxPublisher: ##############################");
		System.out.println(Publisher);
		System.out.println("stop testStaxPublisher: ##############################");

	}

}
