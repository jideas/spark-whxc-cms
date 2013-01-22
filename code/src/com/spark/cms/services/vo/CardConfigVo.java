package com.spark.cms.services.vo;

import java.util.Date;

/**
 * CmsCardConfig entity.
 * 
 * @author MyEclipse Persistence Tools
 */ 
@SuppressWarnings("serial")
public class CardConfigVo implements java.io.Serializable {

	// Fields

	private String recid;
	private Long recver;
	private Double amount;
	private String promotiontype;
	private String giftid;
	private Long realvalue;
	private Date giftlastdate;
	private Date begindate;
	private Date enddate;

	// Constructors

	/** default constructor */
	public CardConfigVo() {
	}

	/** minimal constructor */
	public CardConfigVo(String recid) {
		this.recid = recid;
	}

	/** full constructor */
	public CardConfigVo(String recid, Long recver, Double amount, String promotiontype, String giftid,
			Long realvalue, Date giftlastdate, Date begindate, Date enddate) {
		this.recid = recid;
		this.recver = recver;
		this.amount = amount;
		this.promotiontype = promotiontype;
		this.giftid = giftid;
		this.realvalue = realvalue;
		this.giftlastdate = giftlastdate;
		this.begindate = begindate;
		this.enddate = enddate;
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

	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getPromotiontype() {
		return this.promotiontype;
	}

	public void setPromotiontype(String promotiontype) {
		this.promotiontype = promotiontype;
	}

	public String getGiftid() {
		return this.giftid;
	}

	public void setGiftid(String giftid) {
		this.giftid = giftid;
	}

	public Long getRealvalue() {
		return this.realvalue;
	}

	public void setRealvalue(Long realvalue) {
		this.realvalue = realvalue;
	}

	public Date getGiftlastdate() {
		return this.giftlastdate;
	}

	public void setGiftlastdate(Date giftlastdate) {
		this.giftlastdate = giftlastdate;
	}

	public Date getBegindate() {
		return this.begindate;
	}

	public void setBegindate(Date begindate) {
		this.begindate = begindate;
	}

	public Date getEnddate() {
		return this.enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

}