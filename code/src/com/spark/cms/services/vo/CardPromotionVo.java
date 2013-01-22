/**
 * 
 */
package com.spark.cms.services.vo;

import java.io.Serializable;

/**
 * @author Jideas
 * 
 */
@SuppressWarnings("serial")
public class CardPromotionVo implements Serializable {
	private String recid;
	private Long recver;
	private double amount;
	private String goodsId;
	private double goodsCount = 0;
	private String goodsName;
	private double vantages = 0;
	private boolean activing;
	private double extraAmount;

	public String getRecid() {
		return recid;
	}

	public Long getRecver() {
		return recver;
	}

	public double getAmount() {
		return amount;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public double getGoodsCount() {
		return goodsCount;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public double getVantages() {
		return vantages;
	}

	public boolean isActiving() {
		return activing;
	}

	public void setRecid(String recid) {
		this.recid = recid;
	}

	public void setRecver(Long recver) {
		this.recver = recver;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public void setGoodsCount(double goodsCount) {
		this.goodsCount = goodsCount;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public void setVantages(double vantages) {
		this.vantages = vantages;
	}

	public void setActiving(boolean activing) {
		this.activing = activing;
	}

	public double getExtraAmount() {
		return extraAmount;
	}

	public void setExtraAmount(double extraAmount) {
		this.extraAmount = extraAmount;
	}
}
