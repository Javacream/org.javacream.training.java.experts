package org.javacream.books.warehouse.api.types;

import org.javacream.books.warehouse.api.Book;

public class Comic extends Book {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String hero;

	public Comic() {
	}

	public Comic(String isbn, String title, double price, boolean available, String hero) {
		super(isbn, title, price, available);
		this.hero = hero;
	}

	public String getHero() {
		return hero;
	}

	@Override
	public String toString() {
		return "Comic [hero=" + hero + ", toString()=" + super.toString() + "]";
	}

	
}
