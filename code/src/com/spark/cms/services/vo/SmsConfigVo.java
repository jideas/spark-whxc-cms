/**
 * 
 */
package com.spark.cms.services.vo;

import java.io.Serializable;

/**
 * @author Jideas
 *
 */
@SuppressWarnings("serial")
public class SmsConfigVo implements Serializable {
	private String recid;
	private Long recver;
	private boolean activing;
	private String balanceUrl;
	private String submitUrl;
	private String userNameKey;
	private String passwordKey;
	private String phoneNumberKey;
	private String msgContentKey;
	public String compAccount;
	public String compAccountKey;
	private String userName;
	private String password;
	private String secretKey;

	public String getRecid() {
		return recid;
	}

	public Long getRecver() {
		return recver;
	}

	public boolean isActiving() {
		return activing;
	}

	public String getBalanceUrl() {
		return balanceUrl;
	}

	public String getSubmitUrl() {
		return submitUrl;
	}

	public String getCompAccount() {
		return compAccount;
	}

	public String getCompAccountKey() {
		return compAccountKey;
	}

	public void setCompAccount(String compAccount) {
		this.compAccount = compAccount;
	}

	public void setCompAccountKey(String compAccountKey) {
		this.compAccountKey = compAccountKey;
	}

	public String getUserNameKey() {
		return userNameKey;
	}

	public String getPasswordKey() {
		return passwordKey;
	}

	public String getPhoneNumberKey() {
		return phoneNumberKey;
	}

	public String getMsgContentKey() {
		return msgContentKey;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setRecid(String recid) {
		this.recid = recid;
	}

	public void setRecver(Long recver) {
		this.recver = recver;
	}

	public void setActiving(boolean activing) {
		this.activing = activing;
	}

	public void setBalanceUrl(String balanceUrl) {
		this.balanceUrl = balanceUrl;
	}

	public void setSubmitUrl(String submitUrl) {
		this.submitUrl = submitUrl;
	}

	public void setUserNameKey(String userNameKey) {
		this.userNameKey = userNameKey;
	}

	public void setPasswordKey(String passwordKey) {
		this.passwordKey = passwordKey;
	}

	public void setPhoneNumberKey(String phoneNumberKey) {
		this.phoneNumberKey = phoneNumberKey;
	}

	public void setMsgContentKey(String msgContentKey) {
		this.msgContentKey = msgContentKey;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}
}
