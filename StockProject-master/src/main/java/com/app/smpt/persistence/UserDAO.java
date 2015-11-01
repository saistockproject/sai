package com.app.smpt.persistence;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.smpt.model.User;


public interface UserDAO extends JpaRepository<User, Long>{
	
	@Query("select u from User u where u.emailAddress like ?1")
	public Page<User> findByemailAddress(String emailAddress, Pageable pageable);

	@Query("select u from User u where u.emailAddress = ?1")
	public User getUser(String emailAddress);
	
	@Query("select u from User u where u.emailAddress like ?1")
	public List<User> getUserList(String emailAddress);
	
	@Query("select u from User u order by u.lastName ,u.firstName asc")
	public List<User> getUserList();
	
	@Query("select u from User u where u.userId= ?1 ")
	public User getUserById(Long userId);
	
	
}
