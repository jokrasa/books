package com.paymentpin;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.paymentpin.entity.Book;

/**
 * A JPA-based implementation of the Booking Service. Delegates to a JPA entity
 * manager to issue data access calls against the backing repository. The
 * EntityManager reference is provided by the managing container (Spring)
 * automatically.
 */
@Service("bookingService")
@Repository
public class JpaBookService implements BookService {

	private EntityManager em;

	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

	public List<Book> searchBooks(String author, String genre, String pages, String year, String rating){
    	System.out.println(">>>>>JpaBookService.searchBooks");
    	List<Predicate> predList = new LinkedList<Predicate>();
    	
			System.out.println(">>>>>author is: "+author);
			System.out.println(">>>>>genre is: "+genre);
			System.out.println(">>>>>pages is: "+pages);
			System.out.println(">>>>>year is: "+year);
			System.out.println(">>>>>rating is: "+rating);

		    Metamodel metamodel = em.getMetamodel();	
		    EntityType<Integer> IntClass = metamodel.entity(Integer.class); 
		    
       	CriteriaBuilder cb = em.getCriteriaBuilder();
    	CriteriaQuery<Book> c =cb.createQuery(Book.class);
    	Root<Book> b = c.from(Book.class);
  
	
		if((!author.equals("")) && (!author.equals("undefined"))){
	    	System.out.println(">>>>>b.get('author'):"+b.get("author"));

	      predList.add(cb.equal(b.get("author"), author));
		}
		if((!genre.equals("")) && (!genre.equals("undefined"))){
  	      predList.add(cb.equal(b.get("genre"), genre));
  		}
		if((!pages.equals("")) && (!pages.equals("undefined"))){
  	      //predList.add(cb.lessThanOrEqualTo(b.get("pages"), pages));
			//ParameterExpression<Integer> Int1 = cb.parameter(Integer.class, b.get("pages").toString());
		       Integer Int1= Integer.valueOf( b.get("pages").toString() );
		       Integer Int2 = Integer.valueOf( pages );

		    //ParameterExpression<Integer> Int2 = cb.parameter(Integer.class, pages);
			predList.add(cb.le(	 Int1, Int2 )); 
  		}
		if((!year.equals("")) && (!year.equals("undefined"))){
  	      predList.add(cb.equal(b.get("year"), year));
  		}
		if((!rating.equals("")) && (!rating.equals("undefined"))){
  	      predList.add(cb.equal(b.get("rating"), rating));
  		}
		Predicate[] predArray = new Predicate[predList.size()];
		predList.toArray(predArray);
		for(Predicate s :predArray){
			System.out.println("predicate: "+s.getExpressions().toString());
		}
		c.where(predArray);
    	

		TypedQuery<Book> q = em.createQuery(c);
		List<Book> result = q.getResultList();
		
		for(Book book: result){
			System.out.println(">>>>>>>FINALLY a BOOK?! "+book.getTitle());
		}



		return result;
	
	}

}
