package com.spark.cms.dao.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CmsMemberAccount entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "cms_member_account")
@SuppressWarnings("serial")
public class MemberAccountPo implements java.io.Serializable {

	// Fields

	private byte[] recid;
	private Long recver;
	private double moneybalance;
	private double vantages;
	private String paypassword;
	// Constructors

	/** default constructor */
	public MemberAccountPo() {
	}

	/** minimal constructor */
	public MemberAccountPo(byte[] recid) {
		this.recid = recid;
	}

	/** full constructor */
	public MemberAccountPo(byte[] recid, Long recver, double moneybalance, double vantages,
			String paypassword) {
		this.recid = recid;
		this.recver = recver;
		this.moneybalance = moneybalance;
		this.vantages = vantages;
		this.paypassword = paypassword;
	}

	// Property accessors
	@Id
	@Column(name = "RECID", unique = true, nullable = false)
	public byte[] getRecid() {
		return this.recid;
	}

	public void setRecid(byte[] recid) {
		this.recid = recid;
	}

	@Column(name = "RECVER")
	public Long getRecver() {
		return this.recver;
	}

	public void setRecver(Long recver) {
		this.recver = recver;
	}

	@Column(name = "MONEYBALANCE")
	public double getMoneybalance() {
		return this.moneybalance;
	}

	public void setMoneybalance(double moneybalance) {
		this.moneybalance = moneybalance;
	}

	@Column(name = "VANTAGES")
	public double getVantages() {
		return this.vantages;
	}

	public void setVantages(double vantages) {
		this.vantages = vantages;
	}

	@Column(name = "PAYPASSWORD")
	public String getPaypassword() {
		return this.paypassword;
	}

	public void setPaypassword(String paypassword) {
		this.paypassword = paypassword;
	}

}