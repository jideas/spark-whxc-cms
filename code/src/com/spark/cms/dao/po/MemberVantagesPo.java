package com.spark.cms.dao.po;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CmsMemberVantages entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "cms_member_vantages")
@SuppressWarnings("serial")
public class MemberVantagesPo implements java.io.Serializable {

	// Fields

	private byte[] recid;
	private Long recver;
	private byte[] memberid;
	private String vtype;
	private byte[] relabillsid;
	private String relabillsno;
	private Double vantages;
	private Date occurdate;

	private String modifyPerson;
	private String modifyPersonId;

	@Column(name = "modifyPerson")
	public String getModifyPerson() {
		return modifyPerson;
	}

	@Column(name = "modifyPersonId")
	public String getModifyPersonId() {
		return modifyPersonId;
	}

	public void setModifyPerson(String modifyPerson) {
		this.modifyPerson = modifyPerson;
	}

	public void setModifyPersonId(String modifyPersonId) {
		this.modifyPersonId = modifyPersonId;
	}

	// Constructors

	/** default constructor */
	public MemberVantagesPo() {
	}

	/** minimal constructor */
	public MemberVantagesPo(byte[] recid) {
		this.recid = recid;
	}

	/** full constructor */
	public MemberVantagesPo(byte[] recid, Long recver, byte[] memberid, String vtype, byte[] relabillsid,
			String relabillsno, Double vantages, Date occurdate) {
		this.recid = recid;
		this.recver = recver;
		this.memberid = memberid;
		this.vtype = vtype;
		this.relabillsid = relabillsid;
		this.relabillsno = relabillsno;
		this.vantages = vantages;
		this.occurdate = occurdate;
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

	@Column(name = "MEMBERID")
	public byte[] getMemberid() {
		return this.memberid;
	}

	public void setMemberid(byte[] memberid) {
		this.memberid = memberid;
	}

	@Column(name = "VTYPE", length = 2)
	public String getVtype() {
		return this.vtype;
	}

	public void setVtype(String vtype) {
		this.vtype = vtype;
	}

	@Column(name = "RELABILLSID")
	public byte[] getRelabillsid() {
		return this.relabillsid;
	}

	public void setRelabillsid(byte[] relabillsid) {
		this.relabillsid = relabillsid;
	}

	@Column(name = "RELABILLSNO", length = 30)
	public String getRelabillsno() {
		return this.relabillsno;
	}

	public void setRelabillsno(String relabillsno) {
		this.relabillsno = relabillsno;
	}

	@Column(name = "VANTAGES", precision = 10)
	public Double getVantages() {
		return this.vantages;
	}

	public void setVantages(Double vantages) {
		this.vantages = vantages;
	}

	@Column(name = "OCCURDATE")
	public Date getOccurdate() {
		return this.occurdate;
	}

	public void setOccurdate(Date occurdate) {
		this.occurdate = occurdate;
	}

}