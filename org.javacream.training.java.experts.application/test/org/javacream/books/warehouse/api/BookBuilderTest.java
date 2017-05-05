package org.javacream.books.warehouse.api;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class BookBuilderTest {

	private BookBuilder bookBuilder;
	@Before public void setUp(){
		bookBuilder = new BookBuilder();
	}
	@Test public void testBookBuilderComic() throws BookException{
		HashMap<String, Object> options = new HashMap<>();
		options.put("hero", "Obelix");
		Book book = bookBuilder.setType("comic").setIsbn("ISBN1").setTitle("Asterix ").setPrice(19.99).setOptions(options).build();
		System.out.println(book);
	}
	@Test public void testBookBuilderSchoolBook() throws BookException{
		HashMap<String, Object> options = new HashMap<>();
		options.put("subject", "Physics");
		options.put("year", 8);
		
		Book book = bookBuilder.setType("school").setIsbn("ISBN1").setTitle("Physics for Dummies ").setPrice(19.99).setOptions(options).build();
		System.out.println(book);
	}
	@Test public void testBookBuilderSpecialistBook() throws BookException{
		HashMap<String, Object> options = new HashMap<>();
		options.put("topic", "Physics");
		Book book = bookBuilder.setType("specialist").setIsbn("ISBN1").setTitle("Astronomie for Dummies ").setPrice(19.99).setOptions(options).build();
		System.out.println(book);
	}

}
