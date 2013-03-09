package com.spark.cms.services.vo;


@SuppressWarnings("serial")
public class PayingLogVo implements java.io.Serializable {
	private String recid;
	private Long recver;
	private String ptype;
	private String amount;
	private String createDate;
	private String orderId;
	private String transDate;
	private String relaBillsId;
	private String logDate;
	private String resultXml;

	public String getRecid() {
		return recid;
	}

	public Long getRecver() {
		return recver;
	}

	public String getPtype() {
		return ptype;
	}

	public String getAmount() {
		return amount;
	}

	public String getCreateDate() {
		return createDate;
	}

	public String getOrderId() {
		return orderId;
	}

	public String getTransDate() {
		return transDate;
	}

	public String getRelaBillsId() {
		return relaBillsId;
	}

	public String getLogDate() {
		return logDate;
	}

	public String getResultXml() {
		return resultXml;
	}

	public void setRecid(String recid) {
		this.recid = recid;
	}

	public void setRecver(Long recver) {
		this.recver = recver;
	}

	public void setPtype(String ptype) {
		this.ptype = ptype;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public void setTransDate(String transDate) {
		this.transDate = transDate;
	}

	public void setRelaBillsId(String relaBillsId) {
		this.relaBillsId = relaBillsId;
	}

	public void setLogDate(String logDate) {
		this.logDate = logDate;
	}

	public void setResultXml(String resultXml) {
		this.resultXml = resultXml;
	}

}
