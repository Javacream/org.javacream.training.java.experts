package org.javacream.training.java.experts.xml;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Publisher {

	private final List<Book> books = new ArrayList<Book>();
	private final String city;

	public Publisher(String city) {
		this.city = city;
	}

	public String getCity() {
		return this.city;
	}
	
	public void add(Book book) {
		this.books.add(book);
	}

	public List<Book> getBooks() {
		return Collections.unmodifiableList(this.books);
	}

	@Override
	public String toString() {
		StringBuilder buf = new StringBuilder();
		buf.append(this.getClass().getName()).append(" [").append(this.city).append(" {\n");
		for (Book book : this.books)
			buf.append("\t").append(book).append("\n");
		return buf.append("}]").toString();
	}
}
