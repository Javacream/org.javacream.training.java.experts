package org.javacream.training.java.experts.xml.sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxPrintHandler extends DefaultHandler {

	private int depth = -1;

	static private void printTabs(int depth) {
		while (depth-- > 0)
			System.out.print("    ");
	}

	@Override
	public void startDocument() throws SAXException {
		System.out.println("startDocument");
	}

	@Override
	public void endDocument() throws SAXException {
		System.out.println("endDocument");
	}

	@Override
	public void startElement(String namespaceURI, String localName,
			String rawName, Attributes attributes) throws SAXException {
		this.depth++;
		printTabs(this.depth);
		System.out.println(" --> " + rawName);
		for (int i = 0; i < attributes.getLength(); i++) {
			printTabs(this.depth);
			System.out.println("     " + attributes.getLocalName(i) + " = " + attributes.getValue(i));
		}
	}

	@Override
	public void endElement(String namespaceURI, String localName, String rawName)
			throws SAXException {
		printTabs(this.depth);
		this.depth--;
		System.out.println(" <-- " + rawName);
	}

	@Override
	public void characters(char[] ch, int start, int end) throws SAXException {
		String s = new String(ch, start, end);
		printTabs(this.depth + 1);
		System.out.println(s);
	}
}
