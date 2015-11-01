package com.app.smpt.model;

import java.io.Serializable;

public class StockSearch implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3581814154804702080L;
	
	private int id;
	private String searchCriteria;
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSearchCriteria() {
		return searchCriteria;
	}
	public void setSearchCriteria(String searchCriteria) {
		this.searchCriteria = searchCriteria;
	}
	
	
}
