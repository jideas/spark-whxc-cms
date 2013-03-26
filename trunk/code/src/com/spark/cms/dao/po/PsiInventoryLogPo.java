package com.spark.cms.dao.po;
// default package

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * PsiInventoryLog entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "psi_inventory_log")
public class PsiInventoryLogPo implements java.io.Serializable {

	// Fields

	private byte[] recid;
	private Long recver;
	private byte[] storeid;
	private byte[] stockid;
	private String name;
	private String properties;
	private String unit;
	private byte[] categoryid;
	private String code;
	private String stockno;
	private String orderid;
	private String orderno;
	private String logtype;
	private Double instocount;
	private Double instoamount;
	private Integer scale;
	private Double outstocount;
	private Double outstoamount;
	private String createperson;
//	private Date createddate;
	private String inventorytype;

	// Constructors

	/** default constructor */
	public PsiInventoryLogPo() {
	}

	/** minimal constructor */
	public PsiInventoryLogPo(byte[] recid) {
		this.recid = recid;
	}

	/** full constructor */
//	public PsiInventoryLogPo(byte[] recid, Long recver, byte[] storeid,
//			byte[] stockid, String name, String properties, String unit,
//			byte[] categoryid, String code, String stockno, String orderid,
//			String orderno, String logtype, Double instocount,
//			Double instoamount, Integer scale, Double outstocount,
//			Double outstoamount, String createperson, Date createddate,
//			String inventorytype) {
//		this.recid = recid;
//		this.recver = recver;
//		this.storeid = storeid;
//		this.stockid = stockid;
//		this.name = name;
//		this.properties = properties;
//		this.unit = unit;
//		this.categoryid = categoryid;
//		this.code = code;
//		this.stockno = stockno;
//		this.orderid = orderid;
//		this.orderno = orderno;
//		this.logtype = logtype;
//		this.instocount = instocount;
//		this.instoamount = instoamount;
//		this.scale = scale;
//		this.outstocount = outstocount;
//		this.outstoamount = outstoamount;
//		this.createperson = createperson;
//		this.createddate = createddate;
//		this.inventorytype = inventorytype;
//	}
	
	public PsiInventoryLogPo(byte[] recid, Long recver, byte[] storeid,
			byte[] stockid, String name, String properties, String unit,
			byte[] categoryid, String code, String stockno, String orderid,
			String orderno, String logtype, Double instocount,
			Double instoamount, Integer scale, Double outstocount,
			Double outstoamount, String createperson,
			String inventorytype) {
		this.recid = recid;
		this.recver = recver;
		this.storeid = storeid;
		this.stockid = stockid;
		this.name = name;
		this.properties = properties;
		this.unit = unit;
		this.categoryid = categoryid;
		this.code = code;
		this.stockno = stockno;
		this.orderid = orderid;
		this.orderno = orderno;
		this.logtype = logtype;
		this.instocount = instocount;
		this.instoamount = instoamount;
		this.scale = scale;
		this.outstocount = outstocount;
		this.outstoamount = outstoamount;
		this.createperson = createperson;
		this.inventorytype = inventorytype;
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

	@Column(name = "STOREID")
	public byte[] getStoreid() {
		return this.storeid;
	}

	public void setStoreid(byte[] storeid) {
		this.storeid = storeid;
	}

	@Column(name = "STOCKID")
	public byte[] getStockid() {
		return this.stockid;
	}

	public void setStockid(byte[] stockid) {
		this.stockid = stockid;
	}

	@Column(name = "NAME", length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "PROPERTIES", length = 100)
	public String getProperties() {
		return this.properties;
	}

	public void setProperties(String properties) {
		this.properties = properties;
	}

	@Column(name = "UNIT", length = 20)
	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Column(name = "CATEGORYID")
	public byte[]  getCategoryid() {
		return this.categoryid;
	}

	public void setCategoryid(byte[]  categoryid) {
		this.categoryid = categoryid;
	}

	@Column(name = "CODE", length = 30)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "STOCKNO", length = 30)
	public String getStockno() {
		return this.stockno;
	}

	public void setStockno(String stockno) {
		this.stockno = stockno;
	}

	@Column(name = "ORDERID")
	public String getOrderid() {
		return this.orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	@Column(name = "ORDERNO", length = 30)
	public String getOrderno() {
		return this.orderno;
	}

	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}

	@Column(name = "LOGTYPE", length = 2)
	public String getLogtype() {
		return this.logtype;
	}

	public void setLogtype(String logtype) {
		this.logtype = logtype;
	}

	@Column(name = "INSTOCOUNT", precision = 17, scale = 5)
	public Double getInstocount() {
		return this.instocount;
	}

	public void setInstocount(Double instocount) {
		this.instocount = instocount;
	}

	@Column(name = "INSTOAMOUNT", precision = 17, scale = 5)
	public Double getInstoamount() {
		return this.instoamount;
	}

	public void setInstoamount(Double instoamount) {
		this.instoamount = instoamount;
	}

	@Column(name = "SCALE")
	public Integer getScale() {
		return this.scale;
	}

	public void setScale(Integer scale) {
		this.scale = scale;
	}

	@Column(name = "OUTSTOCOUNT", precision = 17, scale = 5)
	public Double getOutstocount() {
		return this.outstocount;
	}

	public void setOutstocount(Double outstocount) {
		this.outstocount = outstocount;
	}

	@Column(name = "OUTSTOAMOUNT", precision = 17, scale = 5)
	public Double getOutstoamount() {
		return this.outstoamount;
	}

	public void setOutstoamount(Double outstoamount) {
		this.outstoamount = outstoamount;
	}

	@Column(name = "CREATEPERSON", length = 20)
	public String getCreateperson() {
		return this.createperson;
	}

	public void setCreateperson(String createperson) {
		this.createperson = createperson;
	}

//	@Temporal(TemporalType.DATE)
//	@Column(name = "CREATEDDATE", length = 19)
//	public Date getCreateddate() {
//		return this.createddate;
//	}
//
//	public void setCreateddate(Date createddate) {
//		this.createddate = createddate;
//	}

	@Column(name = "INVENTORYTYPE", length = 2)
	public String getInventorytype() {
		return this.inventorytype;
	}

	public void setInventorytype(String inventorytype) {
		this.inventorytype = inventorytype;
	}

}