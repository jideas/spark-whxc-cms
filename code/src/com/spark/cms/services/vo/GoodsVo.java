package com.spark.cms.services.vo;

import java.util.Date;

import javax.persistence.Column;


/**
 * CmsGoods entity.
 * 
 * @author MyEclipse Persistence Tools
 */ 
@SuppressWarnings("serial")
public class GoodsVo implements java.io.Serializable {

	// Fields

	private String recid;
	private Long recver;
	private String goodscode;
	private String goodsno;
	private String goodsname;
	private String goodsspec;
	private String goodsunit;
	private double originalprice;
	private double realprice;
	private String vantagestype;
	private boolean freedelivery;
	/**
	 * 一级分类ID
	 */
	private String categoryid1;
	/**
	 * 二级分类ID
	 */
	private String categoryid2;
	/**
	 * 三级分类ID
	 */
	private String categoryid3;
	private String propertiy1;
	private String propertiy2;
	private String propertiy3;
	private String propertiy4;
	private String propertiy5;
	private String picturepath1;
	private String picturepath2;
	private String picturepath3;
	private double saledCount;
	private double inventoryCount;
	private boolean isMostSales;
	private boolean isPopular;
	private boolean isPublished = false;
	private boolean isVantagesGoods;
	private double vantagesCost; 
	private String goodsType;
	private byte[] publishPersonId;
	private String publishPersonName;
	private Date publishDate;
	
	private boolean isPromotion;
	// Constructors

	public String getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}

	@Column(name = "isVantagesGoods")
	public boolean isVantagesGoods() {
		return isVantagesGoods;
	}

	public void setVantagesGoods(boolean isVantagesGoods) {
		this.isVantagesGoods = isVantagesGoods;
	}

	@Column(name = "vantagesCost")
	public double getVantagesCost() {
		return vantagesCost;
	}

	public void setVantagesCost(double vantagesCost) {
		this.vantagesCost = vantagesCost;
	}

	// Constructors

	/** default constructor */
	public GoodsVo() {
	}

	/** minimal constructor */
	public GoodsVo(String recid) {
		this.recid = recid;
	}

	/** full constructor */
	public GoodsVo(String recid, Long recver, String goodscode, String goodsno, String goodsname,
			String goodsspec, String goodsunit, double originalprice, double realprice, String vantagestype,
			boolean freedelivery, String categoryid1, String categoryid2, String categoryid3, String propertiy1,
			String propertiy2, String propertiy3, String propertiy4, String propertiy5, String picturepath1,
			String picturepath2, String picturepath3, double saledCount, double inventoryCount, boolean isMostSales,
			boolean isPopular, boolean isPublished, byte[] publishPersonId, String publishPersonName, Date publishDate) {
		this.recid = recid;
		this.recver = recver;
		this.goodscode = goodscode;
		this.goodsno = goodsno;
		this.goodsname = goodsname;
		this.goodsspec = goodsspec;
		this.goodsunit = goodsunit;
		this.originalprice = originalprice;
		this.realprice = realprice;
		this.vantagestype = vantagestype;
		this.freedelivery = freedelivery;
		this.categoryid1 = categoryid1;
		this.categoryid2 = categoryid2;
		this.categoryid3 = categoryid3;
		this.propertiy1 = propertiy1;
		this.propertiy2 = propertiy2;
		this.propertiy3 = propertiy3;
		this.propertiy4 = propertiy4;
		this.propertiy5 = propertiy5;
		this.picturepath1 = picturepath1;
		this.picturepath2 = picturepath2;
		this.picturepath3 = picturepath3;
		this.saledCount = saledCount;
		this.inventoryCount = inventoryCount;
		this.isMostSales = isMostSales;
		this.isPopular = isPopular;
		this.isPublished = isPublished;
		this.publishPersonId = publishPersonId;
		this.publishDate = publishDate;
		this.publishPersonName = publishPersonName;
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

	public String getGoodscode() {
		return this.goodscode;
	}

	public void setGoodscode(String goodscode) {
		this.goodscode = goodscode;
	}

	public String getGoodsno() {
		return this.goodsno;
	}

	public void setGoodsno(String goodsno) {
		this.goodsno = goodsno;
	}

	public String getGoodsname() {
		return this.goodsname;
	}

	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}

	public String getGoodsspec() {
		return this.goodsspec;
	}

	public void setGoodsspec(String goodsspec) {
		this.goodsspec = goodsspec;
	}

	public String getGoodsunit() {
		return this.goodsunit;
	}

	public void setGoodsunit(String goodsunit) {
		this.goodsunit = goodsunit;
	}

	public double getOriginalprice() {
		return this.originalprice;
	}

	public void setOriginalprice(double originalprice) {
		this.originalprice = originalprice;
	}

	public double getRealprice() {
		return this.realprice;
	}

	public void setRealprice(double realprice) {
		this.realprice = realprice;
	}

	public String getVantagestype() {
		return this.vantagestype;
	}

	public void setVantagestype(String vantagestype) {
		this.vantagestype = vantagestype;
	}
	public boolean isFreedelivery() {
		return freedelivery;
	}

	public void setFreedelivery(boolean freedelivery) {
		this.freedelivery = freedelivery;
	}

	public String getCategoryid1() {
		return this.categoryid1;
	}

	public void setCategoryid1(String categoryid1) {
		this.categoryid1 = categoryid1;
	}

	public String getCategoryid2() {
		return this.categoryid2;
	}

	public void setCategoryid2(String categoryid2) {
		this.categoryid2 = categoryid2;
	}

	public String getCategoryid3() {
		return this.categoryid3;
	}

	public void setCategoryid3(String categoryid3) {
		this.categoryid3 = categoryid3;
	}

	public String getPropertiy1() {
		return this.propertiy1;
	}

	public void setPropertiy1(String propertiy1) {
		this.propertiy1 = propertiy1;
	}

	public String getPropertiy2() {
		return this.propertiy2;
	}

	public void setPropertiy2(String propertiy2) {
		this.propertiy2 = propertiy2;
	}

	public String getPropertiy3() {
		return this.propertiy3;
	}

	public void setPropertiy3(String propertiy3) {
		this.propertiy3 = propertiy3;
	}

	public String getPropertiy4() {
		return this.propertiy4;
	}

	public void setPropertiy4(String propertiy4) {
		this.propertiy4 = propertiy4;
	}

	public String getPropertiy5() {
		return this.propertiy5;
	}

	public void setPropertiy5(String propertiy5) {
		this.propertiy5 = propertiy5;
	}

	public String getPicturepath1() {
		return this.picturepath1;
	}

	public void setPicturepath1(String picturepath1) {
		this.picturepath1 = picturepath1;
	}

	public String getPicturepath2() {
		return this.picturepath2;
	}

	public void setPicturepath2(String picturepath2) {
		this.picturepath2 = picturepath2;
	}

	public String getPicturepath3() {
		return this.picturepath3;
	}

	public void setPicturepath3(String picturepath3) {
		this.picturepath3 = picturepath3;
	}

	public double getSaledCount() {
		return saledCount;
	}

	public void setSaledCount(double saledCount) {
		this.saledCount = saledCount;
	}

	public double getInventoryCount() {
		return inventoryCount;
	}

	public void setInventoryCount(double inventoryCount) {
		this.inventoryCount = inventoryCount;
	}

	public boolean isMostSales() {
		return isMostSales;
	}

	public void setMostSales(boolean isMostSales) {
		this.isMostSales = isMostSales;
	}

	public boolean isPopular() {
		return isPopular;
	}

	public void setPopular(boolean isPopular) {
		this.isPopular = isPopular;
	}

	public boolean isPublished() {
		return isPublished;
	}

	public void setPublished(boolean isPublished) {
		this.isPublished = isPublished;
	}

	public byte[] getPublishPersonId() {
		return publishPersonId;
	}

	public void setPublishPersonId(byte[] publishPersonId) {
		this.publishPersonId = publishPersonId;
	}

	public String getPublishPersonName() {
		return publishPersonName;
	}

	public void setPublishPersonName(String publishPersonName) {
		this.publishPersonName = publishPersonName;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public boolean isPromotion() {
		return isPromotion;
	}

	public void setPromotion(boolean isPromotion) {
		this.isPromotion = isPromotion;
	}
	
	
	
}