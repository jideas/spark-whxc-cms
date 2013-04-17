package com.spark.cms.dao.po;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * CardValue entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "cms_cardValue")
@SuppressWarnings("serial")
public class CardValuePo implements java.io.Serializable {

	// Fields

	private byte[] recid;
	private Long recver;
	private Double cardvalue; //ÃæÖµ
	private byte[] creatorid;
	private String creator;
	private Date createdate;
	private String valuestatus;

	// Constructors

	/** default constructor */
	public CardValuePo() {
	}

	/** minimal constructor */
	public CardValuePo(byte[] recid) {
		this.recid = recid;
	}

	/** full constructor */
	public CardValuePo(byte[] recid, Long recver, byte[] creatorid,
			String creator, Date createdate, String status,Double cardValue) {
		super();
		this.recid = recid;
		this.recver = recver;
		this.creatorid = creatorid;
		this.creator = creator;
		this.createdate = createdate;
		this.valuestatus = status;
		this.cardvalue = cardValue;
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

	@Column(name = "CREATORID")
	public byte[] getCreatorid() {
		return this.creatorid;
	}

	public void setCreatorid(byte[] creatorid) {
		this.creatorid = creatorid;
	}

	@Column(name = "CREATOR", length = 100)
	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CREATEDATE", length = 19)
	public Date getCreatedate() {
		return this.createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	@Column(name = "VALUESTATUS", length = 2)
	public String getValuestatus() {
		return this.valuestatus;
	}

	public void setValuestatus(String status) {
		this.valuestatus = status;
	}

	@Column(name = "CARDVALUE")
	public Double getCardvalue() {
		return cardvalue;
	}

	public void setCardvalue(Double cardvalue) {
		this.cardvalue = cardvalue;
	}
	
}