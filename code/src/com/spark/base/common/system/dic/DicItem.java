package com.spark.base.common.system.dic;

public class DicItem {
	private String code;
	private String title;
	private String parentcode;

	public DicItem(String code, String title) {
		this.code = code;
		this.title = title;
	}

	public DicItem(String code, String title,String parentcode) {
		this.parentcode = parentcode;
		this.code = code;
		this.title = title;
	}

	public String getCode() {
		return code;
	}

	public String getTitle() {
		return title;
	}

	public String getParentcode() {
		return parentcode;
	}
}
