package com.app.smpt.service;

import java.io.Serializable;

import com.app.smpt.common.quandl.QDataset;
import com.app.smpt.model.Stock;

public interface StockService extends Serializable{
	public Stock getStockFromQndlStock(QDataset qDataset);

}
