/**
 * 
 */
package com.spark.cms.dao.po;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Jideas
 *
 */
@Entity
@Table(name = "cms_card_promotion")
@SuppressWarnings("serial")
public class CardPromotionPo implements Serializable {
	private byte[] recid;
	private Long recver;
	private double amount;
	private byte[]goodsId;
	private double goodsCount=0;
	private String goodsName;
	private double vantages;
	private boolean activing;
	private double extraAmount;
	@Id
	@Column(name = "RECID", unique = true, nullable = false)
	public byte[] getRecid() {
		return recid;
	}
	@Column(name = "RECVER")
	public Long getRecver() {
		return recver;
	}
	@Column(name = "amount")
	public double getAmount() {
		return amount;
	}
	@Column(name = "goodsId")
	public byte[] getGoodsId() {
		return goodsId;
	}
	@Column(name = "goodsCount")
	public double getGoodsCount() {
		return goodsCount;
	}
	@Column(name = "goodsName")
	public String getGoodsName() {
		return goodsName;
	}
	@Column(name = "vantages")
	public double getVantages() {
		return vantages;
	}
	@Column(name = "activing")
	public boolean isActiving() {
		return activing;
	}
	public void setRecid(byte[] recid) {
		this.recid = recid;
	}
	public void setRecver(Long recver) {
		this.recver = recver;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public void setGoodsId(byte[] goodsId) {
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
	@Column(name = "extraAmount")
	public double getExtraAmount() {
		return extraAmount;
	}
	public void setExtraAmount(double extraAmount) {
		this.extraAmount = extraAmount;
	}

}
