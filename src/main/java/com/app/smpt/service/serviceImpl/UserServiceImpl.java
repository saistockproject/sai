package com.app.smpt.service.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.smpt.common.SysLogger;
import com.app.smpt.common.SysLoggerFactory;
import com.app.smpt.common.exception.BusinessException;
import com.app.smpt.common.exception.SystemException;
import com.app.smpt.model.User;
import com.app.smpt.persistence.UserDAO;
import com.app.smpt.service.UserService;
import com.app.smpt.utils.SysUtilities;
 

@Service("userService") 
public class UserServiceImpl implements UserService {

	private static final long serialVersionUID = 1L;
	private SysLogger logger = SysLoggerFactory.getLogger(this.getClass());
	
	@Resource  UserDAO userDAO;
	
	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	

	public Page<User> getUsersList(String emailAddress,Pageable pageable) throws SystemException{
		try{
			if (SysUtilities.isEmptyString(emailAddress)) {
				emailAddress = "%";
			} else {
				emailAddress = "%" + emailAddress + "%";
			}
		//	public Map<String, Object> findByUserName(String username, int start, int size, boolean execCountQuery);

			Page<User> page =  userDAO.findByemailAddress(emailAddress, pageable);
			page.getTotalElements();
			
			return page;
		}catch(Exception ex){
			if(ex instanceof SystemException){
				throw (SystemException)ex;
			}
			throw new SystemException("generic.error.occured.while", new String[] { "getting Users" }, ex);
		}
	}
	
	@Transactional
	public User getUser(String emailAddress) {
		return userDAO.getUser(emailAddress);
	}
	@Transactional
	public User getUserById(long userId) {
		User user =  userDAO.findOne(userId);
		return user;
	}

	@Transactional
	public List<User> getUserList() {
		return userDAO.findAll();
	}
	
	@Transactional
	public void persist(User user) throws BusinessException,SystemException {
		try{
			
			User dbUser = userDAO.getUser(user.getEmailAddress());
			if (dbUser != null) {
				throw new BusinessException("insert.primary.key.violation.error.msg", new String[]{user.getEmailAddress()});
			} else {
				userDAO.save(user);
			}
			
		}catch(BusinessException tb){
			throw tb;
		}catch(Exception ex){
			if(ex instanceof SystemException){
				throw (SystemException)ex;
			}
			throw new SystemException("generic.error.occured.while", new String[] { "saving User" }, ex);
		}
	}
	

	@Transactional
	public void update(User user) throws BusinessException,SystemException {
		try{
			
			User dbUser = userDAO.getUser(user.getEmailAddress());
			if (dbUser != null) {
				if (dbUser.getUserId() != user.getUserId()) {
					throw new BusinessException("insert.primary.key.violation.error.msg", new String[]{user.getEmailAddress() + " user email"});
				}
			}
			userDAO.save(user);
		}catch(BusinessException tb){
			throw tb;
		}catch(Exception ex){
			if(ex instanceof SystemException){
				throw (SystemException)ex;
			}
			throw new SystemException("generic.error.occured.while", new String[] { "updating User" }, ex);
		}
		
	}

	
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public void delete(User user, Long userId) throws SystemException{
		try{
			userDAO.delete(user.getUserId());
			//addNotification("User  " + user + " is deleted",userId);
		}catch(Exception ex){
			throw new SystemException("generic.error.occured.while", new String[] { "deleting User" }, ex);
		}
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public void delete(List<User> delList ,Long userId) throws SystemException {
		try {
			userDAO.delete(delList);
			//addNotification("User list  " + delList + " is deleted",userId);
		} catch (Exception ex) {			
			throw new SystemException("generic.error.occured.while", new String[] { "deleting Company" }, ex);
		}

	}	

	public List<User> getUserList(String userName)throws SystemException{
		try{
			if (SysUtilities.isEmptyString(userName)) {
				userName = "%";
			} else {
				userName += "%";
			}
			return userDAO.getUserList(userName);
		}catch(Exception ex){
			if(ex instanceof SystemException){
				throw (SystemException)ex;
			}
			throw new SystemException("generic.error.occured.while", new String[] { "getting Users" }, ex);
		}
		
	}
	
	@Transactional(rollbackFor = Exception.class)
	public boolean performForgetPasswordReset(String userName, String email) throws BusinessException {
		try {
			User user = userDAO.getUser(userName);
			if (user != null) {
				if(email.equalsIgnoreCase(user.getEmailAddress())){
					String password = "dv123";
					String encodedPassword = passwordEncoder.encode(password);
					user.setUserPassword(encodedPassword);
					userDAO.save(user);
					//String emailBody = "Your passowrd:" + password;
					//MailService mailService = (MailService) SpringAppContext.getBean(MailService.class);
					//String emailFrom = SystemConfigConstants.getFROM_EMAIL();
					//mailService.sendEmail("Ganim Medical password", email, emailFrom, null, emailBody, null);
					return true;
				}else{
					throw new BusinessException("Incorrect user name/email address");
				}
				
			}else{
				throw new BusinessException("Incorrect user name/email address");
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

	}
//	@Override
//	public void persist(UserRoleMapping userRoleMapping)
//			throws BusinessException, SystemException {
//		// TODO Auto-generated method stub
//		
//	}
	
	
	@Override
	public Page<User> getUsersList(String emailAddress, Pageable pageable,
			int userType) throws SystemException {

		try{
			if (SysUtilities.isEmptyString(emailAddress)) {
				emailAddress = "%";
			} else {
				emailAddress = "%" + emailAddress + "%";
			}
		//	public Map<String, Object> findByUserName(String username, int start, int size, boolean execCountQuery);

			Page<User> page =  userDAO.findByemailAddress(emailAddress, pageable);
			page.getTotalElements();
			
			return page;
		}catch(Exception ex){
			if(ex instanceof SystemException){
				throw (SystemException)ex;
			}
			throw new SystemException("generic.error.occured.while", new String[] { "getting Users" }, ex);
		}
	}
	@Transactional
	public void addUser(User user) throws SystemException {
		try {
			userDAO.save(user);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new SystemException("generic.error.occured.while", new String[] { "getting Users" }, ex);
		}
		
	}
	@Override
	public void storeLastPasswords(User user) {
		// TODO Auto-generated method stub
		
	}

}
