package com.app.smpt.service;

import java.io.Serializable;
import java.util.List;

import com.app.smpt.model.StockMaster;

public interface StockMasterService extends Serializable{
	
	public List<StockMaster> getStockMasterList();
	public StockMaster getStockMasterByString(String searchString);

}
