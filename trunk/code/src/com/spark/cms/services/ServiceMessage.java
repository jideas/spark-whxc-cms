/**
 * 
 */
package com.spark.cms.services;

import org.springframework.http.ResponseEntity;

import com.spark.cms.common.MessageModel;
import com.spark.cms.common.ResponseEntityUtil;

/**
 * 服务返回结果
 * 
 * @author Jideas
 */
@SuppressWarnings("serial")
public class ServiceMessage extends RuntimeException {

	private boolean operSuccess = false;
	private Object returnObj;

	/**
	 * @param string
	 */
	public ServiceMessage(String message) {
		super(message);
	}

	/**
	 * @param string
	 */
	public ServiceMessage(boolean operSuccess, String message) {
		super(message);
		this.operSuccess = operSuccess;
	}

	/**
	 * @param string
	 */
	public ServiceMessage(boolean operSuccess, String message, Object returnObj) {
		super(message);
		this.operSuccess = operSuccess;
		this.returnObj = returnObj;
	}

	public ResponseEntity<MessageModel> getMessageModel() {
		return ResponseEntityUtil.getResponseEntity(new MessageModel(operSuccess, this.getMessage(), this.returnObj));
	}

	public boolean isOperSuccess() {
		return operSuccess;
	}

	public Object getReturnObj() {
		return returnObj;
	}

}
