package com.spark.cms.dao.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CmsGoodsPromotion entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "cms_goods_promotion")
@SuppressWarnings("serial")
public class GoodsPromotionPo implements java.io.Serializable {

	// Fields

	private byte[] recid;
	private Long recver;
	private byte[] goodsid;
	private Long begintime;
	private Long endtime;
	private Double disrate;
	private Double pcount;
	private boolean isactiving;
	private Double saledcount;

	// Constructors

	/** default constructor */
	public GoodsPromotionPo() {
	}

	/** minimal constructor */
	public GoodsPromotionPo(byte[] recid) {
		this.recid = recid;
	}

	/** full constructor */
	public GoodsPromotionPo(byte[] recid, Long recver, byte[] goodsid, Long begintime, Long endtime, Double disrate,
			Double pcount, boolean isactiving, Double saledcount) {
		this.recid = recid;
		this.recver = recver;
		this.goodsid = goodsid;
		this.begintime = begintime;
		this.endtime = endtime;
		this.disrate = disrate;
		this.pcount = pcount;
		this.isactiving = isactiving;
		this.saledcount = saledcount;
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

	@Column(name = "GOODSID")
	public byte[] getGoodsid() {
		return this.goodsid;
	}

	public void setGoodsid(byte[] goodsid) {
		this.goodsid = goodsid;
	}

	@Column(name = "BEGINTIME", length = 19)
	public Long getBegintime() {
		return this.begintime;
	}

	public void setBegintime(Long begintime) {
		this.begintime = begintime;
	}

	@Column(name = "ENDTIME", length = 19)
	public Long getEndtime() {
		return this.endtime;
	}

	public void setEndtime(Long endtime) {
		this.endtime = endtime;
	}

	@Column(name = "DISRATE", precision = 3)
	public Double getDisrate() {
		return this.disrate;
	}

	public void setDisrate(Double disrate) {
		this.disrate = disrate;
	}

	@Column(name = "PCOUNT", precision = 10)
	public Double getPcount() {
		return this.pcount;
	}

	public void setPcount(Double pcount) {
		this.pcount = pcount;
	}

	@Column(name = "ISACTIVING")
	public boolean isIsactiving() {
		return this.isactiving;
	}

	public void setIsactiving(boolean isactiving) {
		this.isactiving = isactiving;
	}

	@Column(name = "SALEDCOUNT", precision = 10)
	public Double getSaledcount() {
		return this.saledcount;
	}

	public void setSaledcount(Double saledcount) {
		this.saledcount = saledcount;
	}

}