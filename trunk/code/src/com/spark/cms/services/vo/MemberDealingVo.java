package com.spark.cms.services.vo;

import java.util.Date;

/**
 * CmsMemberDealing entity.
 * 
 * @author MyEclipse Persistence Tools
 */ 
@SuppressWarnings("serial")
public class MemberDealingVo implements java.io.Serializable {

	// Fields

	private String recid;
	private Long recver;
	private String memberid;
	private String dealtype;
	private String relabillsid;
	private String relabillsno;
	private double amount;
	private Date occurdate;
	private String occurdateStr;
	private String dealtypeStr;

	// Constructors

	public String getDealtypeStr() {
		return dealtypeStr;
	}

	public void setDealtypeStr(String dealtypeStr) {
		this.dealtypeStr = dealtypeStr;
	}

	public String getOccurdateStr() {
		return occurdateStr;
	}

	public void setOccurdateStr(String occurdateStr) {
		this.occurdateStr = occurdateStr;
	}

	/** default constructor */
	public MemberDealingVo() {
	}

	/** minimal constructor */
	public MemberDealingVo(String recid) {
		this.recid = recid;
	}

	/** full constructor */
	public MemberDealingVo(String recid, Long recver, String memberid, String dealtype, String relabillsid,
			String relabillsno, double amount, Date occurdate) {
		this.recid = recid;
		this.recver = recver;
		this.memberid = memberid;
		this.dealtype = dealtype;
		this.relabillsid = relabillsid;
		this.relabillsno = relabillsno;
		this.amount = amount;
		this.occurdate = occurdate;
	}

	// Property accessors

	public String getRecid() {
		return this.recid;
	}

	public void setRecid(String recid) {
		this.recid = recid;
	}

	public Long getRecver() {
		return this.recver;
	}

	public void setRecver(Long recver) {
		this.recver = recver;
	}

	public String getMemberid() {
		return this.memberid;
	}

	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}

	public String getDealtype() {
		return this.dealtype;
	}

	public void setDealtype(String dealtype) {
		this.dealtype = dealtype;
	}

	public String getRelabillsid() {
		return this.relabillsid;
	}

	public void setRelabillsid(String relabillsid) {
		this.relabillsid = relabillsid;
	}

	public String getRelabillsno() {
		return this.relabillsno;
	}

	public void setRelabillsno(String relabillsno) {
		this.relabillsno = relabillsno;
	}

	public double getAmount() {
		return this.amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getOccurdate() {
		return this.occurdate;
	}

	public void setOccurdate(Date occurdate) {
		this.occurdate = occurdate;
	}

}