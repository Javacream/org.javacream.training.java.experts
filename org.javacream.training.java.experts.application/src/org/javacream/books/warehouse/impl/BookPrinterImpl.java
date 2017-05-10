package org.javacream.books.warehouse.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.javacream.books.warehouse.api.Book;
import org.javacream.books.warehouse.api.BookPrintStrategy;
import org.javacream.books.warehouse.api.BookPrinter;

public class BookPrinterImpl implements BookPrinter{

	private Map<Set<String>, BookPrintStrategy> strategies;
	
	{
		strategies = new HashMap<>();
	}
	
	
	
	public Map<Set<String>, BookPrintStrategy> getStrategies() {
		return strategies;
	}



	@Override
	public void print(Map<String, String> options, Book book) {
		strategies.get(options.keySet()).print(options, book);
	}

}
