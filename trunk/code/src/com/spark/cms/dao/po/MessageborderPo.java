package com.spark.cms.dao.po;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CmsGoods entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "cms_messageborder")
@SuppressWarnings("serial")
public class MessageborderPo implements java.io.Serializable {

	// Fields
	private byte[] recid;
	private byte[] recoveryid;
	private byte[] msgcreatorid;
	private String msgcreator;
	private String msgcontent;
	private Date msgdate;
	private String isrecovery;

	@Id
	@Column(name = "RECID", unique = true, nullable = false)
	public byte[] getRecid() {
		return recid;
	}

	public void setRecid(byte[] recid) {
		this.recid = recid;
	}

	@Column(name = "RECOVERYID")
	public byte[] getRecoveryid() {
		return recoveryid;
	}

	public void setRecoveryid(byte[] recoveryid) {
		this.recoveryid = recoveryid;
	}

	@Column(name = "MSGCREATORID")
	public byte[] getMsgcreatorid() {
		return msgcreatorid;
	}

	public void setMsgcreatorid(byte[] msgcreatorid) {
		this.msgcreatorid = msgcreatorid;
	}

	@Column(name = "MSGCREATOR")
	public String getMsgcreator() {
		return msgcreator;
	}

	public void setMsgcreator(String msgcreator) {
		this.msgcreator = msgcreator;
	}

	@Column(name = "MSGCONTENT")
	public String getMsgcontent() {
		return msgcontent;
	}

	public void setMsgcontent(String msgcontent) {
		this.msgcontent = msgcontent;
	}

	@Column(name = "MSGDATE")
	public Date getMsgdate() {
		return msgdate;
	}

	public void setMsgdate(Date msgdate) {
		this.msgdate = msgdate;
	}
	
	@Column(name = "ISRECOVERY")
	public String getIsrecovery() {
		return isrecovery;
	}

	public void setIsrecovery(String isrecovery) {
		this.isrecovery = isrecovery;
	}

}