package com.spark.cms.dao.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CmsOrderDet entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "cms_order_det")
@SuppressWarnings("serial")
public class OrderDetPo implements java.io.Serializable {

	// Fields

	private byte[] recid;
	private Long recver;
	private byte[] billsid;
	private byte[] goodsid;
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
	private byte[] promotionid;
	private Double vantages;
	private String vantagesType;
	private double vantagesCost;

	private boolean vantagesGoods;

	@Column(name = "vantagesGoods")
	public boolean isVantagesGoods() {
		return vantagesGoods;
	}

	public void setVantagesGoods(boolean vantagesGoods) {
		this.vantagesGoods = vantagesGoods;
	}

	@Column(name = "vantagesCost")
	public double getVantagesCost() {
		return vantagesCost;
	}

	public void setVantagesCost(double vantagesCost) {
		this.vantagesCost = vantagesCost;
	}

	// Constructors
	@Column(name = "vantages")
	public Double getVantages() {
		return vantages;
	}

	public void setVantages(Double vantages) {
		this.vantages = vantages;
	}

	@Column(name = "vantagesType")
	public String getVantagesType() {
		return vantagesType;
	}

	public void setVantagesType(String vantagesType) {
		this.vantagesType = vantagesType;
	}

	// Constructors

	/** default constructor */
	public OrderDetPo() {
	}

	/** minimal constructor */
	public OrderDetPo(byte[] recid) {
		this.recid = recid;
	}

	/** full constructor */
	public OrderDetPo(byte[] recid, Long recver, byte[] billsid,
			byte[] goodsid, String goodscode, String goodsno, String goodsname,
			String goodsspec, String unit, Integer goodsscale, Double price,
			Double planprice, Double count, double disrate, Double disamount,
			Double amount, byte[] promotionid) {
		this.recid = recid;
		this.recver = recver;
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
		this.promotionid = promotionid;
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

	@Column(name = "BILLSID")
	public byte[] getBillsid() {
		return this.billsid;
	}

	public void setBillsid(byte[] billsid) {
		this.billsid = billsid;
	}

	@Column(name = "GOODSID")
	public byte[] getGoodsid() {
		return this.goodsid;
	}

	public void setGoodsid(byte[] goodsid) {
		this.goodsid = goodsid;
	}

	@Column(name = "GOODSCODE", length = 30)
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

	@Column(name = "GOODSNAME", length = 50)
	public String getGoodsname() {
		return this.goodsname;
	}

	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}

	@Column(name = "GOODSSPEC", length = 100)
	public String getGoodsspec() {
		return this.goodsspec;
	}

	public void setGoodsspec(String goodsspec) {
		this.goodsspec = goodsspec;
	}

	@Column(name = "UNIT", length = 10)
	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Column(name = "GOODSSCALE")
	public Integer getGoodsscale() {
		return this.goodsscale;
	}

	public void setGoodsscale(Integer goodsscale) {
		this.goodsscale = goodsscale;
	}

	@Column(name = "PRICE", precision = 17)
	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Column(name = "PLANPRICE", precision = 17)
	public Double getPlanprice() {
		return this.planprice;
	}

	public void setPlanprice(Double planprice) {
		this.planprice = planprice;
	}

	@Column(name = "COUNT", precision = 17, scale = 5)
	public Double getCount() {
		return this.count;
	}

	public void setCount(Double count) {
		this.count = count;
	}

	@Column(name = "disrate")
	public double getDisrate() {
		return this.disrate;
	}

	public void setDisrate(double disrate) {
		this.disrate = disrate;
	}

	@Column(name = "DISAMOUNT", precision = 17)
	public Double getDisamount() {
		return this.disamount;
	}

	public void setDisamount(Double disamount) {
		this.disamount = disamount;
	}

	@Column(name = "AMOUNT", precision = 17)
	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Column(name = "PROMOTIONID")
	public byte[] getPromotionid() {
		return this.promotionid;
	}

	public void setPromotionid(byte[] promotionid) {
		this.promotionid = promotionid;
	}

}