package com.paymentpin.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;

/**
 * The main entity
 * uses javax.validation for the template pages
 * @author jokrasa
 *
 */
@Entity
public final class Book
{
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
	
    @NotNull(message="Title cannot be Null")
    @Pattern(regexp="^[a-zA-Z0-9_ ]+$",message="Invalid Title")
	private String title;
	
    @NotNull(message="Author cannot be Null")
    @Pattern(regexp="^[a-zA-Z0-9_ ]+$",message="Invalid Author")
	private String author;
    
    @NotNull(message="Genre cannot be Null")
    @Pattern(regexp="^[a-zA-Z0-9_ ]+$",message="Invalid Genre")
    private String genre;
    
    
    @NotNull(message="pages cannot be null")
    @Digits(message="invalid format for pages",integer=32767, fraction = 0)
    private Integer pages;
    
    @NotNull
    private String year;
    
    @NotNull
    private String rating;
      

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
    public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}	
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public Integer getPages() {
		return pages;
	}
	public void setPages(Integer pages) {
		this.pages = pages;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	

    

}
