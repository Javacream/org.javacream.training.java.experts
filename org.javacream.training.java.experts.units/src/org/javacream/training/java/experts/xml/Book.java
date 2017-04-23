package org.javacream.training.java.experts.xml;

public class Book {

	private String isbn;
	private String title;
	private double price;

	public Book() {
	}
	public Book(String isbn, String title, double price) {
		this.isbn = isbn;
		this.title = title;
		this.price = price;
	}

	public String getIsbn() {
		return this.isbn;
	}
	public void setIsbn(String value) {
		this.isbn = value;
	}

	public String getTitle() {
		return this.title;
	}
	public void setTitle(String value) {
		this.title = value;
	}

	public double getPrice() {
		return this.price;
	}
	public void setPrice(double value) {
		this.price = value;
	}

	@Override
	public String toString() {
		return this.getClass().getName() + 
			" [" + this.isbn + ", " + this.title + ", " + this.price + "]";
	}
}
