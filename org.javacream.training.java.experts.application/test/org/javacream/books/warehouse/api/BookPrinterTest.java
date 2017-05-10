package org.javacream.books.warehouse.api;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.javacream.books.warehouse.impl.BookPrinterImpl;
import org.junit.Before;
import org.junit.Test;

public class BookPrinterTest {

	private BookPrinterImpl bookPrinter;
	@Before public void init(){
		bookPrinter = new BookPrinterImpl();
		Map<Set<String>, BookPrintStrategy> strategies = bookPrinter.getStrategies();
		HashSet<String> optionKeys = new HashSet<>();
		optionKeys.add("color");
		optionKeys.add("resolution");
		strategies.put(optionKeys, (options, book) -> System.out.println("Printing book in color=" + options.get("color") + ", resolution=" + options.get("resolution") + ": " + book.toString()));
		optionKeys = new HashSet<>();
		optionKeys.add("mailaddress");
		strategies.put(optionKeys, (options, book) -> System.out.println("Mailing book to mailaddress=" + options.get("mailaddress") + ": " + book.toString()));
		optionKeys = new HashSet<>();
		optionKeys.add("filename");
		optionKeys.add("mediatype");
		strategies.put(optionKeys, (options, book) -> System.out.println("Saving book to file=" + options.get("filename") + "." +  options.get("mediatype") + ": " + book.toString()));
	}
	@Test public void testBookPrinter() throws BookException{
		Book book = new BookBuilder().setType("school").setIsbn("ISBN").setTitle("Title").setPrice(19.99).setOptions(new HashMap<>()).build();
		Map<String, String> options = new HashMap<>();
		
		options.put("color", "red");
		options.put("resolution", "1024*512");
		bookPrinter.print(options, book);
		
		options.clear();
		options.put("mailaddress", "to@me.org");
		bookPrinter.print(options, book);
		options.clear();
		options.put("filename", "aBook");
		options.put("mediatype", "pdf");
		bookPrinter.print(options, book);
		
	}
}
