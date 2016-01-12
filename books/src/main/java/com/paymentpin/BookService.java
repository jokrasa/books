package com.paymentpin;

import java.util.List;

import com.paymentpin.entity.Book;

/**
 * Interface for the JPA CriteriaBuilder
 * @author jokrasa
 *
 */

public interface BookService {
	
    public List<Book> searchBooks(String author, String genre, Integer pages, String year, String rating);


}
