package com.spark.cms.services.vo;

import java.util.Date;

/**
 * CmsChannelGoods entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
public class ChannelGoodsVo implements java.io.Serializable {

	// Fields

	private String recid;
	private Long recver;
	private String channelid;
	private String creatorid;
	private String creator;
	private Date createdate;
	private String status;
	private String goodsid;
	private Integer ordinal;
	
	private String goodsName;
	private String goodsspec;
	private String goodsunit;
	private double originalprice;
	private double realprice;
	private double saledCount;
	private boolean freedelivery;
	private String picturepath1;
	private String picturepath2;
	private String picturepath3;
	private boolean isPopular;
	private boolean isMostSales;
	private boolean isVantagesGoods;
	private double vantagesCost; 
	private Date publishDate;
	private boolean isPublished;
	// Constructors

	/** default constructor */
	public ChannelGoodsVo() {
	}

	/** minimal constructor */
	public ChannelGoodsVo(String recid) {
		this.recid = recid;
	}

	/** full constructor */
	public ChannelGoodsVo(String recid, Long recver, String channelid, String creatorid, String creator,
			Date createdate, String status, String goodsid, Integer ordinal) {
		this.recid = recid;
		this.recver = recver;
		this.channelid = channelid;
		this.creatorid = creatorid;
		this.creator = creator;
		this.createdate = createdate;
		this.status = status;
		this.goodsid = goodsid;
		this.ordinal = ordinal;
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

	public String getChannelid() {
		return this.channelid;
	}

	public void setChannelid(String channelid) {
		this.channelid = channelid;
	}

	public String getCreatorid() {
		return this.creatorid;
	}

	public void setCreatorid(String creatorid) {
		this.creatorid = creatorid;
	}

	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreatedate() {
		return this.createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getGoodsid() {
		return this.goodsid;
	}

	public void setGoodsid(String goodsid) {
		this.goodsid = goodsid;
	}

	public Integer getOrdinal() {
		return this.ordinal;
	}

	public void setOrdinal(Integer ordinal) {
		this.ordinal = ordinal;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGoodsspec() {
		return goodsspec;
	}

	public void setGoodsspec(String goodsspec) {
		this.goodsspec = goodsspec;
	}

	public String getGoodsunit() {
		return goodsunit;
	}

	public void setGoodsunit(String goodsunit) {
		this.goodsunit = goodsunit;
	}

	public double getOriginalprice() {
		return originalprice;
	}

	public void setOriginalprice(double originalprice) {
		this.originalprice = originalprice;
	}

	public double getRealprice() {
		return realprice;
	}

	public void setRealprice(double realprice) {
		this.realprice = realprice;
	}

	public double getSaledCount() {
		return saledCount;
	}

	public void setSaledCount(double saledCount) {
		this.saledCount = saledCount;
	}

	public boolean isFreedelivery() {
		return freedelivery;
	}

	public void setFreedelivery(boolean freedelivery) {
		this.freedelivery = freedelivery;
	}

	public String getPicturepath1() {
		return picturepath1;
	}

	public void setPicturepath1(String picturepath1) {
		this.picturepath1 = picturepath1;
	}

	public String getPicturepath2() {
		return picturepath2;
	}

	public void setPicturepath2(String picturepath2) {
		this.picturepath2 = picturepath2;
	}

	public String getPicturepath3() {
		return picturepath3;
	}

	public void setPicturepath3(String picturepath3) {
		this.picturepath3 = picturepath3;
	}

	public boolean isPopular() {
		return isPopular;
	}

	public void setPopular(boolean isPopular) {
		this.isPopular = isPopular;
	}

	public boolean isMostSales() {
		return isMostSales;
	}

	public void setMostSales(boolean isMostSales) {
		this.isMostSales = isMostSales;
	}

	public boolean isVantagesGoods() {
		return isVantagesGoods;
	}

	public void setVantagesGoods(boolean isVantagesGoods) {
		this.isVantagesGoods = isVantagesGoods;
	}

	public double getVantagesCost() {
		return vantagesCost;
	}

	public void setVantagesCost(double vantagesCost) {
		this.vantagesCost = vantagesCost;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public boolean isPublished() {
		return isPublished;
	}

	public void setPublished(boolean isPublished) {
		this.isPublished = isPublished;
	}

	
}