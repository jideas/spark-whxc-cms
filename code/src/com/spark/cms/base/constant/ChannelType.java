package com.spark.cms.base.constant;

public enum ChannelType {

	GoodsChannel("01", "��Ʒ��Ŀ"), ContentChannel("02", "������Ŀ"), AdChannel("03", "���λ"), ;
	private String code;
	private String title;

	private ChannelType(String code, String title) {
		this.code = code;
		this.title = title;
	}

	public String getCode() {
		return code;
	}

	public String getTitle() {
		return title;
	}
}
