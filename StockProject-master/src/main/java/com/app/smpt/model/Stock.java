package com.app.smpt.model;

import java.io.Serializable;
import java.util.Date;


public class Stock  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 
	
	private Long stockId;
	private Date date;
	private Double open;
	private Double high;
	private Double low;
	private Double close;
	private Double volume;
	private Double exDivident;
	private Double splitRatio;
	private Double adjOpen;
	private Double adjHigh;
	private Double adjLow;
	private Double adjClose;
	private Double adjVolume;
	
	
	// getter setters
	public Long getStockId() {
		return stockId;
	}
	public void setStockId(Long stockId) {
		this.stockId = stockId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Double getOpen() {
		return open;
	}
	public void setOpen(Double open) {
		this.open = open;
	}
	public Double getHigh() {
		return high;
	}
	public void setHigh(Double high) {
		this.high = high;
	}
	public Double getLow() {
		return low;
	}
	public void setLow(Double low) {
		this.low = low;
	}
	public Double getClose() {
		return close;
	}
	public void setClose(Double close) {
		this.close = close;
	}
	public Double getVolume() {
		return volume;
	}
	public void setVolume(Double volume) {
		this.volume = volume;
	}
	public Double getExDivident() {
		return exDivident;
	}
	public void setExDivident(Double exDivident) {
		this.exDivident = exDivident;
	}
	public Double getSplitRatio() {
		return splitRatio;
	}
	public void setSplitRatio(Double splitRatio) {
		this.splitRatio = splitRatio;
	}
	public Double getAdjOpen() {
		return adjOpen;
	}
	public void setAdjOpen(Double adjOpen) {
		this.adjOpen = adjOpen;
	}
	public Double getAdjHigh() {
		return adjHigh;
	}
	public void setAdjHigh(Double adjHigh) {
		this.adjHigh = adjHigh;
	}
	public Double getAdjLow() {
		return adjLow;
	}
	public void setAdjLow(Double adjLow) {
		this.adjLow = adjLow;
	}
	public Double getAdjClose() {
		return adjClose;
	}
	public void setAdjClose(Double adjClose) {
		this.adjClose = adjClose;
	}
	public Double getAdjVolume() {
		return adjVolume;
	}
	public void setAdjVolume(Double adjVolume) {
		this.adjVolume = adjVolume;
	}
	
	
}
