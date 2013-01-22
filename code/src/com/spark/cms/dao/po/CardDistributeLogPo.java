package com.spark.cms.dao.po;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * CmsCardDistributeLog entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "cms_card_distribute_log")
@SuppressWarnings("serial")
public class CardDistributeLogPo implements java.io.Serializable {

	// Fields

	private byte[] recid;
	private Long recver;
	private byte[] cardid;
	private byte[] distributepersonid;
	private String distributeperson;
	private String responsiblepersonid;
	private String responsibleperson;
	private Date distributedate;
	private String lastpersonid;
	private String lastperson;

	// Constructors

	/** default constructor */
	public CardDistributeLogPo() {
	}

	/** minimal constructor */
	public CardDistributeLogPo(byte[] recid) {
		this.recid = recid;
	}

	/** full constructor */
	public CardDistributeLogPo(byte[] recid, Long recver, byte[] cardid, byte[] distributepersonid,
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

	@Column(name = "CARDID")
	public byte[] getCardid() {
		return this.cardid;
	}

	public void setCardid(byte[] cardid) {
		this.cardid = cardid;
	}

	@Column(name = "DISTRIBUTEPERSONID")
	public byte[] getDistributepersonid() {
		return this.distributepersonid;
	}

	public void setDistributepersonid(byte[] distributepersonid) {
		this.distributepersonid = distributepersonid;
	}

	@Column(name = "DISTRIBUTEPERSON", length = 30)
	public String getDistributeperson() {
		return this.distributeperson;
	}

	public void setDistributeperson(String distributeperson) {
		this.distributeperson = distributeperson;
	}

	@Column(name = "RESPONSIBLEPERSONID")
	public String getResponsiblepersonid() {
		return this.responsiblepersonid;
	}

	public void setResponsiblepersonid(String responsiblepersonid) {
		this.responsiblepersonid = responsiblepersonid;
	}

	@Column(name = "RESPONSIBLEPERSON", length = 30)
	public String getResponsibleperson() {
		return this.responsibleperson;
	}

	public void setResponsibleperson(String responsibleperson) {
		this.responsibleperson = responsibleperson;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DISTRIBUTEDATE", length = 19)
	public Date getDistributedate() {
		return this.distributedate;
	}

	public void setDistributedate(Date distributedate) {
		this.distributedate = distributedate;
	}

	@Column(name = "LASTPERSONID")
	public String getLastpersonid() {
		return this.lastpersonid;
	}

	public void setLastpersonid(String lastpersonid) {
		this.lastpersonid = lastpersonid;
	}

	@Column(name = "LASTPERSON", length = 30)
	public String getLastperson() {
		return this.lastperson;
	}

	public void setLastperson(String lastperson) {
		this.lastperson = lastperson;
	}

}