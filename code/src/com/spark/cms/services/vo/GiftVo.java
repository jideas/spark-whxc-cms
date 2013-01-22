package com.spark.cms.services.vo;

import java.util.Date;

/**
 * CmsGift entity.
 * 
 * @author MyEclipse Persistence Tools
 */ 
@SuppressWarnings("serial")
public class GiftVo implements java.io.Serializable {

	// Fields

	private String recid; 
	private Long recver;
	private String memberid;
	private String goodsid;
	private Double goodscount;
	private String reason;
	private Date lastdate;
	private String status;

	// Constructors

	/** default constructor */
	public GiftVo() {
	}

	/** minimal constructor */
	public GiftVo(String recid) {
		this.recid = recid;
	}

	/** full constructor */
	public GiftVo(String recid, Long recver, String memberid, String goodsid, Double goodscount,
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

	public Long getRecver() {
		return recver;
	}

	public void setRecver(Long recver) {
		this.recver = recver;
	}

	public String getRecid() {
		return this.recid;
	}

	public void setRecid(String recid) {
		this.recid = recid;
	} 

	public String getMemberid() {
		return this.memberid;
	}

	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}

	public String getGoodsid() {
		return this.goodsid;
	}

	public void setGoodsid(String goodsid) {
		this.goodsid = goodsid;
	}

	public Double getGoodscount() {
		return this.goodscount;
	}

	public void setGoodscount(Double goodscount) {
		this.goodscount = goodscount;
	}

	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Date getLastdate() {
		return this.lastdate;
	}

	public void setLastdate(Date lastdate) {
		this.lastdate = lastdate;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}