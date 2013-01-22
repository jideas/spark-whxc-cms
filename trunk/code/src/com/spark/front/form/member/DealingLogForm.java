/**
 * 
 */
package com.spark.front.form.member;

/**
 * @author Jideas
 * 
 */
public class DealingLogForm {
	private String relaBillsNo, dealType, sumAmount, subAmount, date;

	public String getRelaBillsNo() {
		return relaBillsNo;
	}

	public String getDealType() {
		return dealType;
	}

	public String getDate() {
		return date;
	}

	public String getSumAmount() {
		return sumAmount;
	}

	public String getSubAmount() {
		return subAmount;
	}

	public void setSumAmount(String sumAmount) {
		this.sumAmount = sumAmount;
	}

	public void setSubAmount(String subAmount) {
		this.subAmount = subAmount;
	}

	public void setRelaBillsNo(String relaBillsNo) {
		this.relaBillsNo = relaBillsNo;
	}

	public void setDealType(String dealType) {
		this.dealType = dealType;
	}

	public void setDate(String date) {
		this.date = date;
	}
}
