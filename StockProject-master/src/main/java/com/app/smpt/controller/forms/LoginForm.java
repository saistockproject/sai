package com.app.smpt.controller.forms;

import java.io.Serializable;

import com.app.smpt.model.modelValidation.SysEmail;
import com.app.smpt.model.modelValidation.SysNotNull;

public class LoginForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6921329040400323694L;
	
	@SysEmail
	@SysNotNull(label = "Enter Your Email")
	private String emailAddr;
	@SysNotNull(label = "Enter Password")
	private String userPassword;
	public String getEmailAddr() {
		return emailAddr;
	}
	public void setEmailAddr(String emailAddr) {
		this.emailAddr = emailAddr;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
	

}
