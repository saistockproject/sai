package com.app.smpt.controller.forms;

import java.io.Serializable;

public class StockSearchForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6368062653988040538L;
	
	private String searchCriteria;
	
	
// getter setter
	public String getSearchCriteria() {
		return searchCriteria;
	}

	public void setSearchCriteria(String searchCriteria) {
		this.searchCriteria = searchCriteria;
	}

}
