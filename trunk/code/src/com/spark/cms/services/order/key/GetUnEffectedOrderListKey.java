package com.spark.cms.services.order.key;

import com.spark.base.common.key.LimitKey;

public class GetUnEffectedOrderListKey extends LimitKey {

	private String memberId;
	private boolean thisMonth = true;
	public GetUnEffectedOrderListKey(int offset, int pageSize, boolean queryTotalCount) {
		super(offset, pageSize, queryTotalCount);
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public boolean isThisMonth() {
		return thisMonth;
	}
	public void setThisMonth(boolean thisMonth) {
		this.thisMonth = thisMonth;
	}
	
}
