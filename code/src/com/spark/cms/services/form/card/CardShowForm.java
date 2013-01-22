package com.spark.cms.services.form.card;

public class CardShowForm {

	private String recid;

	private String amount;
	private String cardNo;
	private String activedate;
	private String distributedate,lastDate;
	private String useddate;
	private String stopdate;
	private String activeperson;
	private String distributeperson;
	private String responsibleperson;
	private String usedperson;
	private String stationname;
	/**
	 * Ë³ÐòºÅ
	 */
	private String ordinal;
	private String createDate, creator, status;

	public String getLastDate() {
		return lastDate;
	}

	public void setLastDate(String lastDate) {
		this.lastDate = lastDate;
	}

	public String getActiveperson() {
		return activeperson;
	}

	public String getDistributeperson() {
		return distributeperson;
	}

	public String getResponsibleperson() {
		return responsibleperson;
	}

	public String getUsedperson() {
		return usedperson;
	}

	public String getStationname() {
		return stationname;
	}

	public void setActiveperson(String activeperson) {
		this.activeperson = activeperson;
	}

	public void setDistributeperson(String distributeperson) {
		this.distributeperson = distributeperson;
	}

	public void setResponsibleperson(String responsibleperson) {
		this.responsibleperson = responsibleperson;
	}

	public void setUsedperson(String usedperson) {
		this.usedperson = usedperson;
	}

	public void setStationname(String stationname) {
		this.stationname = stationname;
	}

	public String getActivedate() {
		return activedate;
	}

	public String getDistributedate() {
		return distributedate;
	}

	public String getUseddate() {
		return useddate;
	}

	public String getStopdate() {
		return stopdate;
	}

	public void setActivedate(String activedate) {
		this.activedate = activedate;
	}

	public void setDistributedate(String distributedate) {
		this.distributedate = distributedate;
	}

	public void setUseddate(String useddate) {
		this.useddate = useddate;
	}

	public void setStopdate(String stopdate) {
		this.stopdate = stopdate;
	}

	public String getRecid() {
		return recid;
	}

	public String getAmount() {
		return amount;
	}

	public String getCardNo() {
		return cardNo;
	}

	public String getOrdinal() {
		return ordinal;
	}

	public String getCreateDate() {
		return createDate;
	}

	public String getCreator() {
		return creator;
	}

	public String getStatus() {
		return status;
	}

	public void setRecid(String recid) {
		this.recid = recid;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public void setOrdinal(String ordinal) {
		this.ordinal = ordinal;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
