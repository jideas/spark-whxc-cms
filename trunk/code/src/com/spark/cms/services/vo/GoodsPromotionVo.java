package com.spark.cms.services.vo;

/**
 * CmsGoodsPromotion entity.
 * 
 * @author MyEclipse Persistence Tools
 */ 
@SuppressWarnings("serial")
public class GoodsPromotionVo implements java.io.Serializable {

	// Fields

	private String recid;
	private Long recver;
	private String goodsid;
	private Long begintime;
	private Long endtime;
	private Double disrate;
	private Double pcount;
	private boolean isactiving;
	private Double saledcount = 0d;

	// Constructors

	/** default constructor */
	public GoodsPromotionVo() {
	}

	/** minimal constructor */
	public GoodsPromotionVo(String recid) {
		this.recid = recid;
	}

	/** full constructor */
	public GoodsPromotionVo(String recid, Long recver, String goodsid, Long begintime, Long endtime,
			Double disrate, Double pcount, boolean isactiving, Double saledcount) {
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

	public String getGoodsid() {
		return this.goodsid;
	}

	public void setGoodsid(String goodsid) {
		this.goodsid = goodsid;
	}

	public Long getBegintime() {
		return this.begintime;
	}

	public void setBegintime(Long begintime) {
		this.begintime = begintime;
	}

	public Long getEndtime() {
		return this.endtime;
	}

	public void setEndtime(Long endtime) {
		this.endtime = endtime;
	}

	public Double getDisrate() {
		return this.disrate;
	}

	public void setDisrate(Double disrate) {
		this.disrate = disrate;
	}

	public Double getPcount() {
		return this.pcount;
	}

	public void setPcount(Double pcount) {
		this.pcount = pcount;
	}

	public boolean isIsactiving() {
		return this.isactiving;
	}

	public void setIsactiving(boolean isactiving) {
		this.isactiving = isactiving;
	}

	public Double getSaledcount() {
		return this.saledcount;
	}

	public void setSaledcount(Double saledcount) {
		this.saledcount = saledcount;
	}

}