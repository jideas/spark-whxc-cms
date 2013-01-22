/**
 * 
 */
package com.spark.cms.base.constant.payment;

/**
 * @author Jideas
 * 
 */
public enum PayingBillStatus {

	Paying("01"),

	Failure("02"),

	Success("03"),

	;
	private String code;

	private PayingBillStatus(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}
