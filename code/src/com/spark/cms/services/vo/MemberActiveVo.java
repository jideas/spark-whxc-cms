package com.spark.cms.services.vo;

@SuppressWarnings("serial")
public class MemberActiveVo implements java.io.Serializable {
	private String code;
	private String username;
	private String membername;
	private String mobile;
	private String stationname;
	private Integer ordercount;
	private Double ordermoney;
	private Integer returncount;
	private Double returnmoney;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMembername() {
		return membername;
	}

	public void setMembername(String membername) {
		this.membername = membername;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getOrdercount() {
		return ordercount;
	}

	public void setOrdercount(Integer ordercount) {
		this.ordercount = ordercount;
	}

	public Double getOrdermoney() {
		return ordermoney;
	}

	public void setOrdermoney(Double ordermoney) {
		this.ordermoney = ordermoney;
	}

	public Integer getReturncount() {
		return returncount;
	}

	public void setReturncount(Integer returncount) {
		this.returncount = returncount;
	}

	public Double getReturnmoney() {
		return returnmoney;
	}

	public void setReturnmoney(Double returnmoney) {
		this.returnmoney = returnmoney;
	}

	public MemberActiveVo(String code, String username, String membername,
			String mobile, Integer ordercount, Double ordermoney,
			Integer returncount, Double returnmoney) {
		super();
		this.code = code;
		this.username = username;
		this.membername = membername;
		this.mobile = mobile;
		this.ordercount = ordercount;
		this.ordermoney = ordermoney;
		this.returncount = returncount;
		this.returnmoney = returnmoney;
	}

	public MemberActiveVo() {
		super();
	}

	public String getStationname() {
		return stationname;
	}

	public void setStationname(String stationname) {
		this.stationname = stationname;
	}

}