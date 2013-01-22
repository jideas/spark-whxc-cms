package com.spark.front.form.order;

import java.util.Date;
import java.util.List;

import com.spark.cms.services.vo.OrderDetVo;
import com.spark.cms.services.vo.OrderLogVo;

@SuppressWarnings("serial")
public class OrderInfo implements java.io.Serializable {

	private String recid;
	private Long recver;
	private String billsno;
	private String memberid;
	private String realname;
	private String consignee;
	private String consigneetel;
	private String address;
	private Date deliveryedate;
	private String remark;
	private Double disamount;
	private Double totalamount;
	private String type;
	private String stationid;
	private String stationname;
	private String status;
	private String statusStr;
	private Date createdate;
	private String createDateStr;
	private String consignorid;
	private String consignor;
	private Date consigneddate;
	private String deliverpersonid;
	private String deliverperson;
	private Date deliverdate;
	private String arrivedconfirmid;
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
	private String payTypeStr;
	private List<OrderDetVo> dets;
	private List<OrderLogVo> logs;
	
	/**
	 * 是否积分商城订单
	 */
	private boolean vantagesCostOrder;
	
	private double lockedDeliveryBalance;

	/**
	 * 锁定上门包月送货次数
	 * @return
	 */
	public double getLockedDeliveryBalance() {
		return lockedDeliveryBalance;
	}

	public void setLockedDeliveryBalance(double lockedDeliveryBalance) {
		this.lockedDeliveryBalance = lockedDeliveryBalance;
	}

	public boolean isVantagesCostOrder() {
		return vantagesCostOrder;
	}

	public void setVantagesCostOrder(boolean vantagesCostOrder) {
		this.vantagesCostOrder = vantagesCostOrder;
	}

	public List<OrderLogVo> getLogs() {
		return logs;
	}

	public void setLogs(List<OrderLogVo> logs) {
		this.logs = logs;
	}

	public List<OrderDetVo> getDets() {
		return dets;
	}

	public void setDets(List<OrderDetVo> dets) {
		this.dets = dets;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public boolean isToDoor() {
		return toDoor;
	}

	public void setToDoor(boolean toDoor) {
		this.toDoor = toDoor;
	}

	public double getVantagesCost() {
		return vantagesCost;
	}

	public void setVantagesCost(double vantagesCost) {
		this.vantagesCost = vantagesCost;
	}

	public double getVantages() {
		return vantages;
	}

	public void setVantages(double vantages) {
		this.vantages = vantages;
	}

//	public double getBagsCost() {
//		return bagsCost;
//	}
//
//	public void setBagsCost(double bagsCost) {
//		this.bagsCost = bagsCost;
//	}

	// Constructors

	public double getDeliveryCost() {
		return deliveryCost;
	}

	public void setDeliveryCost(double deliveryCost) {
		this.deliveryCost = deliveryCost;
	}

	public String getRecid() {
		return this.recid;
	}

	public void setRecid(String recid) {
		this.recid = recid;
	}

	public Long getRecver() {
		return this.recver;
	}

	public void setRecver(Long recver) {
		this.recver = recver;
	}

	public String getBillsno() {
		return this.billsno;
	}

	public void setBillsno(String billsno) {
		this.billsno = billsno;
	}

	public String getMemberid() {
		return this.memberid;
	}

	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}

	public String getRealname() {
		return this.realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getConsignee() {
		return this.consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}

	public String getConsigneetel() {
		return this.consigneetel;
	}

	public void setConsigneetel(String consigneetel) {
		this.consigneetel = consigneetel;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getDeliveryedate() {
		return this.deliveryedate;
	}

	public void setDeliveryedate(Date deliveryedate) {
		this.deliveryedate = deliveryedate;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Double getDisamount() {
		return this.disamount;
	}

	public void setDisamount(Double disamount) {
		this.disamount = disamount;
	}

	public Double getTotalamount() {
		return this.totalamount;
	}

	public void setTotalamount(Double totalamount) {
		this.totalamount = totalamount;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStationid() {
		return this.stationid;
	}

	public void setStationid(String stationid) {
		this.stationid = stationid;
	}

	public String getStationname() {
		return this.stationname;
	}

	public void setStationname(String stationname) {
		this.stationname = stationname;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreatedate() {
		return this.createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public String getConsignorid() {
		return this.consignorid;
	}

	public void setConsignorid(String consignorid) {
		this.consignorid = consignorid;
	}

	public String getConsignor() {
		return this.consignor;
	}

	public void setConsignor(String consignor) {
		this.consignor = consignor;
	}

	public Date getConsigneddate() {
		return this.consigneddate;
	}

	public void setConsigneddate(Date consigneddate) {
		this.consigneddate = consigneddate;
	}

	public String getDeliverpersonid() {
		return this.deliverpersonid;
	}

	public void setDeliverpersonid(String deliverpersonid) {
		this.deliverpersonid = deliverpersonid;
	}

	public String getDeliverperson() {
		return this.deliverperson;
	}

	public void setDeliverperson(String deliverperson) {
		this.deliverperson = deliverperson;
	}

	public Date getDeliverdate() {
		return this.deliverdate;
	}

	public void setDeliverdate(Date deliverdate) {
		this.deliverdate = deliverdate;
	}

	public String getArrivedconfirmid() {
		return this.arrivedconfirmid;
	}

	public void setArrivedconfirmid(String arrivedconfirmid) {
		this.arrivedconfirmid = arrivedconfirmid;
	}

	public String getArrivedconfirm() {
		return this.arrivedconfirm;
	}

	public void setArrivedconfirm(String arrivedconfirm) {
		this.arrivedconfirm = arrivedconfirm;
	}

	public Date getArrivedconfirmdate() {
		return this.arrivedconfirmdate;
	}

	public void setArrivedconfirmdate(Date arrivedconfirmdate) {
		this.arrivedconfirmdate = arrivedconfirmdate;
	}

	public String getVerificationcode() {
		return this.verificationcode;
	}

	public void setVerificationcode(String verificationcode) {
		this.verificationcode = verificationcode;
	}

	public String getNoverificationreason() {
		return this.noverificationreason;
	}

	public void setNoverificationreason(String noverificationreason) {
		this.noverificationreason = noverificationreason;
	}

	public boolean isReturnflag() {
		return this.returnflag;
	}

	public void setReturnflag(boolean returnflag) {
		this.returnflag = returnflag;
	}

	public String getCreateDateStr() {
		return createDateStr;
	}

	public void setCreateDateStr(String createDateStr) {
		this.createDateStr = createDateStr;
	}

	public String getStatusStr() {
		return statusStr;
	}

	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}

	public String getPayTypeStr() {
		return payTypeStr;
	}

	public void setPayTypeStr(String payTypeStr) {
		this.payTypeStr = payTypeStr;
	}

}
