package com.spark.cms.services.vo;


/**
 * CmsMemberAccount entity.
 * 
 * @author MyEclipse Persistence Tools
 */ 
@SuppressWarnings("serial")
public class MemberAccountVo implements java.io.Serializable {

	// Fields

	private String recid; 
	private Long recver; 
	private double moneybalance;
	private double vantages;
	private String paypassword;
	private int deliveryCount; 

	// Constructors

	/** default constructor */
	public MemberAccountVo() {
	}

	/** minimal constructor */
	public MemberAccountVo(String recid) {
		this.recid = recid;
	}

	public Long getRecver() {
		return recver;
	}

	public void setRecver(Long recver) {
		this.recver = recver;
	}

	/** full constructor */
	public MemberAccountVo(String recid,  Double moneybalance, Double vantages,
			String paypassword) {
		this.recid = recid;  
		this.moneybalance = moneybalance;
		this.vantages = vantages;
		this.paypassword = paypassword;
	}

	// Property accessors

	public String getRecid() {
		return this.recid;
	}

	public void setRecid(String recid) {
		this.recid = recid;
	}  

	public double getMoneybalance() {
		return this.moneybalance;
	}

	public void setMoneybalance(double moneybalance) {
		this.moneybalance = moneybalance;
	}

	public double getVantages() {
		return this.vantages;
	}

	public void setVantages(double vantages) {
		this.vantages = vantages;
	}

	public String getPaypassword() {
		return this.paypassword;
	}

	public void setPaypassword(String paypassword) {
		this.paypassword = paypassword;
	}

	public int getDeliveryCount() {
		return deliveryCount;
	}

	public void setDeliveryCount(int deliveryCount) {
		this.deliveryCount = deliveryCount;
	} 

}