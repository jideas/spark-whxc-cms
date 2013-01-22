/**
 * 
 */
package com.spark.cms.base.constant.promotion;

/**
 * @author Jideas
 * 
 */
public enum PromotionType {

	OrderGift("01", "��������Ʒ"),

	OrderVantages("02", "�����ͻ���"),

	OrderFreeDelivery("03", "�����ͻ�����"),

	GoodsTimes("04", "��ʱ����"),

	GoodsCounts("05", "��������"), 

	;
	private String code;
	private String title;

	private PromotionType(String code, String title) {
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
