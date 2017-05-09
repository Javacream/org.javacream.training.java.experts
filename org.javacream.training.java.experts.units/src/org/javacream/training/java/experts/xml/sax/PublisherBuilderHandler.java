package org.javacream.training.java.experts.xml.sax;

import org.javacream.training.java.experts.xml.Book;
import org.javacream.training.java.experts.xml.Publisher;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class PublisherBuilderHandler extends DefaultHandler {

	private String pcdata;
	private Publisher publisher;
	private Book book;

	public Publisher getPublisher() {
		return this.publisher;
	}

	public void startElement(String namespaceURI, String localName,
			String rawName, Attributes attributes) throws SAXException {

		if (rawName.equals("publisher")) {
			String city = attributes.getValue("city");
			this.publisher = new Publisher(city);
		}
		else if (rawName.equals("book")) {
			this.publisher.add(this.book = new Book());
		}
	}

	public void endElement(String namespaceURI, String localName, String rawName)
			throws SAXException {
		if (rawName.equals("isbn"))
			this.book.setIsbn(this.pcdata);
		else if (rawName.equals("title"))
			this.book.setTitle(this.pcdata);
		else if (rawName.equals("price"))
			this.book.setPrice(Double.parseDouble(this.pcdata));
	}

	public void characters(char[] ch, int start, int end) throws SAXException {
		this.pcdata = new String(ch, start, end);
	}
	
}
