package com.spark.cms.services.vo;

import java.util.Date;

/**
 * CardValue entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
public class CardValueVo implements java.io.Serializable {

	// Fields

	private String recid;
	private Long recver;
	private Double cardvalue;
	private String title;
	private String creatorid;
	private String creator;
	private Date createdate;
	private String valuestatus;
	
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
	public String getCreatorid() {
		return creatorid;
	}
	public void setCreatorid(String creatorid) {
		this.creatorid = creatorid;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	
	public String getValuestatus() {
		return valuestatus;
	}
	public void setValuestatus(String valuestatus) {
		this.valuestatus = valuestatus;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Double getCardvalue() {
		return cardvalue;
	}
	public void setCardvalue(Double cardvalue) {
		this.cardvalue = cardvalue;
	}

}