package com.spark.front.form.order;

import com.spark.cms.services.vo.OrderVo;

public class OrderData {
	private boolean noLogin;
	private boolean success = true;
	private String errorMsg;
	private String[] orderIds;
	private String payType;
	private OrderVo orderVo;
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public String[] getOrderIds() {
		return orderIds;
	}
	public void setOrderId(String[] orderIds) {
		this.orderIds = orderIds;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public OrderVo getOrderVo() {
		return orderVo;
	}
	public void setOrderVo(OrderVo orderVo) {
		this.orderVo = orderVo;
	}
	public boolean isNoLogin() {
		return noLogin;
	}
	public void setNoLogin(boolean noLogin) {
		this.noLogin = noLogin;
	}
	
	
}
