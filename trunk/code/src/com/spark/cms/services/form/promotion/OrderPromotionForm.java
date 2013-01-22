/**
 * 
 */
package com.spark.cms.services.form.promotion;

/**
 * @author Jideas
 * 
 */
public class OrderPromotionForm {

	private String recid, beginAmount, endAmount = "--", detail,detailId ,count = "--", status, type;

	public String getRecid() {
		return recid;
	}

	public void setRecid(String recid) {
		this.recid = recid;
	}

	public String getBeginAmount() {
		return beginAmount;
	}

	public String getEndAmount() {
		return endAmount;
	}

	public String getStatus() {
		return status;
	}

	public String getDetailId() {
		return detailId;
	}

	public void setDetailId(String detailId) {
		this.detailId = detailId;
	}

	public void setBeginAmount(String beginAmount) {
		this.beginAmount = beginAmount;
	}

	public void setEndAmount(String endAmount) {
		this.endAmount = endAmount;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDetail() {
		return detail;
	}

	public String getCount() {
		return count;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public void setCount(String count) {
		this.count = count;
	}
}
