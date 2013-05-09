package com.spark.cms.services.member.key;

import com.spark.base.common.key.LimitKey;

public class GetMemberListKey extends LimitKey {
	private String beginDate;
	private String endDate;

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public GetMemberListKey(int offset, int pageSize, boolean queryTotalCount) {
		super(offset, pageSize, queryTotalCount);
	}

}
