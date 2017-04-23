package org.javacream.training.java.experts.xml.dom;

import org.javacream.training.java.experts.xml.Book;
import org.javacream.training.java.experts.xml.Publisher;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

public class DomUtil {
	
	public static void print(Node node, int depth) {
		for (int i = 0; i < depth; i++)
			System.out.print("\t");
		if (node instanceof Document)
			System.out.println("Document");
		else if (node instanceof Element) {
			System.out.print("Element: " + node.getNodeName() + " ");
			for (int i = 0; i < node.getAttributes().getLength(); i++)
				System.out.print(node.getAttributes().item(i) + " ");
			System.out.println();
		} else if (node instanceof Text)
			System.out.println("Text: " + node.getNodeValue());
		else
			System.out.println("Other: " + node.getClass().getSimpleName());
		NodeList children = node.getChildNodes();
		for (int i = 0; i < children.getLength(); i++) {
			Node child = children.item(i);
			print(child, depth + 1);
		}
	}

	public static Publisher buildPublisher(Document doc) {
		Element root = doc.getDocumentElement();
		String city = root.getAttribute("city");
		Publisher publisher = new Publisher(city);
		NodeList books = root.getElementsByTagName("book");
		for (int i = 0; i < books.getLength(); i++) {
			Element book = (Element)books.item(i);
			String isbn = book.getElementsByTagName("isbn").item(0).getTextContent();
			String title = book.getElementsByTagName("title").item(0).getTextContent();
			double price = Double.parseDouble(book.getElementsByTagName("price").item(0).getTextContent());
			publisher.add(new Book(isbn, title, price));
		}
		return publisher;
	}

}
