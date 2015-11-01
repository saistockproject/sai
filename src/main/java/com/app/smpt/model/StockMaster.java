package com.app.smpt.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.app.smpt.model.modelValidation.SysNotNull;

@Entity
@Table(name="STOCK_MASTER")
public class StockMaster implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7730955340059670154L;

	@Id
	@SysNotNull(label = "Stock Master Id")
	@GeneratedValue(strategy=GenerationType.AUTO)   
	@Column(name="SM_ID", unique=true, nullable=false)
	private Long smId;
	
	@Column(nullable=false,name= "SM_TICKER")
	private String smTicker;
	
	@Column(nullable=false,name= "SM_Name")
	private String smName;

	public Long getSmId() {
		return smId;
	}

	public void setSmId(Long smId) {
		this.smId = smId;
	}

	public String getSmTicker() {
		return smTicker;
	}

	public void setSmTicker(String smTicker) {
		this.smTicker = smTicker;
	}

	public String getSmName() {
		return smName;
	} 

	public void setSmName(String smName) {
		this.smName = smName;
	}
	
}
