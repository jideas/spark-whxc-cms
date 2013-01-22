package com.spark.cms.dao.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "psi_base_goodsitem")
@SuppressWarnings("serial")
public class EGoodsPo implements java.io.Serializable {
	private byte[] recid;
	private String goodsCode;
	private String goodsNo;
	private String goodsName;
	private String spec;
	private String goodsUnit;
	private Double originalPrice;
	private byte[] categoryId;
	private Double salePrice;
	
	public EGoodsPo() {
	}
	
	public EGoodsPo(byte[] recid) {
		this.recid = recid;
	}
	
	public EGoodsPo(byte[] recid, String goodsCode, String goodsNo,
			String goodsName, String spec, String goodsUnit,
			Double originalPrice, byte[] categoryId, Double salePrice) {
		super();
		this.recid = recid;
		this.goodsCode = goodsCode;
		this.goodsNo = goodsNo;
		this.goodsName = goodsName;
		this.spec = spec;
		this.goodsUnit = goodsUnit;
		this.originalPrice = originalPrice;
		this.categoryId = categoryId;
		this.salePrice = salePrice;
	}
	@Id
	@Column(name = "RECID", unique = true, nullable = false)
	public byte[] getRecid() {
		return recid;
	}
	public void setRecid(byte[] recid) {
		this.recid = recid;
	}
	@Column(name = "GOODSCODE")
	public String getGoodsCode() {
		return goodsCode;
	}
	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}
	@Column(name = "GOODSNO", length = 30)
	public String getGoodsNo() {
		return goodsNo;
	}
	public void setGoodsNo(String goodsNo) {
		this.goodsNo = goodsNo;
	}
	@Column(name = "GOODSNAME", length = 100)
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	@Column(name = "SPEC", length = 50)
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	@Column(name = "GOODSUNIT", length = 20)
	public String getGoodsUnit() {
		return goodsUnit;
	}
	public void setGoodsUnit(String goodsUnit) {
		this.goodsUnit = goodsUnit;
	}
	@Column(name = "ORIGINALPRICE", precision = 10)
	public Double getOriginalPrice() {
		return originalPrice;
	}
	public void setOriginalPrice(Double originalPrice) {
		this.originalPrice = originalPrice;
	}
	@Column(name = "CATEGORYID")
	public byte[] getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(byte[] categoryId) {
		this.categoryId = categoryId;
	}
	@Column(name = "SALEPRICE")
	public Double getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(Double salePrice) {
		this.salePrice = salePrice;
	}
	
	
}
