package com.paymentpin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paymentpin.entity.Book;



@Controller
@RequestMapping("/")
public class BookController {
	
	private BookService bs;

	private BookRepository BookRepo;
	private ReferenceData refData;

	@Autowired
	public BookController(BookRepository BookRepo, BookService bs) {
		this.BookRepo = BookRepo;
		this.bs = bs;
		
	}
	


	
	
	@RequestMapping(method=RequestMethod.GET)
	public String home(Map<String,Object> model) {
		
		System.out.println("HOME>>>>>>>>>>>>");
		
		List<Book> books = BookRepo.findAll();
		model.put("books", books);
		for(Book b : books){
			 System.out.println("book = "+b.getTitle());
		}
		
//	    ArrayList<String> authors = BookRepo.findAllDistinctAuthor();
//		model.put("authors",authors);
//		
//	    ArrayList<String> genres = BookRepo.findAllDistinctGenre();		
//		model.put("genres",genres);
//		
//		refData = new ReferenceData();
//		model.put("refData", refData);

		return "home";
	}	

	@RequestMapping(method=RequestMethod.POST)
	public String submit(Book book) {
		
		//TEST the book....
		System.out.println("title="+book.getTitle());
		System.out.println("author="+book.getAuthor());
		System.out.println("genre="+book.getGenre());
		System.out.println("pages="+book.getPages());
		System.out.println("rating="+book.getRating());
		System.out.println("year="+book.getYear());
		
		BookRepo.save(book);
		return "redirect:/";
	}

	// does a read...
	@RequestMapping(value="/search",method=RequestMethod.GET)
	public String searchForm(Model model){				//Map<String,Object> model) {
		
		System.out.println("SEARCH LANDING>>>>>>>>>>>>");
		
	    ArrayList<String> authors = BookRepo.findAllDistinctAuthor();
		model.addAttribute("authors",authors);
		
	    ArrayList<String> genres = BookRepo.findAllDistinctGenre();		
		model.addAttribute("genres",genres);
		
	    ArrayList<String> years = BookRepo.findAllDistinctYear();		
		model.addAttribute("years",years);		
		
		refData = new ReferenceData();
		model.addAttribute("refData", refData);
		
		model.addAttribute("book", new Book()); 
		
		
		return "/search";
	}	
	
	
	/// does submit form...
	@RequestMapping(value ="/search",method=RequestMethod.POST)
	public String searchSubmit(@ModelAttribute Book book, Model model) {
		
		System.out.println("SEARCHING back To SEARCH>>>>>>>>>>>>");
		
		//TEST the book....
		System.out.println("title="+book.getTitle());
		System.out.println("author="+book.getAuthor());

		System.out.println("genre="+book.getGenre());

		System.out.println("pages="+book.getPages());
		System.out.println("rating="+book.getRating());
		System.out.println("years="+book.getYear());

		
	    ArrayList<String> authors = BookRepo.findAllDistinctAuthor();
		model.addAttribute("authors",authors);
		
	    ArrayList<String> genres = BookRepo.findAllDistinctGenre();		
		model.addAttribute("genres",genres);
		
	    ArrayList<String> years = BookRepo.findAllDistinctYear();		
		model.addAttribute("years",years);
		
		refData = new ReferenceData();
		model.addAttribute("refData", refData);
 
		/* Query Builder Strategy Pattern 
		 * 
		 *   takes BookRepo and a book returns List of books  
		 *   
		 *   */
List<Book> books = bs.searchBooks(book.getAuthor(), book.getGenre(), book.getPages(), book.getYear(), book.getRating());
System.out.println("after books = bs.searchBooks");
		//List<Book> books = BookRepo.findAllWhereLikeOrderBy(book.getAuthor(), book.getGenre());
		
		
		//Map<String,Object> model = new HashMap<String, Object>();
		model.addAttribute("books", books);
		
		//System.out.println(books.size());
		if(books!=null){
			for(Book b : books){
				System.out.println(b.getTitle());
			}
		}
		

		//return "redirect:/search";
		return "/search";

	}
	
	
	// new endpoint for READ....
	
	@RequestMapping(value="/update",method=RequestMethod.GET)
	public String update(@RequestParam int id,Map<String,Object> model) {
		
		System.out.println("UPDATE GET>>>>>>>>>>>>");
		
		List<Book> books = BookRepo.findById(id);
		Book book = books.remove(0);
		model.put("book", book);
		System.out.println("book.getTitle()="+book.getTitle());


		return "/update";
	}

	// new endpoint for Update ...
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	@Transactional
	public String updateSubmit(Book book) {
		System.out.println("UPDATE POST>>>>>>>>>>>>");	
		//TEST the book....
		System.out.println("title="+book.getTitle());
		System.out.println("author="+book.getAuthor());
		System.out.println("genre="+book.getGenre());
		System.out.println("pages="+book.getPages());
		System.out.println("rating="+book.getRating());
		System.out.println("year="+book.getYear());
		System.out.println("id="+book.getId());
		
		BookRepo.updateBookInfoById(book.getTitle(), book.getAuthor(), book.getGenre(), book.getPages(), book.getYear(), book.getRating(),book.getId());
		return "redirect:/search";
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.GET)
	@Transactional
	public String updateDelete(@RequestParam int id) {
		System.out.println("UPDATE DELETE>>>>>>>>>>>>");	
//		//TEST the book....
//		System.out.println("title="+book.getTitle());
//		System.out.println("author="+book.getAuthor());
//		System.out.println("genre="+book.getGenre());
//		System.out.println("pages="+book.getPages());
//		System.out.println("rating="+book.getRating());
//		System.out.println("year="+book.getYear());
//		System.out.println("id="+book.getId());
		
		BookRepo.deleteById(id);
		return "redirect:/search";
	}	
	
	
}
