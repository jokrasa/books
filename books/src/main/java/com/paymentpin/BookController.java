package com.paymentpin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.paymentpin.entity.Book;



@Controller
@RequestMapping("/")
public class BookController{	//extends WebMvcConfigurerAdapter {
	
	private BookService bs;

	private BookRepository BookRepo;
	private ReferenceData refData;

	@Autowired
	public BookController(BookRepository BookRepo, BookService bs) {
		this.BookRepo = BookRepo;
		this.bs = bs;
		
	}
	

//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/home").setViewName("home");
//    }
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String home(Model model) {
		
		System.out.println("HOME>>>>>>>>>>>>");
		
		refData = new ReferenceData();
		model.addAttribute("refData", refData);
		
		model.addAttribute("book", new Book());
		
		List<Book> books = BookRepo.findAll();
		// the validated book...
		model.addAttribute("books", books);
		for(Book b : books){
			 System.out.println("book = "+b.getTitle());
		}

		return "home";
	}	

	@RequestMapping(method=RequestMethod.POST)
	public String submit(@Valid Book book,BindingResult bindingResult, Model model ) {
		System.out.println(">>>>>>>>submit(@Valid Book book,Model model, BindingResult bindingResult)");
		//TEST the book....
		System.out.println("title="+book.getTitle());
		System.out.println("author="+book.getAuthor());
		System.out.println("genre="+book.getGenre());
		System.out.println("pages="+book.getPages());
		System.out.println("rating="+book.getRating());
		System.out.println("year="+book.getYear());
		
		
		refData = new ReferenceData();
		model.addAttribute("refData", refData);
		
        if (bindingResult.hasErrors()) {
        	System.out.println(">>>>>>>>bindingResult.HAS Errors()");
            return "home";
        }
        else{
        	System.out.println(">>>>>>>>bindingResult.NO Errors()");
        }
		
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
	
		refData = new ReferenceData();
		model.addAttribute("refData", refData);
 

		List<Book> books = bs.searchBooks(book.getAuthor(), book.getGenre(), book.getPages(), book.getYear(), book.getRating());
		System.out.println("after books = bs.searchBooks");
		
		
		model.addAttribute("books", books);
		
		if(books!=null){
			for(Book b : books){
				System.out.println(b.getTitle());
			}
		}
		

		//return "redirect:/search";
		return "/search";

	}	
	
	//getting data from the server
	@RequestMapping(value="/update",method=RequestMethod.GET)
	public String update(@RequestParam(value = "id", required=false)int id, @Valid Book book, BindingResult bindingResult,Model model){			//Map<String,Object> model) {
		
		System.out.println("UPDATE 1st GET>>>>>>>>>>>>");
		
		refData = new ReferenceData();
		model.addAttribute("refData", refData);
		
		if(id!=0){
			List<Book> books = BookRepo.findById(id);
			book = books.remove(0);
			model.addAttribute("book", book);
			System.out.println("book.getTitle()="+book.getTitle());
		}


		return "/update";
	}
	
	
	

	// new endpoint for Update ...
	// post is creating data on the serverside
	@RequestMapping(value="/update", method=RequestMethod.POST)
	@Transactional
	public String updateSubmit(@Valid Book book, BindingResult bindingResult,Model model) {
		System.out.println("UPDATE POST>>>>>>>>>>>>");	
		//TEST the book....
		System.out.println("title="+book.getTitle());
		System.out.println("author="+book.getAuthor());
		System.out.println("genre="+book.getGenre());
		System.out.println("pages="+book.getPages());
		System.out.println("rating="+book.getRating());
		System.out.println("year="+book.getYear());
		System.out.println("id="+book.getId());
		
        if (bindingResult.hasErrors()) {
        	List<FieldError> errors = bindingResult.getFieldErrors();
        	for(FieldError e: errors){
        		System.out.println(">>>>>>FieldError:  "+e.getField());
        		
        	}
    		refData = new ReferenceData();
    		model.addAttribute("refData", refData);
        	System.out.println(">>>>>>>>bindingResult.HAS Errors()");
        	return "/update";
        }

        	System.out.println(">>>>>>>>bindingResult.NO Errors()");
		
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
