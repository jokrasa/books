package com.paymentpin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.paymentpin.entity.Book;


public interface BookRepository extends CrudRepository<Book, String> {
	
//    private String id;
//	  private String title;
//	  private String author;
//    private String genre;
//    private String pages;
//    private String year;
//    private String rating;
	
	
	

    List<Book> findAll();
    
    List<Book> findById(int id);
    
    Long deleteById(int id);
    
    /*
     * Search Criteria
     * 
     * 
     */
    
    @Query("select distinct author from Book")
    ArrayList<String> findAllDistinctAuthor();
    
    @Query("select distinct genre from Book")
    ArrayList<String> findAllDistinctGenre();
    
    @Query("select distinct year from Book")
    ArrayList<String> findAllDistinctYear();
  
    
    // the year cannot be empty so by default something
    @Query("select b from Book b where b.author =  ?1 "
    		+ "and b.genre = ?2  "
    		+ "and b.pages = ?3 "
    		+ "and b.year =  ?4 "
    		+ "and b.rating = ?5 ")   
    List<Book> findAllBySearch(@Param("author") String author,@Param("genre") String genre,@Param("pages") Integer pages,
    					@Param("year") String year,
    					@Param("rating") String rating);
    
    
    
    
//    
//    // THIS ONE WORKS!!!
//    @Query("select b from Book b where b.author = ?1 and b.genre = ?2 and b.pages = any(select pages from Book)")
//    List<Book> findAllWhereLikeOrderBy(@Param("author") String author,
//    								   @Param("genre") String genre);
    

    
    @Modifying
    @Query("update Book b set b.title = ?1, b.author = ?2, b.genre = ?3, b.pages = ?4, b.year = ?5, b.rating = ?6 where b.id = ?7")
    void updateBookInfoById(String title, String author, String genre,Integer pages, String year, String rating,Integer id);
   
    

}