package com.paymentpin;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.fge.jackson.JsonLoader;
import com.paymentpin.entity.Book;

@RestController
@RequestMapping("/rest")
public class RestBookController {
	
	private BookRepository BookRepo;
	private BookService bs;

	@Autowired
	public RestBookController(BookRepository BookRepo, BookService bs) {
		this.BookRepo = BookRepo;
		this.bs = bs;
	}
	
	
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public Book addBook(@RequestBody Book book) {
	    return this.BookRepo.save(book);
	}

	@RequestMapping(value = "/{bookId}", method = RequestMethod.PUT)
	@ResponseBody
	public Book updateBook(@PathVariable int bookId, @RequestBody Book book) {
	    // make sure the book we're to save has the correct ID
	    book.setId(bookId);
	    return this.BookRepo.save(book);
	}

	@RequestMapping(value = "/{bookId}", method = RequestMethod.DELETE)
	public void deleteBook(@PathVariable int bookId) {
	    //Book book = this.BookRepo.findById(bookId);
	    this.BookRepo.deleteById(bookId);
	}
	
	@RequestMapping(value = "/{author}/{genre}/{pages}/{year}/{rating}",method = RequestMethod.GET)
	@ResponseBody
	public List<Book> searchBooks(@PathVariable String author,
								  @PathVariable String genre,
								  @PathVariable String pages,
								  @PathVariable String year,
								  @PathVariable String rating) {
		System.out.println(">>>>>>>>restContr searchBooks");

		System.out.println(">>>>>>>>author: "+author);
		System.out.println(">>>>>>>>genre: "+genre);
		System.out.println(">>>>>>>>year: "+year);
		System.out.println(">>>>>>>>pages: "+pages);
		System.out.println(">>>>>>>>rating: "+rating);


		List<Book> books = this.bs.searchBooks(author, genre, pages, year, rating);
	    
	    return books;
	}	
    
}
