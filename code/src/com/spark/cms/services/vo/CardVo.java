package com.spark.cms.services.vo;

import java.util.Date;

import com.spark.base.common.utils.DateUtil;
import com.spark.base.common.utils.DoubleUtil;
import com.spark.cms.base.constant.card.CardStatus;
import com.spark.cms.services.form.card.CardShowForm;

/**
 * CmsCard entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
public class CardVo implements java.io.Serializable {

	// Fields

	private String recid;
	private Long recver;
	private String ordinal;
	private String cardno;
	private String password;
	private Double amount;
	private String secretkey;
	private String creatorid;
	private String creator;
	private Date createdate;
	private String activepersonid;
	private Date activedate;
	private String distributepersonid;
	private String responsiblepersonid;
	private Date distributedate;
	private String usedpersonid;
	private Date useddate;
	private String stationid;
	private String status;
	private Date stopdate;
	private String activeperson;
	private String distributeperson;
	private String responsibleperson;
	private String usedperson;
	private String stationname;
	private long lastDate;

	// Constructors

	/** default constructor */
	public CardVo() {
	}

	// Property accessors

	public long getLastDate() {
		return lastDate;
	}

	public void setLastDate(long lastDate) {
		this.lastDate = lastDate;
	}

	public String getRecid() {
		return this.recid;
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

	public String getOrdinal() {
		return this.ordinal;
	}

	public void setOrdinal(String ordinal) {
		this.ordinal = ordinal;
	}

	public String getCardno() {
		return this.cardno;
	}

	public void setCardno(String cardno) {
		this.cardno = cardno;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getSecretkey() {
		return this.secretkey;
	}

	public void setSecretkey(String secretkey) {
		this.secretkey = secretkey;
	}

	public String getCreatorid() {
		return this.creatorid;
	}

	public void setCreatorid(String creatorid) {
		this.creatorid = creatorid;
	}

	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreatedate() {
		return this.createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public String getActivepersonid() {
		return this.activepersonid;
	}

	public void setActivepersonid(String activepersonid) {
		this.activepersonid = activepersonid;
	}

	public String getActiveperson() {
		return this.activeperson;
	}

	public void setActiveperson(String activeperson) {
		this.activeperson = activeperson;
	}

	public Date getActivedate() {
		return this.activedate;
	}

	public void setActivedate(Date activedate) {
		this.activedate = activedate;
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

	public String getUsedpersonid() {
		return this.usedpersonid;
	}

	public void setUsedpersonid(String usedpersonid) {
		this.usedpersonid = usedpersonid;
	}

	public String getUsedperson() {
		return this.usedperson;
	}

	public void setUsedperson(String usedperson) {
		this.usedperson = usedperson;
	}

	public Date getUseddate() {
		return this.useddate;
	}

	public void setUseddate(Date useddate) {
		this.useddate = useddate;
	}

	public String getStationid() {
		return this.stationid;
	}

	public void setStationid(String stationid) {
		this.stationid = stationid;
	}

	public String getStationname() {
		return this.stationname;
	}

	public void setStationname(String stationname) {
		this.stationname = stationname;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getStopdate() {
		return this.stopdate;
	}

	public void setStopdate(Date stopdate) {
		this.stopdate = stopdate;
	}

	public CardShowForm toShowForm() {
		CardShowForm form = new CardShowForm();
		form.setRecid(this.getRecid());
		form.setCardNo(this.cardno);
		form.setCreateDate(DateUtil.dateFromat(this.createdate));
		form.setCreator(this.creator);
		form.setOrdinal(this.ordinal);
		form.setStatus(CardStatus.getStatus(this.status).getTitle());
		form.setAmount(DoubleUtil.getRoundStr(this.getAmount()) + "Ԫ");
		form.setActivedate(DateUtil.dateFromat(this.activedate));
		form.setDistributedate(DateUtil.dateFromat(this.distributedate));
		form.setUseddate(DateUtil.dateFromat(this.useddate));
		form.setStopdate(DateUtil.dateFromat(this.stopdate));
		form.setActiveperson(this.activeperson);
		form.setDistributeperson(this.distributeperson);
		form.setUsedperson(usedperson);
		form.setStationname(stationname);
		form.setResponsibleperson(responsibleperson);
		form.setLastDate(DateUtil.dateFromat(this.lastDate));
		return form;
	}

}