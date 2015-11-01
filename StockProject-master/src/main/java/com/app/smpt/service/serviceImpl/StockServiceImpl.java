package com.app.smpt.service.serviceImpl;


import org.springframework.stereotype.Service;

import com.app.smpt.common.quandl.QDataset;
import com.app.smpt.model.Stock;
import com.app.smpt.service.StockService;

@Service("stockService")
public class StockServiceImpl implements StockService{

	@Override
	public Stock getStockFromQndlStock(QDataset qDataset) {
		Stock stockInfo = new Stock();
		return null;
	}

}
