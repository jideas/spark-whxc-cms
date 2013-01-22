/**
 * 
 */
package com.spark.cms.base.constant.card;

/**
 * @author Jideas
 * 
 */
public enum CardStatus {

	Created("01", "待打印"),

	Printed("02", "已打印"),

	Actived("03", "待分发"),

	Distributed("04", "已分发"),

	Used("05", "已使用"),

	Canceled("06", "已作废");

	private String code;
	private String title;

	private CardStatus(String code, String title) {
		this.code = code;
		this.title = title;
	}

	public String getCode() {
		return code;
	}

	public String getTitle() {
		return title;
	}

	public static CardStatus getStatus(String code) {
		if (Created.getCode().equals(code)) {
			return Created;
		} else if (Printed.getCode().equals(code)) {
			return Printed;
		} else if (Actived.getCode().equals(code)) {
			return Actived;
		} else if (Distributed.getCode().equals(code)) {
			return Distributed;
		} else if (Used.getCode().equals(code)) {
			return Used;
		} else if (Canceled.getCode().equals(code)) {
			return Canceled;
		}
		return null;
	}
}
