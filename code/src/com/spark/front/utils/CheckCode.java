/**
 * 
 */
package com.spark.front.utils;

/**
 * ��֤��
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
	 * ��������id
	 */
	private String objectId;

	private Object relaObj;

	/**
	 * ��֤��
	 */
	private String code;

	/**
	 * ����ʱ��
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
