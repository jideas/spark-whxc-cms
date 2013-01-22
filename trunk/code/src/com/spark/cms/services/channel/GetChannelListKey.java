package com.spark.cms.services.channel;

import com.spark.base.common.key.LimitKey;
import com.spark.cms.common.Constant.ChannelEnum.PageType;

public class GetChannelListKey extends LimitKey {
	private String floorId = null;
	private PageType pageType = null;
	public String getFloorId() {
		return floorId;
	}
	public void setFloorId(String floorId) {
		this.floorId = floorId;
	}
	public PageType getPageType() {
		return pageType;
	}
	public void setPageType(PageType pageType) {
		this.pageType = pageType;
	}
	
	
}
