package com.spark.cms.dao.po;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * CmsMemberDeliverLog entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "cms_member_deliver_log")
@SuppressWarnings("serial")
public class MemberDeliverLogPo implements java.io.Serializable {

	// Fields

	private byte[] recid;
	private Long recver;
	private byte[] memberid;
	private byte[] deliveryid;
	private byte[] relabillsid;
	private String relabillsno;
	private Long count;
	private Date occurdate;

	// Constructors

	/** default constructor */
	public MemberDeliverLogPo() {
	}

	/** minimal constructor */
	public MemberDeliverLogPo(byte[] recid) {
		this.recid = recid;
	}

	/** full constructor */
	public MemberDeliverLogPo(byte[] recid, Long recver, byte[] memberid, byte[] deliveryid,
			byte[] relabillsid, String relabillsno, Long count, Date occurdate) {
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

	@Column(name = "DELIVERYID")
	public byte[] getDeliveryid() {
		return this.deliveryid;
	}

	public void setDeliveryid(byte[] deliveryid) {
		this.deliveryid = deliveryid;
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

	@Column(name = "COUNT", precision = 10, scale = 0)
	public Long getCount() {
		return this.count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "OCCURDATE", length = 19)
	public Date getOccurdate() {
		return this.occurdate;
	}

	public void setOccurdate(Date occurdate) {
		this.occurdate = occurdate;
	}

}