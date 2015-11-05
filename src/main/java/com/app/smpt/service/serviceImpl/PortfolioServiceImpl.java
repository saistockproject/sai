package com.app.smpt.service.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.app.smpt.common.exception.SystemException;
import com.app.smpt.model.Portfolio;
import com.app.smpt.model.User;
import com.app.smpt.persistence.PortfolioDAO;
import com.app.smpt.service.PortfolioService;

@Service("portfolioService") 
public class PortfolioServiceImpl implements PortfolioService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Resource PortfolioDAO portfolioDAO;
	
	@Override
	public void addPortfolio(Portfolio portfolio) throws SystemException {
		portfolioDAO.save(portfolio);
		
	}

	@Override
	public Portfolio gePortfolioById(long pfId) throws SystemException {
		return portfolioDAO.getOne(pfId);
	}

	@Override
	public List<Portfolio> getPortfolioByUser(User user) throws SystemException {
		return (List<Portfolio>) portfolioDAO.findPortfolioByUser(user);
	}

	
	// getter setter
	public PortfolioDAO getPortfolioDAO() {
		return portfolioDAO;
	}

	public void setPortfolioDAO(PortfolioDAO portfolioDAO) {
		this.portfolioDAO = portfolioDAO;
	}

}
