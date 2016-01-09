package com.paymentpin;

import java.util.List;

import com.paymentpin.entity.Book;

public interface BookService {
	
    public List<Book> searchBooks(String author, String genre, Integer pages, String year, String rating);


}
