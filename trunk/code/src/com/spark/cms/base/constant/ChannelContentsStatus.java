package com.spark.cms.base.constant;

public enum ChannelContentsStatus {
	ENABLE("01", "������"),
	DISABLE("02", "δ����");
	
	private String code;
	private String title;
	private ChannelContentsStatus(String code, String title) {
		this.code = code;
		this.title = title;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public String getCode() {
		return this.code;
	}
}
