package com.spark.cms.services.channel;

import com.spark.base.common.key.LimitKey;
import com.spark.cms.base.constant.ChannelContentsStatus;

public class GetChannelAdvertisingKey extends LimitKey {
	private String channelId;
	private ChannelContentsStatus status;
	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public ChannelContentsStatus getStatus() {
		return status;
	}

	public void setStatus(ChannelContentsStatus status) {
		this.status = status;
	}

	
	
	
}
