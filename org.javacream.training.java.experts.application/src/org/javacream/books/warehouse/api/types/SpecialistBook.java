package org.javacream.books.warehouse.api.types;

import org.javacream.books.warehouse.api.Book;

public class SpecialistBook extends Book{

	public SpecialistBook() {
		super();
	}
	public SpecialistBook(String isbn, String title, double price, boolean available, String topic) {
		super(isbn, title, price, available);
		this.topic = topic;
	}
	private static final long serialVersionUID = 1L;

	private String topic;
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	@Override
	public String toString() {
		return "SpecialistBook [topic=" + topic + ", toString()=" + super.toString() + "]";
	}
}
