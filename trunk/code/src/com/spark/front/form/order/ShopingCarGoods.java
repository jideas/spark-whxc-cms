package com.spark.front.form.order;

import com.spark.base.common.utils.DoubleUtil;

public class ShopingCarGoods {

	private String goodsId;
	private String goodsCode;
	private String goodsName;
	private String spec;
	private double count;
	private double totalAmount;
	private boolean gift;
	private double price;
	private double vantages;
	private Double disrate;
	private String vantagesType;
	private double originalprice;
	private boolean freedelivery;
	private String miniPicturePath;
	private double vantagesCost;
	private boolean vantagesGoods;
	
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
	public boolean isFreedelivery() {
		return freedelivery;
	}
	public void setFreedelivery(boolean freedelivery) {
		this.freedelivery = freedelivery;
	}
	public Double getDisrate() {
		return disrate;
	}
	public void setDisrate(Double disrate) {
		this.disrate = disrate;
	}
	public double getOriginalprice() {
		return originalprice;
	}
	public void setOriginalprice(double originalprice) {
		this.originalprice = originalprice;
	}
	public String getVantagesType() {
		return vantagesType;
	}
	public void setVantagesType(String vantagesType) {
		this.vantagesType = vantagesType;
	}
	
	public String getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	public String getGoodsCode() {
		return goodsCode;
	}
	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	public double getCount() {
		return count;
	}
	public void setCount(double count) {
		this.count = count;
	}
	public boolean isGift() {
		return gift;
	}
	public void setGift(boolean gift) {
		this.gift = gift;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getVantages() {
		return vantages;
	}
	public void setVantages(double vantages) {
		this.vantages = vantages;
	}
	public double getTotalAmount() {
		totalAmount = DoubleUtil.mul(price, count);
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getMiniPicturePath() {
		return miniPicturePath;
	}
	public void setMiniPicturePath(String miniPicturePath) {
		this.miniPicturePath = miniPicturePath;
	}
	
}
