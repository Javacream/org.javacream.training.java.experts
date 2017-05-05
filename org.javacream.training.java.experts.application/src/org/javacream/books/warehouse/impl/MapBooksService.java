package org.javacream.books.warehouse.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.javacream.books.warehouse.api.Book;
import org.javacream.books.warehouse.api.BookBuilder;
import org.javacream.books.warehouse.api.BookException;
import org.javacream.books.warehouse.api.BookException.BookExceptionType;
import org.javacream.books.warehouse.api.BooksService;
import org.javacream.util.notification.NotificationSupport;

/**
 * @author Dr. Rainer Sawitzki
 * @company Javacream
 * @mailto rainer.sawitzki@javacream.org
 * 
 */

public class MapBooksService implements BooksService {

	
	public MapBooksService() {

	}

	private Map<String, Book> books;

	private BookBuilder bookBuilder;

	private NotificationSupport<Book> notificationSupport;
	{
		bookBuilder = new BookBuilder();
		books = new HashMap<>();
		notificationSupport = new NotificationSupport<>();
	}


	public NotificationSupport<Book> getNotificationSupport() {
		return notificationSupport;
	}

	public void setNotificationSupport(NotificationSupport<Book> notificationSupport) {
		this.notificationSupport = notificationSupport;
	}

	@Override
	public String newBook(String type, String title, double price, Map<String, Object> options) throws BookException {
		String isbn = "ISBN:" + Math.random();
		Book book = bookBuilder.setType(type).setIsbn(isbn).setPrice(price).setTitle(title).setOptions(options).build();
		books.put(isbn, book);
		notificationSupport.fireNotification("newBook", book);
		return isbn;
	}

	@Override
	public Book findBookByIsbn(String isbn) throws BookException {
		Book result = (Book) books.get(isbn);
		if (result == null) {
			throw new BookException(BookException.BookExceptionType.NOT_FOUND, isbn);
		}
		return result;
	}

	@Override
	public Book updateBook(Book book) throws BookException {
		if (book.getPrice() < 0){
			throw new BookException(BookExceptionType.CONSTRAINT, "illegal price");
		}
		Book value = books.get(book.getIsbn());
		value.setTitle(book.getTitle());
		value.setPrice(book.getPrice());
		value.setAvailable(book.isAvailable());
		notificationSupport.fireNotification("updateBook", book);
		return value;
	}

	@Override
	public void deleteBookByIsbn(String isbn) throws BookException {
		Book book = books.remove(isbn);
		if (book == null) {
			throw new BookException(BookException.BookExceptionType.NOT_DELETED, isbn);
		}else{
			notificationSupport.fireNotification("deleteBook", book);

		}
		
	}

	@Override
	public Collection<Book> findAllBooks() {
		return new ArrayList<>(books.values());
	}

}
