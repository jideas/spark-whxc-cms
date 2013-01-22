/**
 * 
 */
package com.spark.front.utils;

/**
 * 验证码
 * 
 * @author Jideas
 * 
 */
public class CheckCode {

	public CheckCode(String objectId, String code, long theLastTime, Object relaObj) {
		this.objectId = objectId;
		this.code = code;
		this.theLastTime = theLastTime;
		this.relaObj = relaObj;
	}

	/**
	 * 关联对象id
	 */
	private String objectId;

	private Object relaObj;

	/**
	 * 验证码
	 */
	private String code;

	/**
	 * 到期时间
	 */
	private long theLastTime;

	public String getObjectId() {
		return objectId;
	}

	public String getCode() {
		return code;
	}

	public long getTheLastTime() {
		return theLastTime;
	}

	public Object getRelaObj() {
		return relaObj;
	}
}
