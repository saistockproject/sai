package com.app.smpt.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.app.smpt.model.modelValidation.SysNotNull;

@Entity
@Table(name="PORTFOLIO")
public class Portfolio implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8198259252092220025L;
	
	@Id
	@SysNotNull(label = "Portfolio Id")
	@GeneratedValue(strategy=GenerationType.AUTO)   
	@Column(name="PF_ID", unique=true, nullable=false)
	private Long pfId;
	
	@Column(name="PF_NAME",nullable=false)
	private String pfName;
	
	@ManyToOne
	@JoinColumn(name="PF_USER_ID",nullable=false)
	private User user;
	
	public Long getPfId() {
		return pfId;
	}
	public void setPfId(Long pfId) {
		this.pfId = pfId;
	}
	public String getPfName() {
		return pfName;
	}
	public void setPfName(String pfName) {
		this.pfName = pfName;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	

}
