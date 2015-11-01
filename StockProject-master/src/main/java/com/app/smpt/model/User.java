package com.app.smpt.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.app.smpt.model.modelValidation.SysNotNull;

 
@Entity
@Table(name="USERS")
public class User implements Serializable {
	

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)   
	@Column(name="USERS_ID", unique=true, nullable=false)
	private Long userId;
	
    @Column(name= "USERS_FIRST_NAME")
	private String firstName;

    @Column(name= "USERS_LAST_NAME")
	private String lastName;
    
    @Column(name="USERS_ACTIVE_INDICATOR", nullable=false, length=1)
	private String activeIndicator;  

   	@Column(name="USERS_PASSWORD", nullable=false, length=500)
   	private String userPassword;
	
 	@Column(name="USERS_EMAIL",  length=50)
   	private String emailAddress;
 	
 	@Column(name="USERS_CREATED_DATE")
 	private Date createdDate;
 	
 	@Column(name="USERS_DOB")
 	private Date dob;
 	
 	
 	
 	// getter setter
    public User() {
    }

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	
	public String getActiveIndicator() {
		return activeIndicator;
	}

	public void setActiveIndicator(String activeIndicator) {
		this.activeIndicator = activeIndicator;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName="
				+ ", firstName=" + firstName + ", lastName=" + lastName
				+ ", activeIndicator=" + activeIndicator + "]";
	}

	
}
	