/**
 * 
 */
package com.spark.cms.services.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Jideas
 * 
 */
@SuppressWarnings("serial")
public class DeliveryPriceVo implements Serializable {
	private String recid;
	private Long recver;
	private int dcount;
	private double dprice;
	private boolean single;
	private String lastModify;
	private Date lastModifyDate;
	private String remark;
	private boolean activing;

	public boolean isActiving() {
		return activing;
	}

	public void setActiving(boolean activing) {
		this.activing = activing;
	}

	public String getRecid() {
		return recid;
	}

	public Long getRecver() {
		return recver;
	}

	public int getDcount() {
		return dcount;
	}

	public double getDprice() {
		return dprice;
	}

	public boolean isSingle() {
		return single;
	}

	public String getLastModify() {
		return lastModify;
	}

	public Date getLastModifyDate() {
		return lastModifyDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setRecid(String recid) {
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
