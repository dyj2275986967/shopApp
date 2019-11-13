package com.zx.vo;

import java.util.Date;
import java.util.List;

public class OrderVo {
	private int id;
	private String orderCode;
	private Date createDate;
	private Date SendDate;
	private String status;
	private double amount;
	private   int userId;
	private List<OrderItemVo> orderItemList;
	
	
	

	/**
	 * @return the orderCode
	 */
	public String getOrderCode() {
		return orderCode;
	}
	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}
	/**
	 * @return the sendDate
	 */
	public Date getSendDate() {
		return SendDate;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}
	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}
	/**
	 * @param orderCode the orderCode to set
	 */
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * @param sendDate the sendDate to set
	 */
	public void setSendDate(Date sendDate) {
		SendDate = sendDate;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the orderItemList
	 */
	public List<OrderItemVo> getOrderItemList() {
		return orderItemList;
	}
	/**
	 * @param orderItemList the orderItemList to set
	 */
	public void setOrderItemList(List<OrderItemVo> orderItemList) {
		this.orderItemList = orderItemList;
	}
	
	
	
	
	
	
	
	
	

}
