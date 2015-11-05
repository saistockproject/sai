package com.app.smpt.service;

import java.io.Serializable;
import java.util.List;

import com.app.smpt.common.exception.SystemException;
import com.app.smpt.model.Portfolio;
import com.app.smpt.model.User;

public interface PortfolioService extends Serializable{

	public void addPortfolio(Portfolio portfolio) throws SystemException;

	public Portfolio gePortfolioById(long pfId) throws SystemException;

	public List<Portfolio> getPortfolioByUser(User user) throws SystemException;
}
