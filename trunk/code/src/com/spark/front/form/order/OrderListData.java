package com.spark.front.form.order;

public class OrderListData {

	private String html;
	private int totalCount;
	public String getHtml() {
		return html;
	}
	public void setHtml(String html) {
		this.html = html;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	private boolean success;
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	private boolean noLogin;
	public boolean isNoLogin() {
		return noLogin;
	}
	public void setNoLogin(boolean noLogin) {
		this.noLogin = noLogin;
	}
	
	
}
