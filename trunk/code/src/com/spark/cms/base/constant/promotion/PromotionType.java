/**
 * 
 */
package com.spark.cms.base.constant.promotion;

/**
 * @author Jideas
 * 
 */
public enum PromotionType {

	OrderGift("01", "满额送赠品"),

	OrderVantages("02", "满额送积分"),

	OrderFreeDelivery("03", "满额送货上门"),

	GoodsTimes("04", "限时抢购"),

	GoodsCounts("05", "限量抢购"), 

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
