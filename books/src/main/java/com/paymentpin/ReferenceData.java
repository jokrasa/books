package com.paymentpin;

import java.util.ArrayList;
import java.util.List;


import com.paymentpin.entity.SelectItem;

/**
 * Reusable for selects throughout the site
 * @author jokrasa
 *
 */
public class ReferenceData {


	private List<SelectItem> pages;

	private List<SelectItem> year;

	private List<SelectItem> rating;
	
	private List<SelectItem> ranking;

	private List<SelectItem> pageSizeOptions;


	public List<SelectItem> getPages() {
		if (pages == null) {
			pages = new ArrayList<SelectItem>();
			pages.add(new SelectItem("50", "50"));
			pages.add(new SelectItem("100", "100"));
			pages.add(new SelectItem("200", "200"));
			pages.add(new SelectItem("300", "300"));
			pages.add(new SelectItem("400", "400"));
			pages.add(new SelectItem("500", "500"));

		}
		return pages;
	}

	public List<SelectItem> getYears() {
		if (year == null) {
			year = new ArrayList<SelectItem>();
			year.add(new SelectItem("Classic", "Classic"));
			year.add(new SelectItem("Modern", "Modern"));
		}
		return year;
	}

	public List<SelectItem> getRating() {
		if (rating == null) {
			rating = new ArrayList<SelectItem>();
			rating.add(new SelectItem("1", "1"));
			rating.add(new SelectItem("2", "2"));
			rating.add(new SelectItem("3", "3"));
			rating.add(new SelectItem("4", "4"));
			rating.add(new SelectItem("5", "5"));

		}
		return rating;
	}
	
	public List<SelectItem> getRanking() {
		if (ranking == null) {
			ranking = new ArrayList<SelectItem>();
			ranking.add(new SelectItem("1", "1"));
			ranking.add(new SelectItem("2", "2"));
			ranking.add(new SelectItem("3", "3"));
			ranking.add(new SelectItem("4", "4"));
			ranking.add(new SelectItem("5", "5"));

		}
		return ranking;
	}

	public List<SelectItem> getPageSizeOptions() {
		if (pageSizeOptions == null) {
			pageSizeOptions = new ArrayList<SelectItem>();
			pageSizeOptions.add(new SelectItem("5", "5"));
			pageSizeOptions.add(new SelectItem("10", "10"));

		}
		return pageSizeOptions;
	}

}
