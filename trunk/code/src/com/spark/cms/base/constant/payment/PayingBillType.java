/**
 * 
 */
package com.spark.cms.base.constant.payment;

/**
 * @author Jideas
 * 
 */
public enum PayingBillType {

	UnionCharge("01"), UnionOrder("02"), AliCharge("03"), AliOrder("04"),
	;
	private String code;

	private PayingBillType(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}
