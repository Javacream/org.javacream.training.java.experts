package org.javacream.training.java.experts.xml.serialization;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

import org.javacream.training.java.experts.xml.Book;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class XmlSerializationTest {
	private static final String filename = "target/books.xml";
	private static final String filenameCircular = "target/circular.xml";
	@Test
	public void a_writeToXml() throws Exception {
		ArrayList<Book> books = new ArrayList<>();
		Book book = new Book("ISBN1", "Java", 19.99);
		books.add(book);
		book = new Book("ISBN2", "Xml", 9.99);
		books.add(book);
		book = new Book("ISBN1", "Spring", 25.99);
		books.add(book);
		XMLEncoder encoder = new XMLEncoder(new FileOutputStream(filename));
		encoder.writeObject(books);
		encoder.close();
	}

	@Test
	public void b_readFromToXml() throws Exception {
		XMLDecoder decoder = new XMLDecoder(new FileInputStream(filename));
		@SuppressWarnings("unchecked")
		ArrayList<Book> books = (ArrayList<Book>) decoder.readObject();
		decoder.close();
		System.out.println(books);
		
	}

	@Test
	public void circular() throws Exception {
		XMLEncoder encoder = new XMLEncoder(new FileOutputStream(filenameCircular));
		Class1 class1 = new Class1();
		class1.setDescription("class1 description");
		Class2 class2 = new Class2();
		class2.setDescription("class2 description");
		class1.setClass2(class2);
		class2.setClass1(class1);
		
		encoder.writeObject(class1);
		encoder.close();
		XMLDecoder decoder = new XMLDecoder(new FileInputStream(filenameCircular));
		System.out.println(decoder.readObject());
		decoder.close();
		
		
	}

}
