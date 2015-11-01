package com.app.smpt.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.smpt.model.StockMaster;
 
public interface StockMasterDAO extends JpaRepository<StockMaster, Long>{
	  
	@Query("select sm from StockMaster sm where sm.smName = ?1")
	public StockMaster getStockMaster(String searchString);
	
	@Query("select sm from StockMaster sm where UPPER(sm.smTicker) = UPPER(?1)")
	public StockMaster getStockMasterByTicker(String searchString);
	 
}
