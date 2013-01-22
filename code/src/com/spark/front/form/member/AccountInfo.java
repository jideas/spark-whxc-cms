/**
 * 
 */
package com.spark.front.form.member;

/**
 * @author Jideas
 * 
 */
public class AccountInfo {
	private String memberId;
	private String moneyBalance;
	private String vanteges;
	private String email;
	private String mobile;
	private String username;
	private String deliveryCountThisMonth;
	private String message;

	public String getMemberId() {
		return memberId;
	}

	public String getMoneyBalance() {
		return moneyBalance;
	}

	public String getVanteges() {
		return vanteges;
	}

	public String getEmail() {
		return email;
	}

	public String getMobile() {
		return mobile;
	}

	public String getUsername() {
		return username;
	}

	public String getDeliveryCountThisMonth() {
		return deliveryCountThisMonth;
	}

	public String getMessage() {
		return message;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public void setMoneyBalance(String moneyBalance) {
		this.moneyBalance = moneyBalance;
	}

	public void setVanteges(String vanteges) {
		this.vanteges = vanteges;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setDeliveryCountThisMonth(String deliveryCountThisMonth) {
		this.deliveryCountThisMonth = deliveryCountThisMonth;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
