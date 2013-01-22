package com.spark.cms.services.vo;

import java.util.Date;

import javax.persistence.Column;

/**
 * CmsMemberDelivery entity.
 * 
 * @author MyEclipse Persistence Tools
 */ 
@SuppressWarnings("serial")
public class MemberDeliveryVo implements java.io.Serializable {

	// Fields

	private String recid;
	private Long recver;
	private String member;
	private Date begindate;
	private Date enddate;
	private long dcount;
	private Long counted;
	private double lockedDeliveryBalance;

	/**
	 * 锁定上门包月送货次数
	 * @return
	 */
	public double getLockedDeliveryBalance() {
		return lockedDeliveryBalance;
	}

	public void setLockedDeliveryBalance(double lockedDeliveryBalance) {
		this.lockedDeliveryBalance = lockedDeliveryBalance;
	}

	// Constructors

	/** default constructor */
	public MemberDeliveryVo() {
	}

	/** minimal constructor */
	public MemberDeliveryVo(String recid) {
		this.recid = recid;
	}

	/** full constructor */
	public MemberDeliveryVo(String recid, Long recver, String member, Date begindate, Date enddate,
			long dcount, Long counted) {
		this.recid = recid;
		this.recver = recver;
		this.member = member;
		this.begindate = begindate;
		this.enddate = enddate;
		this.dcount = dcount;
		this.counted = counted;
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

	public String getMember() {
		return this.member;
	}

	public void setMember(String member) {
		this.member = member;
	}

	public Date getBegindate() {
		return this.begindate;
	}

	public void setBegindate(Date begindate) {
		this.begindate = begindate;
	}

	public Date getEnddate() {
		return this.enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	} 
	public Long getCounted() {
		return this.counted;
	}

	public void setCounted(Long counted) {
		this.counted = counted;
	}

	public long getDcount() {
		return dcount;
	}

	public void setDcount(long dcount) {
		this.dcount = dcount;
	}

}