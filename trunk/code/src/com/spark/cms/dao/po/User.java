package com.spark.cms.dao.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CmsUser entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "cms_user")
public class User implements java.io.Serializable {

	// Fields

	private String id;
	private String name;
	private String account;
	private String mobile;
	private String tele;
	private String email;
	private String pwd;
	private String isEnabled;

	// Constructors

	/** default constructor */
	public User() {
	}

	/** minimal constructor */
	public User(String id) {
		this.id = id;
	}

	/** full constructor */
	public User(String id, String name, String account, String mobile,
			String tele, String email, String pwd, String isEnabled) {
		this.id = id;
		this.name = name;
		this.account = account;
		this.mobile = mobile;
		this.tele = tele;
		this.email = email;
		this.pwd = pwd;
		this.isEnabled = isEnabled;
	}

	// Property accessors
	@Id
	@Column(name = "id", unique = true, nullable = false, length = 32)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "name", length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "account", length = 25)
	public String getAccount() {
		return this.account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	@Column(name = "mobile", length = 32)
	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "tele", length = 20)
	public String getTele() {
		return this.tele;
	}

	public void setTele(String tele) {
		this.tele = tele;
	}

	@Column(name = "email", length = 50)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "pwd", length = 100)
	public String getPwd() {
		return this.pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	@Column(name = "isEnabled", length = 1)
	public String getIsEnabled() {
		return this.isEnabled;
	}

	public void setIsEnabled(String isEnabled) {
		this.isEnabled = isEnabled;
	}

}