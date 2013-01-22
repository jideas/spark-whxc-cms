/**
 * 
 */
package com.spark.cms.base.constant.card;

/**
 * @author Jideas
 * 
 */
public enum CardStatus {

	Created("01", "����ӡ"),

	Printed("02", "�Ѵ�ӡ"),

	Actived("03", "���ַ�"),

	Distributed("04", "�ѷַ�"),

	Used("05", "��ʹ��"),

	Canceled("06", "������");

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
