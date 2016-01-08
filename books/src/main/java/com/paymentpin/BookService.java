package com.paymentpin;

import java.util.List;

import com.paymentpin.entity.Book;

public interface BookService {
	
    public List<Book> searchBooks(String author, String genre, String pages, String year, String rating);


}
