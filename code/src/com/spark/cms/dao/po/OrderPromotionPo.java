package com.spark.cms.dao.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CmsOrderPromotion entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "cms_order_promotion")
@SuppressWarnings("serial")
public class OrderPromotionPo implements java.io.Serializable {

	// Fields

	private byte[] recid;
	private Long recver;
	private boolean isactiving;
	private Double beginamount;
	private Double endamount;
	private byte[] goodsid;
	private Double goodscount;
	private Long vantages;
	private boolean isfreedelivery;

	// Constructors

	/** default constructor */
	public OrderPromotionPo() {
	}

	/** minimal constructor */
	public OrderPromotionPo(byte[] recid) {
		this.recid = recid;
	}

	/** full constructor */
	public OrderPromotionPo(byte[] recid, Long recver, boolean isactiving, Double beginamount, Double endamount, byte[] goodsid,
			Double goodscount, Long vantages, boolean isfreedelivery) {
		this.recid = recid;
		this.recver = recver;
		this.isactiving = isactiving;
		this.beginamount = beginamount;
		this.endamount = endamount;
		this.goodsid = goodsid;
		this.goodscount = goodscount;
		this.vantages = vantages;
		this.isfreedelivery = isfreedelivery;
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

	@Column(name = "ISACTIVING")
	public boolean isIsactiving() {
		return this.isactiving;
	}

	public void setIsactiving(boolean isactiving) {
		this.isactiving = isactiving;
	}

	@Column(name = "BEGINAMOUNT", precision = 10)
	public Double getBeginamount() {
		return this.beginamount;
	}

	public void setBeginamount(Double beginamount) {
		this.beginamount = beginamount;
	}

	@Column(name = "ENDAMOUNT", precision = 10)
	public Double getEndamount() {
		return this.endamount;
	}

	public void setEndamount(Double endamount) {
		this.endamount = endamount;
	}

	@Column(name = "GOODSID")
	public byte[] getGoodsid() {
		return this.goodsid;
	}

	public void setGoodsid(byte[] goodsid) {
		this.goodsid = goodsid;
	}

	@Column(name = "GOODSCOUNT", precision = 10)
	public Double getGoodscount() {
		return this.goodscount;
	}

	public void setGoodscount(Double goodscount) {
		this.goodscount = goodscount;
	}

	@Column(name = "VANTAGES", precision = 10, scale = 0)
	public Long getVantages() {
		return this.vantages;
	}

	public void setVantages(Long vantages) {
		this.vantages = vantages;
	}

	public void setIsfreedelivery(boolean isfreedelivery) {
		this.isfreedelivery = isfreedelivery;
	}

	@Column(name = "ISFREEDELIVERY")
	public boolean isIsfreedelivery() {
		return isfreedelivery;
	}

}