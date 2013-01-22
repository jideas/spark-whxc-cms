package com.spark.cms.services.vo;


/**
 * CmsOrderPromotion entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
public class OrderPromotionVo implements java.io.Serializable {

	// Fields

	private String recid;
	private Long recver;
	private boolean isactiving;
	private Double beginamount;
	private Double endamount;
	private String goodsid;
	private Double goodscount;
	private Long vantages;
	private boolean isfreedelivery;

	// Constructors

	/** default constructor */
	public OrderPromotionVo() {
	}

	/** minimal constructor */
	public OrderPromotionVo(String recid) {
		this.recid = recid;
	}

	/** full constructor */
	public OrderPromotionVo(String recid, Long recver, boolean isactiving, Double beginamount, Double endamount, String goodsid,
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

	public boolean isIsactiving() {
		return isactiving;
	}

	public boolean isIsfreedelivery() {
		return isfreedelivery;
	}

	public void setIsactiving(boolean isactiving) {
		this.isactiving = isactiving;
	}

	public Double getBeginamount() {
		return this.beginamount;
	}

	public void setBeginamount(Double beginamount) {
		this.beginamount = beginamount;
	}

	public Double getEndamount() {
		return this.endamount;
	}

	public void setEndamount(Double endamount) {
		this.endamount = endamount;
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

	public Long getVantages() {
		return this.vantages;
	}

	public void setVantages(Long vantages) {
		this.vantages = vantages;
	}

	public void setIsfreedelivery(boolean isfreedelivery) {
		this.isfreedelivery = isfreedelivery;
	}

}