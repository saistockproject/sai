package com.app.smpt.service.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.smpt.model.StockMaster;
import com.app.smpt.persistence.StockMasterDAO;
import com.app.smpt.service.StockMasterService;

@Service("stockMasterService")
public class StockMasterServiceImpl implements StockMasterService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6039693545921922675L;

	@Resource StockMasterDAO stockMasterDAO;
	 
	@Transactional
	public List<StockMaster> getStockMasterList() {
		return null;
	}
 
	@Transactional
	public StockMaster getStockMasterByString(String searchString) {
		return stockMasterDAO.getStockMasterByTicker(searchString);
	}

}
