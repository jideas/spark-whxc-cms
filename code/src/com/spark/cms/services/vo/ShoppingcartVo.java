package com.spark.cms.services.vo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * CmsShoppingcart entity.
 * 
 * @author MyEclipse Persistence Tools
 */ 
@SuppressWarnings("serial")
public class ShoppingcartVo implements java.io.Serializable {

	// Fields

	private String recid;
	private Long recver;
	private String memberid;
	private String goodsid;
	private Double goodscount;
	private Integer ordinal;
	private Date createdate; 

	// Constructors

	/** default constructor */
	public ShoppingcartVo() {
	}

	/** minimal constructor */
	public ShoppingcartVo(String recid) {
		this.recid = recid;
	}

	/** full constructor */
	public ShoppingcartVo(String recid, Long recver, String memberid, String goodsid, Double goodscount,
			Integer ordinal, Date createdate ) {
		this.recid = recid;
		this.recver = recver;
		this.memberid = memberid;
		this.goodsid = goodsid;
		this.goodscount = goodscount;
		this.ordinal = ordinal;
		this.createdate = createdate; 
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

	@Column(name = "MEMBERID")
	public String getMemberid() {
		return this.memberid;
	}

	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}

	@Column(name = "GOODSID")
	public String getGoodsid() {
		return this.goodsid;
	}

	public void setGoodsid(String goodsid) {
		this.goodsid = goodsid;
	}

	@Column(name = "GOODSCOUNT", precision = 10)
	public Double getGoodscount() {
		return this.goodscount;
	}

	public void setGoodscount(Double goodscount) {
		this.goodscount = goodscount;
	}

	@Column(name = "ORDINAL")
	public Integer getOrdinal() {
		return this.ordinal;
	}

	public void setOrdinal(Integer ordinal) {
		this.ordinal = ordinal;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CREATEDATE", length = 19)
	public Date getCreatedate() {
		return this.createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
}