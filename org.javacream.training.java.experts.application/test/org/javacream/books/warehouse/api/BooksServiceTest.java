package org.javacream.books.warehouse.api;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Collection;
import java.util.HashMap;

import org.javacream.books.warehouse.api.types.Comic;
import org.javacream.books.warehouse.api.types.SchoolBook;
import org.javacream.books.warehouse.api.types.SpecialistBook;
import org.javacream.books.warehouse.impl.MapBooksService;
import org.javacream.util.aspects.SerializerAspect;
import org.javacream.util.notification.Notification;
import org.javacream.util.notification.NotificationListener;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Dr. Rainer Sawitzki
 * @company Javacream
 * @mailto training@rainer-sawitzki.de
 * 
 */
public class BooksServiceTest {

	private BooksService booksService;
	private String ISBN;
	private static final String WRONG_ISBN = "##ISBN##";

	@Before
	public void init() {
		MapBooksService mapBooksService = new MapBooksService();
		mapBooksService.getNotificationSupport().addNotificationListener(new NotificationListener<Book>() {
			
			@Override
			public void handle(Notification<Book> notification) {
				System.out.println("Handling notification on book " + notification.getData() );
			}
		});
		booksService = SerializerAspect.createAspects(mapBooksService);
		try {
			ISBN = booksService.newBook("comic", "Test", 9.99, new HashMap<>());
		} catch (BookException be) {
			throw new RuntimeException(be.getMessage());
		}
	}

	@Test
	public void testBusinessObjects() {
		doTest(booksService);
	}

	@Test
	public void deleteBookByIsbnOk() throws BookException {
		Book book = booksService.findBookByIsbn(ISBN);
		booksService.deleteBookByIsbn(ISBN);
		Assert.assertFalse(booksService.findAllBooks().contains(book));
	}

	@Test(expected = BookException.class)
	public void deleteBookByIsbnWrong() throws BookException {
		booksService.deleteBookByIsbn(WRONG_ISBN);
	}

	@Test
	public void findBookByIsbnOk() throws BookException {
		Book book = booksService.findBookByIsbn(ISBN);
		Assert.assertNotNull(book);
	}

	@Test(expected = BookException.class)
	public void findBookByIsbnWrong() throws BookException {
		booksService.findBookByIsbn(WRONG_ISBN);
	}

	@Test
	public void findBookAllBooks() {
		Assert.assertNotNull(booksService.findAllBooks());
	}

	@Test
	public void updateBookOk() throws BookException {
		final String NEW_TITLE = "CHANGED";
		Book book = booksService.findBookByIsbn(ISBN);
		book.setTitle(NEW_TITLE);
		book.setPrice(19.99);
		Book book2 = booksService.updateBook(book);
		Assert.assertTrue(NEW_TITLE.equals(book2.getTitle()));
	}

	@SuppressWarnings("serial")
	@Test(expected = Exception.class)
	public void updateBookWrong() throws BookException {
		Book book = new Book() {
		};
		book.setIsbn(WRONG_ISBN);
		book.setPrice(19.99);
		booksService.updateBook(book);
	}

	@Test(expected = BookException.class)
	public void updateBookPriceNotGreaterZeroFails() throws BookException {
		Book book = booksService.findBookByIsbn(ISBN);
		book.setPrice(-19.99);
		booksService.updateBook(book);
	}

	@Test
	public void checkDeepCopy() throws BookException {
		Book book = booksService.findBookByIsbn(ISBN);
		Book book2 = booksService.findBookByIsbn(ISBN);
		Assert.assertFalse(book == book2);

	}


	@Test
	public void createSchoolBook() throws BookException {
		HashMap<String, Object> options = new HashMap<String, Object>();
		options.put("subject", "Physics");
		options.put("year", 10);
		String isbn = booksService.newBook("school", "TEST", 19.99, options);
		Book book = booksService.findBookByIsbn(isbn);
		Assert.assertTrue(book.getClass() == SchoolBook.class);

	}

	@Test
	public void createComic() throws BookException {
		HashMap<String, Object> options = new HashMap<String, Object>();
		options.put("hero", "Superman");
		String isbn = booksService.newBook("comic", "TEST", 19.99, options);
		Book book = booksService.findBookByIsbn(isbn);
		Assert.assertTrue(book.getClass() == Comic.class);

	}

	@Test
	public void createSpecialistBook() throws BookException {
		HashMap<String, Object> options = new HashMap<String, Object>();
		options.put("topic", "Very Special");
		String isbn = booksService.newBook("specialist", "TEST", 19.99, options);
		Book book = booksService.findBookByIsbn(isbn);
		Assert.assertTrue(book.getClass() == SpecialistBook.class);

	}

	@Test(expected = BookException.class)
	public void createBookFailsNullOptions() throws BookException {
		booksService.newBook("comic", "TEST", 19.99, null);

	}

	private void doTest(BooksService booksService) {

		try {
			Collection<Book> books = booksService.findAllBooks();
			final int startSize = books.size();
			String j2eeTitle = "Spring";
			String isbn = booksService.newBook("specialist", j2eeTitle, 9.99, new HashMap<String, Object>());
			books = booksService.findAllBooks();
			final int endSize = books.size();
			assertTrue(endSize == startSize + 1);
			assertNotNull(isbn);
			assertNotNull(booksService.findBookByIsbn(isbn));

			try {
				booksService.findBookByIsbn("ISBN-3");
				fail("BookException must be thrown!");
			} catch (BookException e) {
				// OK
			}
			booksService.deleteBookByIsbn(isbn);
			assertTrue(startSize == booksService.findAllBooks().size());
			try {
				booksService.deleteBookByIsbn(isbn);
				fail("BookException must be thrown!");
			} catch (BookException e) {
				// OK
			}

		} catch (BookException bookException) {
			bookException.printStackTrace();
			fail(bookException.getMessage());
		}

	}

}
