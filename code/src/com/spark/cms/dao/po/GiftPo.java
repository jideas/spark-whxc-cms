package com.spark.cms.dao.po;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * CmsGift entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "cms_gift")
@SuppressWarnings("serial")
public class GiftPo implements java.io.Serializable {

	// Fields

	private byte[] recid;
	private Long recver;
	private byte[] memberid;
	private byte[] goodsid;
	private Double goodscount;
	private String reason;
	private Date lastdate;
	private String status;

	// Constructors

	/** default constructor */
	public GiftPo() {
	}

	/** minimal constructor */
	public GiftPo( byte[] recid) {
		this.recid = recid;
	}

	/** full constructor */
	public GiftPo( byte[] recid, Long recver,  byte[] memberid,  byte[] goodsid, Double goodscount,
			String reason, Date lastdate, String status) {
		this.recid = recid;
		this.recver = recver;
		this.memberid = memberid;
		this.goodsid = goodsid;
		this.goodscount = goodscount;
		this.reason = reason;
		this.lastdate = lastdate;
		this.status = status;
	}

	// Property accessors
	@Id
	@Column(name = "RECID", unique = true, nullable = false)
	public  byte[] getRecid() {
		return this.recid;
	}

	public void setRecid( byte[] recid) {
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
	public  byte[] getMemberid() {
		return this.memberid;
	}

	public void setMemberid( byte[] memberid) {
		this.memberid = memberid;
	}

	@Column(name = "GOODSID")
	public  byte[] getGoodsid() {
		return this.goodsid;
	}

	public void setGoodsid( byte[] goodsid) {
		this.goodsid = goodsid;
	}

	@Column(name = "GOODSCOUNT", precision = 10)
	public Double getGoodscount() {
		return this.goodscount;
	}

	public void setGoodscount(Double goodscount) {
		this.goodscount = goodscount;
	}

	@Column(name = "REASON", length = 300)
	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "LASTDATE", length = 19)
	public Date getLastdate() {
		return this.lastdate;
	}

	public void setLastdate(Date lastdate) {
		this.lastdate = lastdate;
	}

	@Column(name = "STATUS", length = 2)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}