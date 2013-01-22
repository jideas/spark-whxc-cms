package com.spark.cms.dao.po;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * CmsCardConfig entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "cms_card_config")
@SuppressWarnings("serial")
public class CardConfigPo implements java.io.Serializable {

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
	public CardConfigPo() {
	}

	/** minimal constructor */
	public CardConfigPo(String recid) {
		this.recid = recid;
	}

	/** full constructor */
	public CardConfigPo(String recid, Long recver, Double amount, String promotiontype, String giftid,
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
	@Id
	@Column(name = "RECID", unique = true, nullable = false)
	public String getRecid() {
		return this.recid;
	}

	public void setRecid(String recid) {
		this.recid = recid;
	}

	@Column(name = "RECVER")
	public Long getRecver() {
		return this.recver;
	}

	public void setRecver(Long recver) {
		this.recver = recver;
	}

	@Column(name = "AMOUNT", precision = 10)
	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Column(name = "PROMOTIONTYPE", length = 2)
	public String getPromotiontype() {
		return this.promotiontype;
	}

	public void setPromotiontype(String promotiontype) {
		this.promotiontype = promotiontype;
	}

	@Column(name = "GIFTID")
	public String getGiftid() {
		return this.giftid;
	}

	public void setGiftid(String giftid) {
		this.giftid = giftid;
	}

	@Column(name = "REALVALUE", precision = 10, scale = 0)
	public Long getRealvalue() {
		return this.realvalue;
	}

	public void setRealvalue(Long realvalue) {
		this.realvalue = realvalue;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "GIFTLASTDATE", length = 19)
	public Date getGiftlastdate() {
		return this.giftlastdate;
	}

	public void setGiftlastdate(Date giftlastdate) {
		this.giftlastdate = giftlastdate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "BEGINDATE", length = 19)
	public Date getBegindate() {
		return this.begindate;
	}

	public void setBegindate(Date begindate) {
		this.begindate = begindate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "ENDDATE", length = 19)
	public Date getEnddate() {
		return this.enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

}