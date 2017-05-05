package org.javacream.books.warehouse.api;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.Properties;

import org.javacream.books.warehouse.api.BookException.BookExceptionType;

public class BookBuilder {

	private String isbn;
	private String title;
	private double price;
	private String type;
	private Map<String, Object> options;

	private Properties bookTypes;

	public BookBuilder setIsbn(String isbn) {
		this.isbn = isbn;
		return this;
	}

	{
		bookTypes = new Properties();
		try {
			bookTypes.load(this.getClass().getResourceAsStream("book_types.properties"));
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}

	}

	public BookBuilder setType(String type) {
		this.type = type;
		return this;
	}

	public BookBuilder setTitle(String title) {
		this.title = title;
		return this;
	}

	public BookBuilder setPrice(double price) {
		this.price = price;
		return this;

	}

	public BookBuilder setOptions(Map<String, Object> options) {
		this.options = options;
		return this;

	}

	public Book build() throws BookException {
		try {

			@SuppressWarnings("unchecked")
			Class<? extends Book> bookClass = (Class<? extends Book>) Class.forName(bookTypes.getProperty(type));
			Book book = bookClass.newInstance();
			book.setIsbn(isbn);
			book.setPrice(price);
			book.setTitle(title);
			for (String attributeName : options.keySet()) {
				Field field = bookClass.getDeclaredField(attributeName);
				field.setAccessible(true);
				field.set(book, options.get(attributeName));
			}
			isbn = null;
			title = null;
			price = 0;
			type = null;
			return book;
		} catch (Exception e) {
			throw new BookException(BookExceptionType.NOT_CREATED, e.getMessage());
		}

	}

}
