package com.spark.cms.dao.po;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * CmsCard entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "cms_card")
@SuppressWarnings("serial")
public class CardPo implements java.io.Serializable {

	// Fields

	private byte[] recid;
	private Long recver;
	private String ordinal;
	private String cardno;
	private String password;
	private Double amount;
	private String secretkey;
	private byte[] creatorid;
	private String creator;
	private Date createdate;
	private byte[] activepersonid;
	private String activeperson;
	private Date activedate;
	private byte[] distributepersonid;
	private String distributeperson;
	private byte[] responsiblepersonid;
	private String responsibleperson;
	private Date distributedate;
	private byte[] usedpersonid;
	private String usedperson;
	private Date useddate;
	private byte[] stationid;
	private String stationname;
	private String status;
	private Date stopdate;

	private long lastDate;
	// Constructors

	/** default constructor */
	public CardPo() {
	}

	/** minimal constructor */
	public CardPo(byte[] recid) {
		this.recid = recid;
	}

	/** full constructor */
	public CardPo(byte[] recid, Long recver, String ordinal, String cardno, String password, Double amount, String secretkey,
			byte[] creatorid, String creator, Date createdate, byte[] activepersonid, String activeperson, Date activedate,
			byte[] distributepersonid, String distributeperson, byte[] responsiblepersonid, String responsibleperson,
			Date distributedate, byte[] usedpersonid, String usedperson, Date useddate, byte[] stationid, String stationname,
			String status, Date stopdate,long lastDate) {
		this.recid = recid;
		this.recver = recver;
		this.ordinal = ordinal;
		this.cardno = cardno;
		this.password = password;
		this.amount = amount;
		this.secretkey = secretkey;
		this.creatorid = creatorid;
		this.creator = creator;
		this.createdate = createdate;
		this.activepersonid = activepersonid;
		this.activeperson = activeperson;
		this.activedate = activedate;
		this.distributepersonid = distributepersonid;
		this.distributeperson = distributeperson;
		this.responsiblepersonid = responsiblepersonid;
		this.responsibleperson = responsibleperson;
		this.distributedate = distributedate;
		this.usedpersonid = usedpersonid;
		this.usedperson = usedperson;
		this.useddate = useddate;
		this.stationid = stationid;
		this.stationname = stationname;
		this.status = status;
		this.stopdate = stopdate;
		this.lastDate = lastDate;
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

	@Column(name = "ORDINAL")
	public String getOrdinal() {
		return this.ordinal;
	}

	public void setOrdinal(String ordinal) {
		this.ordinal = ordinal;
	}

	@Column(name = "CARDNO", length = 100)
	public String getCardno() {
		return this.cardno;
	}

	public void setCardno(String cardno) {
		this.cardno = cardno;
	}

	@Column(name = "PASSWORD", length = 100)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "AMOUNT", precision = 10)
	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Column(name = "SECRETKEY", length = 100)
	public String getSecretkey() {
		return this.secretkey;
	}

	public void setSecretkey(String secretkey) {
		this.secretkey = secretkey;
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

	@Column(name = "ACTIVEPERSONID")
	public byte[] getActivepersonid() {
		return this.activepersonid;
	}

	public void setActivepersonid(byte[] activepersonid) {
		this.activepersonid = activepersonid;
	}

	@Column(name = "ACTIVEPERSON", length = 100)
	public String getActiveperson() {
		return this.activeperson;
	}

	public void setActiveperson(String activeperson) {
		this.activeperson = activeperson;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "ACTIVEDATE", length = 19)
	public Date getActivedate() {
		return this.activedate;
	}

	public void setActivedate(Date activedate) {
		this.activedate = activedate;
	}

	@Column(name = "DISTRIBUTEPERSONID")
	public byte[] getDistributepersonid() {
		return this.distributepersonid;
	}

	public void setDistributepersonid(byte[] distributepersonid) {
		this.distributepersonid = distributepersonid;
	}

	@Column(name = "DISTRIBUTEPERSON", length = 100)
	public String getDistributeperson() {
		return this.distributeperson;
	}

	public void setDistributeperson(String distributeperson) {
		this.distributeperson = distributeperson;
	}

	@Column(name = "RESPONSIBLEPERSONID")
	public byte[] getResponsiblepersonid() {
		return this.responsiblepersonid;
	}

	public void setResponsiblepersonid(byte[] responsiblepersonid) {
		this.responsiblepersonid = responsiblepersonid;
	}

	@Column(name = "RESPONSIBLEPERSON", length = 100)
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

	@Column(name = "USEDPERSONID")
	public byte[] getUsedpersonid() {
		return this.usedpersonid;
	}

	public void setUsedpersonid(byte[] usedpersonid) {
		this.usedpersonid = usedpersonid;
	}

	@Column(name = "USEDPERSON", length = 100)
	public String getUsedperson() {
		return this.usedperson;
	}

	public void setUsedperson(String usedperson) {
		this.usedperson = usedperson;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "USEDDATE", length = 19)
	public Date getUseddate() {
		return this.useddate;
	}

	public void setUseddate(Date useddate) {
		this.useddate = useddate;
	}

	@Column(name = "STATIONID")
	public byte[] getStationid() {
		return this.stationid;
	}

	public void setStationid(byte[] stationid) {
		this.stationid = stationid;
	}

	@Column(name = "STATIONNAME", length = 100)
	public String getStationname() {
		return this.stationname;
	}

	public void setStationname(String stationname) {
		this.stationname = stationname;
	}

	@Column(name = "STATUS", length = 2)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "STOPDATE", length = 19)
	public Date getStopdate() {
		return this.stopdate;
	}

	public void setStopdate(Date stopdate) {
		this.stopdate = stopdate;
	}

	public long getLastDate() {
		return lastDate;
	}

	@Column(name = "lastDate")
	public void setLastDate(long lastDate) {
		this.lastDate = lastDate;
	}

}