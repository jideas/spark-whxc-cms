/**
 * 
 */
package com.spark.cms.dao.po;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Jideas
 * 
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "PSI_BASE_MSGCONFIG")
public class SmsConfigPo implements Serializable {
	private byte[] recid;
	private Long recver;
	private boolean activing;
	private String balanceUrl;
	private String submitUrl;
	private String userNameKey;
	private String passwordKey;
	private String phoneNumberKey;
	private String msgContentKey;
	private String userName;
	private String password;
	private String secretKey;
	public String compAccount;
	public String compAccountKey;

	@Id
	@Column(name = "RECID", unique = true, nullable = false)
	public byte[] getRecid() {
		return recid;
	}

	@Column(name = "recver")
	public Long getRecver() {
		return recver;
	}

	@Column(name = "activing")
	public boolean isActiving() {
		return activing;
	}

	@Column(name = "balanceUrl")
	public String getBalanceUrl() {
		return balanceUrl;
	}

	@Column(name = "submitUrl")
	public String getSubmitUrl() {
		return submitUrl;
	}

	@Column(name = "userNameKey")
	public String getUserNameKey() {
		return userNameKey;
	}

	@Column(name = "passwordKey")
	public String getPasswordKey() {
		return passwordKey;
	}

	@Column(name = "phoneNumberKey")
	public String getPhoneNumberKey() {
		return phoneNumberKey;
	}

	@Column(name = "msgContentKey")
	public String getMsgContentKey() {
		return msgContentKey;
	}

	@Column(name = "userName")
	public String getUserName() {
		return userName;
	}

	@Column(name = "password")
	public String getPassword() {
		return password;
	}

	@Column(name = "secretKey")
	public String getSecretKey() {
		return secretKey;
	}

	@Column(name = "companyAccount")
	public String getCompAccount() {
		return compAccount;
	}

	@Column(name = "companyAccountKey")
	public String getCompAccountKey() {
		return compAccountKey;
	}

	public void setCompAccount(String compAccount) {
		this.compAccount = compAccount;
	}

	public void setCompAccountKey(String compAccountKey) {
		this.compAccountKey = compAccountKey;
	}

	public void setRecid(byte[] recid) {
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
