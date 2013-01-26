/**
 * 
 */
package com.spark.cms.base.constant.gift;

/**
 * @author Jideas
 * 
 */
public enum GiftStatus {
	Created("01", "新增"),
	Taken("02","已领");
	private String code;
	private String title;

	private GiftStatus(String code, String title) {
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
