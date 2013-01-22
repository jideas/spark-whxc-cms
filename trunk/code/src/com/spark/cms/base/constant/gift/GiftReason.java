/**
 * 
 */
package com.spark.cms.base.constant.gift;

/**
 * @author Jideas
 *
 */
public enum GiftReason {
	Charge("01", "³äÖµËÍÔùÆ·");
	private String code;
	private String title;

	private GiftReason(String code, String title) {
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
