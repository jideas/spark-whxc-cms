package com.spark.cms.services.channel;

public class ChannelThrowable extends Throwable {

	private String message;

	public ChannelThrowable(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
	
}
