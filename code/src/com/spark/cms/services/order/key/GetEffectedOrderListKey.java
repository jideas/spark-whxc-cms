package com.spark.cms.services.order.key;

import java.util.Date;

import com.spark.base.common.key.LimitKey;

public class GetEffectedOrderListKey extends LimitKey {

	private String memberId;
	private boolean thisMonth = true;
	public GetEffectedOrderListKey(int offset, int pageSize, boolean queryTotalCount) {
		super(offset, pageSize, queryTotalCount);
	}
	public boolean isThisMonth() {
		return thisMonth;
	}
	public void setThisMonth(boolean thisMonth) {
		this.thisMonth = thisMonth;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public boolean isLog() {
		return log;
	}
	public void setLog(boolean log) {
		this.log = log;
	}

	private boolean log = false;
	
	private Date beginDate;
	private Date endDate;
	public Date getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
}
