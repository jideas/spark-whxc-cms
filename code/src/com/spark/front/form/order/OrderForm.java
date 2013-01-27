package com.spark.front.form.order;

import java.util.Date;
import java.util.List;

import com.spark.base.common.system.dic.DicItem;
import com.spark.cms.services.vo.MemberAddressVo;
import com.spark.cms.services.vo.OrderDetVo;

public class OrderForm {

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
	private Date createdate;
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
	private Byte returnflag;
	private double deliveryCost;
	private double bagsCost;
	private double vantages;
	private List<MemberAddressVo> addressList;
	private List<OrderDetVo> dets;
	private List<ShopingCarGoods> goodsList;
	private List<ShopingCarGoods> vgoodsList;
	private Long p_vantages;
	private boolean p_freedelivery;
	private double beginAmount;
	private double bagPrice;
	private double deliverPrice;
	private List<DicItem> deliverTimeItems;
	private double vantagesCost;
	private boolean hasBookingGoods = false;
	private boolean onlyBookingGoods = false;
	
	public boolean isOnlyBookingGoods() {
		return onlyBookingGoods;
	}

	public void setOnlyBookingGoods(boolean onlyBookingGoods) {
		this.onlyBookingGoods = onlyBookingGoods;
	}

	public boolean isHasBookingGoods() {
		return hasBookingGoods;
	}

	public void setHasBookingGoods(boolean hasBookingGoods) {
		this.hasBookingGoods = hasBookingGoods;
	}

	public double getBeginAmount() {
		return beginAmount;
	}

	public void setBeginAmount(double beginAmount) {
		this.beginAmount = beginAmount;
	}

	public double getVantagesCost() {
		return vantagesCost;
	}

	public void setVantagesCost(double vantagesCost) {
		this.vantagesCost = vantagesCost;
	}

	public List<ShopingCarGoods> getVgoodsList() {
		return vgoodsList;
	}

	public void setVgoodsList(List<ShopingCarGoods> vgoodsList) {
		this.vgoodsList = vgoodsList;
	}

	public List<DicItem> getDeliverTimeItems() {
		return deliverTimeItems;
	}

	public void setDeliverTimeItems(List<DicItem> deliverTimeItems) {
		this.deliverTimeItems = deliverTimeItems;
	}

	public double getBagPrice() {
		return bagPrice;
	}

	public void setBagPrice(double bagPrice) {
		this.bagPrice = bagPrice;
	}

	public double getDeliverPrice() {
		return deliverPrice;
	}

	public void setDeliverPrice(double deliverPrice) {
		this.deliverPrice = deliverPrice;
	}

	public Long getP_vantages() {
		return p_vantages;
	}

	public void setP_vantages(Long p_vantages) {
		this.p_vantages = p_vantages;
	}

	public boolean isP_freedelivery() {
		return p_freedelivery;
	}

	public void setP_freedelivery(boolean p_freedelivery) {
		this.p_freedelivery = p_freedelivery;
	}

	public List<ShopingCarGoods> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(List<ShopingCarGoods> goodsList) {
		this.goodsList = goodsList;
	}

	public String getRecid() {
		return recid;
	}

	public void setRecid(String recid) {
		this.recid = recid;
	}

	public Long getRecver() {
		return recver;
	}

	public void setRecver(Long recver) {
		this.recver = recver;
	}

	public String getBillsno() {
		return billsno;
	}

	public void setBillsno(String billsno) {
		this.billsno = billsno;
	}

	public String getMemberid() {
		return memberid;
	}

	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getConsignee() {
		return consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}

	public String getConsigneetel() {
		return consigneetel;
	}

	public void setConsigneetel(String consigneetel) {
		this.consigneetel = consigneetel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getDeliveryedate() {
		return deliveryedate;
	}

	public void setDeliveryedate(Date deliveryedate) {
		this.deliveryedate = deliveryedate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Double getDisamount() {
		return disamount;
	}

	public void setDisamount(Double disamount) {
		this.disamount = disamount;
	}

	public Double getTotalamount() {
		return totalamount;
	}

	public void setTotalamount(Double totalamount) {
		this.totalamount = totalamount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStationid() {
		return stationid;
	}

	public void setStationid(String stationid) {
		this.stationid = stationid;
	}

	public String getStationname() {
		return stationname;
	}

	public void setStationname(String stationname) {
		this.stationname = stationname;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public String getConsignorid() {
		return consignorid;
	}

	public void setConsignorid(String consignorid) {
		this.consignorid = consignorid;
	}

	public String getConsignor() {
		return consignor;
	}

	public void setConsignor(String consignor) {
		this.consignor = consignor;
	}

	public Date getConsigneddate() {
		return consigneddate;
	}

	public void setConsigneddate(Date consigneddate) {
		this.consigneddate = consigneddate;
	}

	public String getDeliverpersonid() {
		return deliverpersonid;
	}

	public void setDeliverpersonid(String deliverpersonid) {
		this.deliverpersonid = deliverpersonid;
	}

	public String getDeliverperson() {
		return deliverperson;
	}

	public void setDeliverperson(String deliverperson) {
		this.deliverperson = deliverperson;
	}

	public Date getDeliverdate() {
		return deliverdate;
	}

	public void setDeliverdate(Date deliverdate) {
		this.deliverdate = deliverdate;
	}

	public String getArrivedconfirmid() {
		return arrivedconfirmid;
	}

	public void setArrivedconfirmid(String arrivedconfirmid) {
		this.arrivedconfirmid = arrivedconfirmid;
	}

	public String getArrivedconfirm() {
		return arrivedconfirm;
	}

	public void setArrivedconfirm(String arrivedconfirm) {
		this.arrivedconfirm = arrivedconfirm;
	}

	public Date getArrivedconfirmdate() {
		return arrivedconfirmdate;
	}

	public void setArrivedconfirmdate(Date arrivedconfirmdate) {
		this.arrivedconfirmdate = arrivedconfirmdate;
	}

	public String getVerificationcode() {
		return verificationcode;
	}

	public void setVerificationcode(String verificationcode) {
		this.verificationcode = verificationcode;
	}

	public String getNoverificationreason() {
		return noverificationreason;
	}

	public void setNoverificationreason(String noverificationreason) {
		this.noverificationreason = noverificationreason;
	}

	public Byte getReturnflag() {
		return returnflag;
	}

	public void setReturnflag(Byte returnflag) {
		this.returnflag = returnflag;
	}

	public double getDeliveryCost() {
		return deliveryCost;
	}

	public void setDeliveryCost(double deliveryCost) {
		this.deliveryCost = deliveryCost;
	}

	public double getBagsCost() {
		return bagsCost;
	}

	public void setBagsCost(double bagsCost) {
		this.bagsCost = bagsCost;
	}

	public List<OrderDetVo> getDets() {
		return dets;
	}

	public void setDets(List<OrderDetVo> dets) {
		this.dets = dets;
	}

	public double getVantages() {
		return vantages;
	}

	public void setVantages(double vantages) {
		this.vantages = vantages;
	}

	public List<MemberAddressVo> getAddressList() {
		return addressList;
	}

	public void setAddressList(List<MemberAddressVo> addressList) {
		this.addressList = addressList;
	}
	
}
