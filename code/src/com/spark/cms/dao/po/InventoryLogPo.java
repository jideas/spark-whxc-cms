package com.spark.cms.dao.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PSI_Inventory_Log")
@SuppressWarnings("serial")
public class InventoryLogPo implements java.io.Serializable {
	
	private String recid;
	private Long recver;
	private String storeId;
	private String stockId;
	private String name;
	private String properties;
	private String unit;
	private String categoryId;
	private String code;
	private String stockNo;
	private String orderId;
	private String orderNo;
	private String logType;
	private String instoCount;
	private String instoAmount;
	private String scale;
	private String outstoCount;
	private String outstoAmount;
	
	// Property accessors
		@Id
		@Column(name = "RECID", unique = true, nullable = false)
		public String getRecid() {
			return this.recid;
		}

		public void setRecid(String recid) {
			this.recid = recid;
		}

		@Column(name = "RECVER")
		public Long getRecver() {
			return this.recver;
		}

		public void setRecver(Long recver) {
			this.recver = recver;
		}
		
	
	@Column(name = "AMOUNT", precision = 10)
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public String getStockId() {
		return stockId;
	}
	public void setStockId(String stockId) {
		this.stockId = stockId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProperties() {
		return properties;
	}
	public void setProperties(String properties) {
		this.properties = properties;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getStockNo() {
		return stockNo;
	}
	public void setStockNo(String stockNo) {
		this.stockNo = stockNo;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getLogType() {
		return logType;
	}
	public void setLogType(String logType) {
		this.logType = logType;
	}
	public String getInstoCount() {
		return instoCount;
	}
	public void setInstoCount(String instoCount) {
		this.instoCount = instoCount;
	}
	public String getInstoAmount() {
		return instoAmount;
	}
	public void setInstoAmount(String instoAmount) {
		this.instoAmount = instoAmount;
	}
	public String getScale() {
		return scale;
	}
	public void setScale(String scale) {
		this.scale = scale;
	}
	public String getOutstoCount() {
		return outstoCount;
	}
	public void setOutstoCount(String outstoCount) {
		this.outstoCount = outstoCount;
	}
	public String getOutstoAmount() {
		return outstoAmount;
	}
	public void setOutstoAmount(String outstoAmount) {
		this.outstoAmount = outstoAmount;
	}
	public String getCreatePerson() {
		return createPerson;
	}
	public void setCreatePerson(String createPerson) {
		this.createPerson = createPerson;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getInventoryType() {
		return inventoryType;
	}
	public void setInventoryType(String inventoryType) {
		this.inventoryType = inventoryType;
	}
	private String createPerson;
	private String createdDate;
	private String inventoryType;

}
