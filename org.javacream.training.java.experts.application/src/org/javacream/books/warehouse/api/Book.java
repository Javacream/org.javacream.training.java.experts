package org.javacream.books.warehouse.api;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author Dr. Rainer Sawitzki
 * @company Javacream
 * @mailto training@rainer-sawitzki.de
 * 
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public abstract class Book implements Serializable {
	private static final long serialVersionUID = 1L;

	private String isbn;

	private String title;

	private double price;

	private boolean available;

}
