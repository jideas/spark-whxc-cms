package com.spark.cms.dao.po;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CmsMemberDelivery entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "cms_member_delivery")
@SuppressWarnings("serial")
public class MemberDeliveryPo implements java.io.Serializable {

	// Fields

	private byte[] recid;
	private Long recver;
	private byte[] member;
	private Date begindate;
	private Date enddate;
	private long dcount;
	private Long counted;
	private double lockedDeliveryBalance;

	/**
	 * 锁定上门包月送货次数
	 * @return
	 */
	@Column(name = "lockedDeliveryBalance")
	public double getLockedDeliveryBalance() {
		return lockedDeliveryBalance;
	}

	public void setLockedDeliveryBalance(double lockedDeliveryBalance) {
		this.lockedDeliveryBalance = lockedDeliveryBalance;
	}
	// Constructors

	/** default constructor */
	public MemberDeliveryPo() {
	}

	/** minimal constructor */
	public MemberDeliveryPo(byte[] recid) {
		this.recid = recid;
	}

	/** full constructor */
	public MemberDeliveryPo(byte[] recid, Long recver, byte[] member, Date begindate, Date enddate, long dcount,
			Long counted) {
		this.recid = recid;
		this.recver = recver;
		this.member = member;
		this.begindate = begindate;
		this.enddate = enddate;
		this.dcount = dcount;
		this.counted = counted;
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

	@Column(name = "MEMBER")
	public byte[] getMember() {
		return this.member;
	}

	public void setMember(byte[] member) {
		this.member = member;
	}
 
	@Column(name = "BEGINDATE")
	public Date getBegindate() {
		return this.begindate;
	}

	public void setBegindate(Date begindate) {
		this.begindate = begindate;
	}
 
	@Column(name = "ENDDATE" )
	public Date getEnddate() {
		return this.enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	@Column(name = "COUNTED", precision = 10, scale = 0)
	public Long getCounted() {
		return this.counted;
	}

	public void setCounted(Long counted) {
		this.counted = counted;
	}

	@Column(name = "DCOUNT", precision = 10, scale = 0)
	public long getDcount() {
		return dcount;
	}

	public void setDcount(long dcount) {
		this.dcount = dcount;
	}

}