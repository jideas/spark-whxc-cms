package com.spark.cms.dao.po;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CmsGoods entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "cms_goods")
@SuppressWarnings("serial")
public class GoodsPo implements java.io.Serializable {

	// Fields

	private byte[] recid;
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
	private byte[] categoryid1;
	private byte[] categoryid2;
	private byte[] categoryid3;
	private String propertiy1;
	private String propertiy2;
	private String propertiy3;
	private String propertiy4;
	private String propertiy5;
	private String picturepath1;
	private String picturepath2;
	private String picturepath3;
	private double saledCount;// 已销数量
	private double inventoryCount;// 库存数量
	private boolean isMostSales;// 是否热卖
	private boolean isPopular;// 是否人气
	private boolean isPublished = false;
	private boolean isVantagesGoods = false;
	private double vantagesCost; 
	private String goodsType;
	private byte[] publishPersonId;
	private String publishPersonName;
	private Date publishDate;
	
	private boolean isPromotion;
	
	// Constructors
	@Column(name = "goodsType")
	public String getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}
	// Constructors

	@Column(name = "isVantagesGoods")
	public boolean isVantagesGoods() {
		return isVantagesGoods;
	}

	public void setVantagesGoods(boolean isVantagesGoods) {
		this.isVantagesGoods = isVantagesGoods;
	}

	@Column(name = "vantagesCost",scale=0)
	public double getVantagesCost() {
		return vantagesCost;
	}

	public void setVantagesCost(double vantagesCost) {
		this.vantagesCost = vantagesCost;
	}

	/** default constructor */
	public GoodsPo() {
	}

	/** minimal constructor */
	public GoodsPo(byte[] recid) {
		this.recid = recid;
	}

	/** full constructor */
	public GoodsPo(byte[] recid, Long recver, String goodscode, String goodsno, String goodsname,
			String goodsspec, String goodsunit, double originalprice, double realprice, String vantagestype,
			boolean freedelivery, byte[] categoryid1, byte[] categoryid2, byte[] categoryid3, String propertiy1,
			String propertiy2, String propertiy3, String propertiy4, String propertiy5, String picturepath1,
			String picturepath2, String picturepath3, double saledCount, double inventoryCount,
			boolean isMostSales, boolean isPopular, boolean isPublished, byte[] publishPersonId, 
			String publishPersonName, Date publishDate, boolean isPromotion) {
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
		this.isPromotion = isPromotion;
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

	@Column(name = "GOODSCODE")
	public String getGoodscode() {
		return this.goodscode;
	}

	public void setGoodscode(String goodscode) {
		this.goodscode = goodscode;
	}

	@Column(name = "GOODSNO", length = 30)
	public String getGoodsno() {
		return this.goodsno;
	}

	public void setGoodsno(String goodsno) {
		this.goodsno = goodsno;
	}

	@Column(name = "GOODSNAME", length = 100)
	public String getGoodsname() {
		return this.goodsname;
	}

	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}

	@Column(name = "GOODSSPEC", length = 50)
	public String getGoodsspec() {
		return this.goodsspec;
	}

	public void setGoodsspec(String goodsspec) {
		this.goodsspec = goodsspec;
	}

	@Column(name = "GOODSUNIT", length = 20)
	public String getGoodsunit() {
		return this.goodsunit;
	}

	public void setGoodsunit(String goodsunit) {
		this.goodsunit = goodsunit;
	}

	@Column(name = "ORIGINALPRICE", precision = 17,scale=2)
	public double getOriginalprice() {
		return this.originalprice;
	}

	public void setOriginalprice(double originalprice) {
		this.originalprice = originalprice;
	}

	@Column(name = "REALPRICE", precision = 17,scale=2)
	public double getRealprice() {
		return this.realprice;
	}

	public void setRealprice(double realprice) {
		this.realprice = realprice;
	}

	@Column(name = "VANTAGESTYPE", length = 2)
	public String getVantagestype() {
		return this.vantagestype;
	}

	public void setVantagestype(String vantagestype) {
		this.vantagestype = vantagestype;
	}

//	@Column(name = "FREEDELIVERY")
//	public boolean getFreedelivery() {
//		return this.freedelivery;
//	}
//
//	public void setFreedelivery(boolean freedelivery) {
//		this.freedelivery = freedelivery;
//	}
	@Column(name = "FREEDELIVERY")
	public boolean isFreedelivery() {
		return freedelivery;
	}

	public void setFreedelivery(boolean freedelivery) {
		this.freedelivery = freedelivery;
	}
	@Column(name = "CATEGORYID1")
	public byte[] getCategoryid1() {
		return this.categoryid1;
	}

	public void setCategoryid1(byte[] categoryid1) {
		this.categoryid1 = categoryid1;
	}

	@Column(name = "CATEGORYID2")
	public byte[] getCategoryid2() {
		return this.categoryid2;
	}

	public void setCategoryid2(byte[] categoryid2) {
		this.categoryid2 = categoryid2;
	}

	@Column(name = "CATEGORYID3")
	public byte[] getCategoryid3() {
		return this.categoryid3;
	}

	public void setCategoryid3(byte[] categoryid3) {
		this.categoryid3 = categoryid3;
	}

	@Column(name = "PROPERTIY1", length = 30)
	public String getPropertiy1() {
		return this.propertiy1;
	}

	public void setPropertiy1(String propertiy1) {
		this.propertiy1 = propertiy1;
	}

	@Column(name = "PROPERTIY2", length = 30)
	public String getPropertiy2() {
		return this.propertiy2;
	}

	public void setPropertiy2(String propertiy2) {
		this.propertiy2 = propertiy2;
	}

	@Column(name = "PROPERTIY3", length = 30)
	public String getPropertiy3() {
		return this.propertiy3;
	}

	public void setPropertiy3(String propertiy3) {
		this.propertiy3 = propertiy3;
	}

	@Column(name = "PROPERTIY4", length = 30)
	public String getPropertiy4() {
		return this.propertiy4;
	}

	public void setPropertiy4(String propertiy4) {
		this.propertiy4 = propertiy4;
	}

	@Column(name = "PROPERTIY5", length = 30)
	public String getPropertiy5() {
		return this.propertiy5;
	}

	public void setPropertiy5(String propertiy5) {
		this.propertiy5 = propertiy5;
	}

	@Column(name = "PICTUREPATH1", length = 300)
	public String getPicturepath1() {
		return this.picturepath1;
	}

	public void setPicturepath1(String picturepath1) {
		this.picturepath1 = picturepath1;
	}

	@Column(name = "PICTUREPATH2", length = 300)
	public String getPicturepath2() {
		return this.picturepath2;
	}

	public void setPicturepath2(String picturepath2) {
		this.picturepath2 = picturepath2;
	}

	@Column(name = "PICTUREPATH3", length = 300)
	public String getPicturepath3() {
		return this.picturepath3;
	}

	public void setPicturepath3(String picturepath3) {
		this.picturepath3 = picturepath3;
	}
	@Column(name = "saledCount",scale=0)
	public double getSaledCount() {
		return saledCount;
	}

	public void setSaledCount(double saledCount) {
		this.saledCount = saledCount;
	}
	@Column(name = "inventoryCount")
	public double getInventoryCount() {
		return inventoryCount;
	}

	public void setInventoryCount(double inventoryCount) {
		this.inventoryCount = inventoryCount;
	}
//	@Column(name = "isMostSales")
//	public boolean isMostSales() {
//		return isMostSales;
//	}
//
//	public void setMostSales(boolean isMostSales) {
//		this.isMostSales = isMostSales;
//	}
//	@Column(name = "isPopular")
//	public boolean isPopular() {
//		return isPopular;
//	}
//
//	public void setPopular(boolean isPopular) {
//		this.isPopular = isPopular;
//	}
//	@Column(name = "isPublished")
//	public boolean getIsPublished() {
//		return isPublished;
//	}
//
//	public void setIsPublished(boolean isPublished) {
//		this.isPublished = isPublished;
//	}
	@Column(name = "isMostSales")
	public boolean isMostSales() {
		return isMostSales;
	}

	public void setMostSales(boolean isMostSales) {
		this.isMostSales = isMostSales;
	}
	@Column(name = "isPopular")
	public boolean isPopular() {
		return isPopular;
	}

	public void setPopular(boolean isPopular) {
		this.isPopular = isPopular;
	}
	@Column(name = "ISPUBLISHED")
	public boolean isPublished() {
		return isPublished;
	}

	public void setPublished(boolean isPublished) {
		this.isPublished = isPublished;
	}

	@Column(name = "PUBLISHPERSONID")
	public byte[] getPublishPersonId() {
		return publishPersonId;
	}

	public void setPublishPersonId(byte[] publishPersonId) {
		this.publishPersonId = publishPersonId;
	}
	@Column(name = "PUBLISHPERSONNAME")
	public String getPublishPersonName() {
		return publishPersonName;
	}

	public void setPublishPersonName(String publishPersonName) {
		this.publishPersonName = publishPersonName;
	}
	
	@Column(name = "PUBLISHDATE")
	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	@Column(name="ISPROMOTION")
	public boolean isPromotion() {
		return isPromotion;
	}

	public void setPromotion(boolean isPromotion) {
		this.isPromotion = isPromotion;
	}
}
