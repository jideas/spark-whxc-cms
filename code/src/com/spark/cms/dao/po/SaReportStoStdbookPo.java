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
 * SaReportStoStdbook entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sa_report_sto_stdbook")
public class SaReportStoStdbookPo implements java.io.Serializable {

	// Fields

	private byte[] recid;
	private Long recver;
	private byte[] tenantid;
	private byte[] storeguid;
	private byte[] goodsguid;
	private String inventorytype;
	private Double beginstorecount;
	private Double beginstoremoney;
	private Double instocount;
	private Double instoamount;
	private Double outstocount;
	private Double outstoamount;
	private Double endstorecount;
	private Double endstoremoney;
	private Date createddate;
	private String goodsname;
	private String goodsattr;
	private String goodsunit;
	private byte[] goodstypeguid;
	private String goodsno;
	private Integer goodsscale;
	private Integer dateno;
	private Integer monthno;
	private Integer quarter;
	private Integer yearno;

	// Constructors

	/** default constructor */
	public SaReportStoStdbookPo() {
	}

	/** minimal constructor */
	public SaReportStoStdbookPo(byte[] recid) {
		this.recid = recid;
	}

	/** full constructor */
	public SaReportStoStdbookPo(byte[] recid, Long recver, byte[] tenantid,
			byte[] storeguid, byte[] goodsguid, String inventorytype,
			Double beginstorecount, Double beginstoremoney, Double instocount,
			Double instoamount, Double outstocount, Double outstoamount,
			Double endstorecount, Double endstoremoney, Date createddate,
			String goodsname, String goodsattr, String goodsunit,
			byte[] goodstypeguid, String goodsno, Integer goodsscale,
			Integer dateno, Integer monthno, Integer quarter, Integer yearno) {
		this.recid = recid;
		this.recver = recver;
		this.tenantid = tenantid;
		this.storeguid = storeguid;
		this.goodsguid = goodsguid;
		this.inventorytype = inventorytype;
		this.beginstorecount = beginstorecount;
		this.beginstoremoney = beginstoremoney;
		this.instocount = instocount;
		this.instoamount = instoamount;
		this.outstocount = outstocount;
		this.outstoamount = outstoamount;
		this.endstorecount = endstorecount;
		this.endstoremoney = endstoremoney;
		this.createddate = createddate;
		this.goodsname = goodsname;
		this.goodsattr = goodsattr;
		this.goodsunit = goodsunit;
		this.goodstypeguid = goodstypeguid;
		this.goodsno = goodsno;
		this.goodsscale = goodsscale;
		this.dateno = dateno;
		this.monthno = monthno;
		this.quarter = quarter;
		this.yearno = yearno;
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

	@Column(name = "TENANTID")
	public byte[] getTenantid() {
		return this.tenantid;
	}

	public void setTenantid(byte[] tenantid) {
		this.tenantid = tenantid;
	}

	@Column(name = "STOREGUID")
	public byte[] getStoreguid() {
		return this.storeguid;
	}

	public void setStoreguid(byte[] storeguid) {
		this.storeguid = storeguid;
	}

	@Column(name = "GOODSGUID")
	public byte[] getGoodsguid() {
		return this.goodsguid;
	}

	public void setGoodsguid(byte[] goodsguid) {
		this.goodsguid = goodsguid;
	}

	@Column(name = "INVENTORYTYPE", length = 2)
	public String getInventorytype() {
		return this.inventorytype;
	}

	public void setInventorytype(String inventorytype) {
		this.inventorytype = inventorytype;
	}

	@Column(name = "BEGINSTORECOUNT", precision = 17, scale = 4)
	public Double getBeginstorecount() {
		return this.beginstorecount;
	}

	public void setBeginstorecount(Double beginstorecount) {
		this.beginstorecount = beginstorecount;
	}

	@Column(name = "BEGINSTOREMONEY", precision = 17, scale = 4)
	public Double getBeginstoremoney() {
		return this.beginstoremoney;
	}

	public void setBeginstoremoney(Double beginstoremoney) {
		this.beginstoremoney = beginstoremoney;
	}

	@Column(name = "INSTOCOUNT", precision = 17, scale = 4)
	public Double getInstocount() {
		return this.instocount;
	}

	public void setInstocount(Double instocount) {
		this.instocount = instocount;
	}

	@Column(name = "INSTOAMOUNT", precision = 17, scale = 4)
	public Double getInstoamount() {
		return this.instoamount;
	}

	public void setInstoamount(Double instoamount) {
		this.instoamount = instoamount;
	}

	@Column(name = "OUTSTOCOUNT", precision = 17, scale = 4)
	public Double getOutstocount() {
		return this.outstocount;
	}

	public void setOutstocount(Double outstocount) {
		this.outstocount = outstocount;
	}

	@Column(name = "OUTSTOAMOUNT", precision = 17, scale = 4)
	public Double getOutstoamount() {
		return this.outstoamount;
	}

	public void setOutstoamount(Double outstoamount) {
		this.outstoamount = outstoamount;
	}

	@Column(name = "ENDSTORECOUNT", precision = 17, scale = 4)
	public Double getEndstorecount() {
		return this.endstorecount;
	}

	public void setEndstorecount(Double endstorecount) {
		this.endstorecount = endstorecount;
	}

	@Column(name = "ENDSTOREMONEY", precision = 17, scale = 4)
	public Double getEndstoremoney() {
		return this.endstoremoney;
	}

	public void setEndstoremoney(Double endstoremoney) {
		this.endstoremoney = endstoremoney;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CREATEDDATE", length = 19)
	public Date getCreateddate() {
		return this.createddate;
	}

	public void setCreateddate(Date createddate) {
		this.createddate = createddate;
	}

	@Column(name = "GOODSNAME", length = 50)
	public String getGoodsname() {
		return this.goodsname;
	}

	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}

	@Column(name = "GOODSATTR", length = 100)
	public String getGoodsattr() {
		return this.goodsattr;
	}

	public void setGoodsattr(String goodsattr) {
		this.goodsattr = goodsattr;
	}

	@Column(name = "GOODSUNIT", length = 20)
	public String getGoodsunit() {
		return this.goodsunit;
	}

	public void setGoodsunit(String goodsunit) {
		this.goodsunit = goodsunit;
	}

	@Column(name = "GOODSTYPEGUID")
	public byte[] getGoodstypeguid() {
		return this.goodstypeguid;
	}

	public void setGoodstypeguid(byte[] goodstypeguid) {
		this.goodstypeguid = goodstypeguid;
	}

	@Column(name = "GOODSNO", length = 20)
	public String getGoodsno() {
		return this.goodsno;
	}

	public void setGoodsno(String goodsno) {
		this.goodsno = goodsno;
	}

	@Column(name = "GOODSSCALE")
	public Integer getGoodsscale() {
		return this.goodsscale;
	}

	public void setGoodsscale(Integer goodsscale) {
		this.goodsscale = goodsscale;
	}

	@Column(name = "DATENO")
	public Integer getDateno() {
		return this.dateno;
	}

	public void setDateno(Integer dateno) {
		this.dateno = dateno;
	}

	@Column(name = "MONTHNO")
	public Integer getMonthno() {
		return this.monthno;
	}

	public void setMonthno(Integer monthno) {
		this.monthno = monthno;
	}

	@Column(name = "QUARTER")
	public Integer getQuarter() {
		return this.quarter;
	}

	public void setQuarter(Integer quarter) {
		this.quarter = quarter;
	}

	@Column(name = "YEARNO")
	public Integer getYearno() {
		return this.yearno;
	}

	public void setYearno(Integer yearno) {
		this.yearno = yearno;
	}

}