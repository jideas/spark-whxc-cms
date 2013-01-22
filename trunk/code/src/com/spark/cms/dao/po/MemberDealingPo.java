package com.spark.cms.dao.po;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * CmsMemberDealing entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "cms_member_dealing")
@SuppressWarnings("serial")
public class MemberDealingPo implements java.io.Serializable {

	// Fields

	private byte[] recid;
	private Long recver;
	private byte[] memberid;
	private String dealtype;
	private byte[] relabillsid;
	private String relabillsno;
	private double amount;
	private Date occurdate;

	// Constructors

	/** default constructor */
	public MemberDealingPo() {
	}

	/** minimal constructor */
	public MemberDealingPo(byte[] recid) {
		this.recid = recid;
	}

	/** full constructor */
	public MemberDealingPo(byte[] recid, Long recver, byte[] memberid, String dealtype, byte[] relabillsid,
			String relabillsno, Long amount, Date occurdate) {
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

	@Column(name = "MEMBERID")
	public byte[] getMemberid() {
		return this.memberid;
	}

	public void setMemberid(byte[] memberid) {
		this.memberid = memberid;
	}

	@Column(name = "DEALTYPE", length = 2)
	public String getDealtype() {
		return this.dealtype;
	}

	public void setDealtype(String dealtype) {
		this.dealtype = dealtype;
	}

	@Column(name = "RELABILLSID")
	public byte[] getRelabillsid() {
		return this.relabillsid;
	}

	public void setRelabillsid(byte[] relabillsid) {
		this.relabillsid = relabillsid;
	}

	@Column(name = "RELABILLSNO", length = 30)
	public String getRelabillsno() {
		return this.relabillsno;
	}

	public void setRelabillsno(String relabillsno) {
		this.relabillsno = relabillsno;
	}

	@Column(name = "AMOUNT", precision = 10, scale = 0)
	public double getAmount() {
		return this.amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Column(name = "OCCURDATE")
	public Date getOccurdate() {
		return this.occurdate;
	}

	public void setOccurdate(Date occurdate) {
		this.occurdate = occurdate;
	}

}