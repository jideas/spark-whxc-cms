package com.spark.cms.dao.po;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * CmsOrder entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "cms_order")
@SuppressWarnings("serial")
public class OrderPo implements java.io.Serializable {

	// Fields

	private byte[] recid;
	private Long recver;
	private String billsno;
	private byte[] memberid;
	private String realname;
	private String consignee;
	private String consigneetel;
	private String address;
	private Date deliveryedate;
	private String remark;
	private Double disamount;
	private Double totalamount;
	private String type;
	private byte[] stationid;
	private String stationname;
	private String status;
	private Date createdate;
	private byte[] consignorid;
	private String consignor;
	private Date consigneddate;
	private byte[] deliverpersonid;
	private String deliverperson;
	private Date deliverdate;
	private byte[] arrivedconfirmid;
	private String arrivedconfirm;
	private Date arrivedconfirmdate;
	private String verificationcode;
	private String noverificationreason;
	private boolean returnflag;
	private double deliveryCost;
//	private double bagsCost;
	private double vantages;
	private double vantagesCost;
	private boolean toDoor;
	private String payType;
	private double lockedDeliveryBalance;

	/**
	 * 锁定上门包月送货次数
	 * @return
	 */
	@Column(name = "lockedDeliveryBalance")
	public double getLockedDeliveryBalance() {
		return lockedDeliveryBalance;
	}

	public void setLockedDeliveryBalance(double lockedDeliveryBalance) {
		this.lockedDeliveryBalance = lockedDeliveryBalance;
	}
	@Column(name = "payType")
	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}
	@Column(name = "toDoor")
	public boolean isToDoor() {
		return toDoor;
	}

	public void setToDoor(boolean toDoor) {
		this.toDoor = toDoor;
	}
	@Column(name = "vantagesCost")
	public double getVantagesCost() {
		return vantagesCost;
	}

	public void setVantagesCost(double vantagesCost) {
		this.vantagesCost = vantagesCost;
	}

	@Column(name = "vantages")
	public double getVantages() {
		return vantages;
	}

	public void setVantages(double vantages) {
		this.vantages = vantages;
	}

//	@Column(name = "bagsCost")
//	public double getBagsCost() {
//		return bagsCost;
//	}
//
//	public void setBagsCost(double bagsCost) {
//		this.bagsCost = bagsCost;
//	}

	// Constructors
	@Column(name = "deliveryCost")
	public double getDeliveryCost() {
		return deliveryCost;
	}

	public void setDeliveryCost(double deliveryCost) {
		this.deliveryCost = deliveryCost;
	}

	// Constructors

	/** default constructor */
	public OrderPo() {
	}

	/** minimal constructor */
	public OrderPo(byte[] recid) {
		this.recid = recid;
	}

	/** full constructor */
	public OrderPo(byte[] recid, Long recver, String billsno, byte[] memberid, String realname,
			String consignee, String consigneetel, String address, Date deliveryedate, String remark,
			Double disamount, Double totalamount, String type, byte[] stationid, String stationname,
			String status, Date createdate, byte[] consignorid, String consignor, Date consigneddate,
			byte[] deliverpersonid, String deliverperson, Date deliverdate, byte[] arrivedconfirmid,
			String arrivedconfirm, Date arrivedconfirmdate, String verificationcode,
			String noverificationreason, boolean returnflag) {
		this.recid = recid;
		this.recver = recver;
		this.billsno = billsno;
		this.memberid = memberid;
		this.realname = realname;
		this.consignee = consignee;
		this.consigneetel = consigneetel;
		this.address = address;
		this.deliveryedate = deliveryedate;
		this.remark = remark;
		this.disamount = disamount;
		this.totalamount = totalamount;
		this.type = type;
		this.stationid = stationid;
		this.stationname = stationname;
		this.status = status;
		this.createdate = createdate;
		this.consignorid = consignorid;
		this.consignor = consignor;
		this.consigneddate = consigneddate;
		this.deliverpersonid = deliverpersonid;
		this.deliverperson = deliverperson;
		this.deliverdate = deliverdate;
		this.arrivedconfirmid = arrivedconfirmid;
		this.arrivedconfirm = arrivedconfirm;
		this.arrivedconfirmdate = arrivedconfirmdate;
		this.verificationcode = verificationcode;
		this.noverificationreason = noverificationreason;
		this.returnflag = returnflag;
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

	@Column(name = "BILLSNO", length = 20)
	public String getBillsno() {
		return this.billsno;
	}

	public void setBillsno(String billsno) {
		this.billsno = billsno;
	}

	@Column(name = "MEMBERID")
	public byte[] getMemberid() {
		return this.memberid;
	}

	public void setMemberid(byte[] memberid) {
		this.memberid = memberid;
	}

	@Column(name = "REALNAME", length = 20)
	public String getRealname() {
		return this.realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	@Column(name = "CONSIGNEE", length = 40)
	public String getConsignee() {
		return this.consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}

	@Column(name = "CONSIGNEETEL", length = 30)
	public String getConsigneetel() {
		return this.consigneetel;
	}

	public void setConsigneetel(String consigneetel) {
		this.consigneetel = consigneetel;
	}

	@Column(name = "ADDRESS", length = 200)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "DELIVERYEDATE")
	public Date getDeliveryedate() {
		return this.deliveryedate;
	}

	public void setDeliveryedate(Date deliveryedate) {
		this.deliveryedate = deliveryedate;
	}

	@Column(name = "REMARK", length = 1000)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "DISAMOUNT", precision = 17)
	public Double getDisamount() {
		return this.disamount;
	}

	public void setDisamount(Double disamount) {
		this.disamount = disamount;
	}

	@Column(name = "TOTALAMOUNT", precision = 17)
	public Double getTotalamount() {
		return this.totalamount;
	}

	public void setTotalamount(Double totalamount) {
		this.totalamount = totalamount;
	}

	@Column(name = "TYPE", length = 2)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "STATIONID")
	public byte[] getStationid() {
		return this.stationid;
	}

	public void setStationid(byte[] stationid) {
		this.stationid = stationid;
	}

	@Column(name = "STATIONNAME", length = 50)
	public String getStationname() {
		return this.stationname;
	}

	public void setStationname(String stationname) {
		this.stationname = stationname;
	}

	@Column(name = "STATUS", length = 2)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "CREATEDATE")
	public Date getCreatedate() {
		return this.createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	@Column(name = "CONSIGNORID")
	public byte[] getConsignorid() {
		return this.consignorid;
	}

	public void setConsignorid(byte[] consignorid) {
		this.consignorid = consignorid;
	}

	@Column(name = "CONSIGNOR", length = 30)
	public String getConsignor() {
		return this.consignor;
	}

	public void setConsignor(String consignor) {
		this.consignor = consignor;
	}

	@Column(name = "CONSIGNEDDATE")
	public Date getConsigneddate() {
		return this.consigneddate;
	}

	public void setConsigneddate(Date consigneddate) {
		this.consigneddate = consigneddate;
	}

	@Column(name = "DELIVERPERSONID")
	public byte[] getDeliverpersonid() {
		return this.deliverpersonid;
	}

	public void setDeliverpersonid(byte[] deliverpersonid) {
		this.deliverpersonid = deliverpersonid;
	}

	@Column(name = "DELIVERPERSON", length = 30)
	public String getDeliverperson() {
		return this.deliverperson;
	}

	public void setDeliverperson(String deliverperson) {
		this.deliverperson = deliverperson;
	}

	@Column(name = "DELIVERDATE")
	public Date getDeliverdate() {
		return this.deliverdate;
	}

	public void setDeliverdate(Date deliverdate) {
		this.deliverdate = deliverdate;
	}

	@Column(name = "ARRIVEDCONFIRMID")
	public byte[] getArrivedconfirmid() {
		return this.arrivedconfirmid;
	}

	public void setArrivedconfirmid(byte[] arrivedconfirmid) {
		this.arrivedconfirmid = arrivedconfirmid;
	}

	@Column(name = "ARRIVEDCONFIRM", length = 30)
	public String getArrivedconfirm() {
		return this.arrivedconfirm;
	}

	public void setArrivedconfirm(String arrivedconfirm) {
		this.arrivedconfirm = arrivedconfirm;
	}

	@Column(name = "ARRIVEDCONFIRMDATE")
	public Date getArrivedconfirmdate() {
		return this.arrivedconfirmdate;
	}

	public void setArrivedconfirmdate(Date arrivedconfirmdate) {
		this.arrivedconfirmdate = arrivedconfirmdate;
	}

	@Column(name = "VERIFICATIONCODE", length = 6)
	public String getVerificationcode() {
		return this.verificationcode;
	}

	public void setVerificationcode(String verificationcode) {
		this.verificationcode = verificationcode;
	}

	@Column(name = "NOVERIFICATIONREASON", length = 1000)
	public String getNoverificationreason() {
		return this.noverificationreason;
	}

	public void setNoverificationreason(String noverificationreason) {
		this.noverificationreason = noverificationreason;
	}

	@Column(name = "RETURNFLAG")
	public boolean isReturnflag() {
		return this.returnflag;
	}

	public void setReturnflag(boolean returnflag) {
		this.returnflag = returnflag;
	}

}