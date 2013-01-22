package com.spark.cms.services.vo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CmsOrderDet entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ERP_Sales_OnlineOrder_Log")
@SuppressWarnings("serial")
public class OrderLogVo implements java.io.Serializable {

	// Fields

	private byte[] recid;
	private byte[] billsId;
	private Date processingTime;
	private String message;
	private String operator;


	// Property accessors
	@Id
	@Column(name = "RECID", unique = true, nullable = false)
	public byte[] getRecid() {
		return this.recid;
	}

	public void setRecid(byte[] recid) {
		this.recid = recid;
	}
	@Column(name = "billsId")
	public byte[] getBillsId() {
		return billsId;
	}

	public void setBillsId(byte[] billsId) {
		this.billsId = billsId;
	}
	@Column(name = "processingTime")
	public Date getProcessingTime() {
		return processingTime;
	}

	public void setProcessingTime(Date processingTime) {
		this.processingTime = processingTime;
	}
	@Column(name = "message")
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	@Column(name = "operator")
	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}


}