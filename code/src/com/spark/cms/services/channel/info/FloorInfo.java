package com.spark.cms.services.channel.info;

import com.spark.cms.services.vo.ChannelVo;
import com.spark.cms.services.vo.FloorAdvertisingVo;
import com.spark.cms.services.vo.FloorVo;

@SuppressWarnings("serial")
public class FloorInfo extends FloorVo {

	private ChannelVo[] channelVos;
	private FloorAdvertisingVo[] advertisings;

	public ChannelVo[] getChannelVos() {
		return channelVos;
	}

	public void setChannelVos(ChannelVo[] channelVos) {
		this.channelVos = channelVos;
	}

	public FloorAdvertisingVo[] getAdvertisings() {
		return advertisings;
	}

	public void setAdvertisings(FloorAdvertisingVo[] advertisings) {
		this.advertisings = advertisings;
	}
	
	
	
}
