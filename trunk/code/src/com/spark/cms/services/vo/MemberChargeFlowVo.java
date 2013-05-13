package com.spark.cms.services.vo;

@SuppressWarnings("serial")
public class MemberChargeFlowVo implements java.io.Serializable {
	private String code;
	private String username;
	private String membername;
	private String mobile;
	private String chargetype;
	private String orderno;
	private String amount;
	private String occurdate;

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

	public String getChargetype() {
		return chargetype;
	}

	public void setChargetype(String chargetype) {
		this.chargetype = chargetype;
	}

	public String getOrderno() {
		return orderno;
	}

	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getOccurdate() {
		return occurdate;
	}

	public void setOccurdate(String occurdate) {
		this.occurdate = occurdate;
	}

	public MemberChargeFlowVo() {
		super();
	}

}