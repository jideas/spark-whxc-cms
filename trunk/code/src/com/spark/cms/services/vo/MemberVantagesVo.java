package com.spark.cms.services.vo;

import java.util.Date;

/**
 * CmsMemberVantages entity.
 * 
 * @author MyEclipse Persistence Tools
 */ 
@SuppressWarnings("serial")
public class MemberVantagesVo implements java.io.Serializable {

	// Fields

	private String recid;
	private Long recver;
	private String memberid;
	private String vtype;
	private String relabillsid;
	private String relabillsno;
	private Double vantages;
	private Date occurdate;
	private String occurdateStr;
	private String modifyPerson;
	private String modifyPersonId;
	private String vtypeStr;
 
	public String getVtypeStr() {
		return vtypeStr;
	}
	public void setVtypeStr(String vtypeStr) {
		this.vtypeStr = vtypeStr;
	}
	public String getModifyPerson() {
		return modifyPerson;
	} 
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

	public String getOccurdateStr() {
		return occurdateStr;
	}

	public void setOccurdateStr(String occurdateStr) {
		this.occurdateStr = occurdateStr;
	}

	/** default constructor */
	public MemberVantagesVo() {
	}

	/** minimal constructor */
	public MemberVantagesVo(String recid) {
		this.recid = recid;
	}

	/** full constructor */
	public MemberVantagesVo(String recid, Long recver, String memberid, String vtype, String relabillsid,
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

	public String getMemberid() {
		return this.memberid;
	}

	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}

	public String getVtype() {
		return this.vtype;
	}

	public void setVtype(String vtype) {
		this.vtype = vtype;
	}

	public String getRelabillsid() {
		return this.relabillsid;
	}

	public void setRelabillsid(String relabillsid) {
		this.relabillsid = relabillsid;
	}

	public String getRelabillsno() {
		return this.relabillsno;
	}

	public void setRelabillsno(String relabillsno) {
		this.relabillsno = relabillsno;
	}

	public Double getVantages() {
		return this.vantages;
	}

	public void setVantages(Double vantages) {
		this.vantages = vantages;
	}

	public Date getOccurdate() {
		return this.occurdate;
	}

	public void setOccurdate(Date occurdate) {
		this.occurdate = occurdate;
	}

}