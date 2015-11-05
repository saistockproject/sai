package com.app.smpt.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.smpt.model.Portfolio;
import com.app.smpt.model.User;

public interface PortfolioDAO extends JpaRepository<Portfolio, Long>{
	
	@Query("select p from Portfolio p where p.user = ?1")
	public List<Portfolio> findPortfolioByUser(User user);
	
}
