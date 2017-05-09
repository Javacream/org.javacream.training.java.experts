package org.javacream.books.warehouse.api;

import java.util.Collection;
import java.util.Map;

import org.javacream.util.aspects.Traced;

public interface BooksService {

	@Traced
	String newBook(String type, String title, double price, Map<String, Object> options) throws BookException;

	Book findBookByIsbn(String isbn) throws BookException;

	Book updateBook(Book bookDetailValue) throws BookException;

	void deleteBookByIsbn(String isbn) throws BookException;

	Collection<Book> findAllBooks();

}