package com.spark.cms.services.vo;

import java.util.Date;

@SuppressWarnings("serial")
public class MessageborderVo implements java.io.Serializable {
	private String recid;
	private String recoveryid;
	private String msgcreatorid;
	private String msgcreator;
	private String msgcontent;
	private String msgdate;
	private String isrecovery;

	public String getIsrecovery() {
		return isrecovery;
	}

	public void setIsrecovery(String isrecovery) {
		this.isrecovery = isrecovery;
	}

	public String getRecid() {
		return recid;
	}

	public void setRecid(String recid) {
		this.recid = recid;
	}

	public String getRecoveryid() {
		return recoveryid;
	}

	public void setRecoveryid(String recoveryid) {
		this.recoveryid = recoveryid;
	}

	public String getMsgcreatorid() {
		return msgcreatorid;
	}

	public void setMsgcreatorid(String msgcreatorid) {
		this.msgcreatorid = msgcreatorid;
	}

	public String getMsgcreator() {
		return msgcreator;
	}

	public void setMsgcreator(String msgcreator) {
		this.msgcreator = msgcreator;
	}

	public String getMsgcontent() {
		return msgcontent;
	}

	public void setMsgcontent(String msgcontent) {
		this.msgcontent = msgcontent;
	}

	public String getMsgdate() {
		return msgdate;
	}

	public void setMsgdate(String msgdate) {
		this.msgdate = msgdate;
	}
}