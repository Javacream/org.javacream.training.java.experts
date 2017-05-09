package org.javacream.books.warehouse.api.types;

import org.javacream.books.warehouse.api.Book;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString(callSuper=true, includeFieldNames=true)
@AllArgsConstructor
@NoArgsConstructor
public class SpecialistBook extends Book{

	private static final long serialVersionUID = 1L;

	private String topic;
}
