/**
 * 
 */
package com.spark.cms.services.goodsPromotion.result;

/**
 * @author Jideas
 * 
 */
public class OperationResult {

	public OperationResult(boolean ok, String message) {
		this.message = message;
		this.ok = ok;
	}

	private boolean ok;

	private String message;

	public boolean isOk() {
		return ok;
	}

	public String getMessage() {
		return message;
	}
}
