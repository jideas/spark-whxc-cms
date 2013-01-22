package com.spark.cms.services.member.key;

import java.util.Date;

import com.spark.base.common.key.LimitKey;

public class GetDealingListKey extends LimitKey {

	private String memberId;
	public GetDealingListKey(int offset, int pageSize, boolean queryTotalCount) {
		super(offset, pageSize, queryTotalCount);
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
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
