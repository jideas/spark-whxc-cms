package com.spark.cms.services.vo;

/**
 * CmsOrderDet entity.
 * 
 * @author MyEclipse Persistence Tools
 */ 
@SuppressWarnings("serial")
public class OrderDetVo implements java.io.Serializable {

	// Fields

	private String recid;  
	private String billsid;
	private String goodsid;
	private String goodscode;
	private String goodsno;
	private String goodsname;
	private String goodsspec;
	private String unit;
	private Integer goodsscale;
	private Double price;
	private Double planprice;
	private Double count;
	private double disrate;
	private Double disamount;
	private Double amount;
	private Double vantages;
	private String vantagesType;
	private double vantagesCost;
	private boolean vantagesGoods;
	private String imgUrl;
	
	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public boolean isVantagesGoods() {
		return vantagesGoods;
	}

	public void setVantagesGoods(boolean vantagesGoods) {
		this.vantagesGoods = vantagesGoods;
	}

	public double getVantagesCost() {
		return vantagesCost;
	}

	public void setVantagesCost(double vantagesCost) {
		this.vantagesCost = vantagesCost;
	}
	
	// Constructors

	public Double getVantages() {
		return vantages;
	}

	public void setVantages(Double vantages) {
		this.vantages = vantages;
	}

	public String getVantagesType() {
		return vantagesType;
	}

	public void setVantagesType(String vantagesType) {
		this.vantagesType = vantagesType;
	}

	/** default constructor */
	public OrderDetVo() {
	}

	/** minimal constructor */
	public OrderDetVo(String recid) {
		this.recid = recid;
	}

	/** full constructor */
	public OrderDetVo(String recid, Long recver, String billsid, String goodsid, String goodscode,
			String goodsno, String goodsname, String goodsspec, String unit, Integer goodsscale,
			Double price, Double planprice, Double count, Long disrate, Double disamount, Double amount,
			String promotionid) {
		this.recid = recid;
		this.billsid = billsid;
		this.goodsid = goodsid;
		this.goodscode = goodscode;
		this.goodsno = goodsno;
		this.goodsname = goodsname;
		this.goodsspec = goodsspec;
		this.unit = unit;
		this.goodsscale = goodsscale;
		this.price = price;
		this.planprice = planprice;
		this.count = count;
		this.disrate = disrate;
		this.disamount = disamount;
		this.amount = amount;
	}

	// Property accessors

	public String getRecid() {
		return this.recid;
	}

	public void setRecid(String recid) {
		this.recid = recid;
	}

	public String getBillsid() {
		return this.billsid;
	}

	public void setBillsid(String billsid) {
		this.billsid = billsid;
	}

	public String getGoodsid() {
		return this.goodsid;
	}

	public void setGoodsid(String goodsid) {
		this.goodsid = goodsid;
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

	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Integer getGoodsscale() {
		return this.goodsscale;
	}

	public void setGoodsscale(Integer goodsscale) {
		this.goodsscale = goodsscale;
	}

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getPlanprice() {
		return this.planprice;
	}

	public void setPlanprice(Double planprice) {
		this.planprice = planprice;
	}

	public Double getCount() {
		return this.count;
	}

	public void setCount(Double count) {
		this.count = count;
	}

	public double getDisrate() {
		return this.disrate;
	}

	public void setDisrate(double disrate) {
		this.disrate = disrate;
	}

	public Double getDisamount() {
		return this.disamount;
	}

	public void setDisamount(Double disamount) {
		this.disamount = disamount;
	}

	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

}