package com.app.smpt.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.app.smpt.common.exception.BusinessException;
import com.app.smpt.common.exception.SystemException;
import com.app.smpt.model.User;


public interface UserService extends Serializable{

	public Page<User> getUsersList(String userName,Pageable pageable) throws SystemException;

	public Page<User> getUsersList(String userName,Pageable pageable, int userType) throws SystemException;

	public User getUser(String userName);

	public User getUserById(long userId);
	
	public List<User> getUserList() ;

	public List<User> getUserList(String userName) throws SystemException;

	public void delete(User user, Long userId) throws SystemException;
	
	public void delete(List<User> delList, Long userId) throws SystemException;
	
	public  void storeLastPasswords(User user);
	public boolean performForgetPasswordReset(String userName, String email)  throws BusinessException;
	
	public void addUser(User user) throws SystemException;
	
}
