package com.spark.cms.services.member.key;

import com.spark.base.common.key.LimitKey;

public class GetMemberListKey extends LimitKey {

	public GetMemberListKey(int offset, int pageSize, boolean queryTotalCount) {
		super(offset, pageSize, queryTotalCount);
	}

	
}
