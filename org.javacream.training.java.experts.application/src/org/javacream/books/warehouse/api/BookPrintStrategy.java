package org.javacream.books.warehouse.api;

import java.util.Map;

@FunctionalInterface
public interface BookPrintStrategy {

	public void print(Map<String, String> options, Book book);
}
