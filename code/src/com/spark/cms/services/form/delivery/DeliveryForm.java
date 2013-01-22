/**
 * 
 */
package com.spark.cms.services.form.delivery;

/**
 * @author Jideas
 * 
 */
public class DeliveryForm {

	private String recid, dcount, dprice, remark, lastModify, lastModifyDate, status;

	public String getRecid() {
		return recid;
	}

	public String getDcount() {
		return dcount;
	}

	public String getDprice() {
		return dprice;
	}

	public String getRemark() {
		return remark;
	}

	public String getLastModify() {
		return lastModify;
	}

	public String getLastModifyDate() {
		return lastModifyDate;
	}

	public String getStatus() {
		return status;
	}

	public void setRecid(String recid) {
		this.recid = recid;
	}

	public void setDcount(String dcount) {
		this.dcount = dcount;
	}

	public void setDprice(String dprice) {
		this.dprice = dprice;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setLastModify(String lastModify) {
		this.lastModify = lastModify;
	}

	public void setLastModifyDate(String lastModifyDate) {
		this.lastModifyDate = lastModifyDate;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
