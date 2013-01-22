package com.spark.cms.services.vo;

import java.util.Date;

/**
 * CmsMemberDeliverLog entity.
 * 
 * @author MyEclipse Persistence Tools
 */ 
@SuppressWarnings("serial")
public class MemberDeliverLogVo implements java.io.Serializable {

	// Fields

	private String recid; 
	private Long recver;
	private String memberid;
	private String deliveryid;
	private String relabillsid;
	private String relabillsno;
	private Long count;
	private Date occurdate;

	// Constructors

	/** default constructor */
	public MemberDeliverLogVo() {
	}

	/** minimal constructor */
	public MemberDeliverLogVo(String recid) {
		this.recid = recid;
	}

	/** full constructor */
	public MemberDeliverLogVo(String recid,   Long recver, String memberid, String deliveryid,
			String relabillsid, String relabillsno, Long count, Date occurdate) {
		this.recid = recid; 
		this.recver = recver;
		this.memberid = memberid;
		this.deliveryid = deliveryid;
		this.relabillsid = relabillsid;
		this.relabillsno = relabillsno;
		this.count = count;
		this.occurdate = occurdate;
	}

	// Property accessors

	public Long getRecver() {
		return recver;
	}

	public void setRecver(Long recver) {
		this.recver = recver;
	}

	public String getRecid() {
		return this.recid;
	}

	public void setRecid(String recid) {
		this.recid = recid;
	} 

	public String getMemberid() {
		return this.memberid;
	}

	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}

	public String getDeliveryid() {
		return this.deliveryid;
	}

	public void setDeliveryid(String deliveryid) {
		this.deliveryid = deliveryid;
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

	public Long getCount() {
		return this.count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public Date getOccurdate() {
		return this.occurdate;
	}

	public void setOccurdate(Date occurdate) {
		this.occurdate = occurdate;
	}

}