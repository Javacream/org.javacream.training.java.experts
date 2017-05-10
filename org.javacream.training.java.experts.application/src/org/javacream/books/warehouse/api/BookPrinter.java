package org.javacream.books.warehouse.api;

import java.util.Map;

public interface BookPrinter {

	public void print(Map<String, String> options, Book book);
}
