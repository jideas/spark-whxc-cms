package com.spark.cms.dao.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CMS_PAYING_LOG")
@SuppressWarnings("serial")
public class PayingLogPo implements java.io.Serializable {
	private byte[] recid;
	private Long recver;
	private String ptype;
	private String amount;
	private String createDate;
	private String orderId;
	private String transDate;
	private String relaBillsId;
	private String logDate;
	private String resultXml;
	

	@Id
	@Column(name = "RECID", unique = true, nullable = false)
	public byte[] getRecid() {
		return recid;
	}
	@Column(name = "RECVER")
	public Long getRecver() {
		return recver;
	}
	@Column(name = "ptype")
	public String getPtype() {
		return ptype;
	}
	@Column(name = "amount")
	public String getAmount() {
		return amount;
	}
	@Column(name = "createDate")
	public String getCreateDate() {
		return createDate;
	}
	@Column(name = "orderId")
	public String getOrderId() {
		return orderId;
	}
	@Column(name = "transDate")
	public String getTransDate() {
		return transDate;
	}
	@Column(name = "relaBillsId")
	public String getRelaBillsId() {
		return relaBillsId;
	}
	@Column(name = "logDate")
	public String getLogDate() {
		return logDate;
	}
	@Column(name = "resultXml")
	public String getResultXml() {
		return resultXml;
	}
	public void setRecid(byte[] recid) {
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
