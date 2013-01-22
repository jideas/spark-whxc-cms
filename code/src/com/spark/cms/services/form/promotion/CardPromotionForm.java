/**
 * 
 */
package com.spark.cms.services.form.promotion;

/**
 * @author Jideas
 * 
 */
public class CardPromotionForm {

	private String recid, amount, status, detailId, detail, count,type;

	public String getRecid() {
		return recid;
	}

	public String getAmount() {
		return amount;
	}

	public String getStatus() {
		return status;
	}
 
	public void setRecid(String recid) {
		this.recid = recid;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDetailId() {
		return detailId;
	}

	public String getDetail() {
		return detail;
	}

	public String getCount() {
		return count;
	}

	public void setDetailId(String detailId) {
		this.detailId = detailId;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public void setCount(String count) {
		this.count = count;
	}
 
}
