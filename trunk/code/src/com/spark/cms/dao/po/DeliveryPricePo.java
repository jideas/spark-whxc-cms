/**
 * 
 */
package com.spark.cms.dao.po;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Jideas
 * 
 */
@Entity
@Table(name = "cms_delivery_price")
@SuppressWarnings("serial")
public class DeliveryPricePo implements Serializable {
	private byte[] recid;
	private Long recver;
	private int dcount;
	private double dprice;
	private boolean single;
	private String lastModify;
	private Date lastModifyDate;
	private String remark;
	private boolean activing;

	@Id
	@Column(name = "RECID", unique = true, nullable = false)
	public byte[] getRecid() {
		return recid;
	}

	@Column(name = "RECVER")
	public Long getRecver() {
		return recver;
	}

	@Column(name = "dcount")
	public int getDcount() {
		return dcount;
	}

	@Column(name = "dprice")
	public double getDprice() {
		return dprice;
	}

	@Column(name = "single")
	public boolean isSingle() {
		return single;
	}

	@Column(name = "lastModify")
	public String getLastModify() {
		return lastModify;
	}

	@Column(name = "lastModifyDate")
	public Date getLastModifyDate() {
		return lastModifyDate;
	}

	@Column(name = "remark")
	public String getRemark() {
		return remark;
	}
	@Column(name = "activing")
	public boolean isActiving() {
		return activing;
	}

	public void setActiving(boolean activing) {
		this.activing = activing;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setRecid(byte[] recid) {
		this.recid = recid;
	}

	public void setRecver(Long recver) {
		this.recver = recver;
	}

	public void setDcount(int dcount) {
		this.dcount = dcount;
	}

	public void setDprice(double dprice) {
		this.dprice = dprice;
	}

	public void setSingle(boolean single) {
		this.single = single;
	}

	public void setLastModify(String lastModify) {
		this.lastModify = lastModify;
	}

	public void setLastModifyDate(Date lastModifyDate) {
		this.lastModifyDate = lastModifyDate;
	}

}
