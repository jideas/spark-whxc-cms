package com.spark.cms.services.vo;

import java.util.Date;

/**
 * CmsCardDistributeLog entity.
 * 
 * @author MyEclipse Persistence Tools
 */ 
@SuppressWarnings("serial")
public class CardDistributeLogVo implements java.io.Serializable {

	// Fields

	private String recid;
	private Long recver;
	private String cardid;
	private String distributepersonid;
	private String distributeperson;
	private String responsiblepersonid;
	private String responsibleperson;
	private Date distributedate;
	private String lastpersonid;
	private String lastperson;

	// Constructors

	/** default constructor */
	public CardDistributeLogVo() {
	}

	/** minimal constructor */
	public CardDistributeLogVo(String recid) {
		this.recid = recid;
	}

	/** full constructor */
	public CardDistributeLogVo(String recid, Long recver, String cardid, String distributepersonid,
			String distributeperson, String responsiblepersonid, String responsibleperson,
			Date distributedate, String lastpersonid, String lastperson) {
		this.recid = recid;
		this.recver = recver;
		this.cardid = cardid;
		this.distributepersonid = distributepersonid;
		this.distributeperson = distributeperson;
		this.responsiblepersonid = responsiblepersonid;
		this.responsibleperson = responsibleperson;
		this.distributedate = distributedate;
		this.lastpersonid = lastpersonid;
		this.lastperson = lastperson;
	}

	// Property accessors

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

	public String getCardid() {
		return this.cardid;
	}

	public void setCardid(String cardid) {
		this.cardid = cardid;
	}

	public String getDistributepersonid() {
		return this.distributepersonid;
	}

	public void setDistributepersonid(String distributepersonid) {
		this.distributepersonid = distributepersonid;
	}

	public String getDistributeperson() {
		return this.distributeperson;
	}

	public void setDistributeperson(String distributeperson) {
		this.distributeperson = distributeperson;
	}

	public String getResponsiblepersonid() {
		return this.responsiblepersonid;
	}

	public void setResponsiblepersonid(String responsiblepersonid) {
		this.responsiblepersonid = responsiblepersonid;
	}

	public String getResponsibleperson() {
		return this.responsibleperson;
	}

	public void setResponsibleperson(String responsibleperson) {
		this.responsibleperson = responsibleperson;
	}

	public Date getDistributedate() {
		return this.distributedate;
	}

	public void setDistributedate(Date distributedate) {
		this.distributedate = distributedate;
	}

	public String getLastpersonid() {
		return this.lastpersonid;
	}

	public void setLastpersonid(String lastpersonid) {
		this.lastpersonid = lastpersonid;
	}

	public String getLastperson() {
		return this.lastperson;
	}

	public void setLastperson(String lastperson) {
		this.lastperson = lastperson;
	}

}