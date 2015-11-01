package com.app.smpt.service;

import java.io.Serializable;

import com.app.smpt.common.exception.SystemException;
import com.app.smpt.model.Portfolio;

public interface PortfolioService extends Serializable{

	public void addPortfolio(Portfolio portfolio) throws SystemException;
}
