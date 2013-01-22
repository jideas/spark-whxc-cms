package com.spark.cms.dao.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CmsMember entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "cms_member")
@SuppressWarnings("serial")
public class MemberPo implements java.io.Serializable {

	// Fields

	private byte[] recid;
	private Long recver;
	private String username;
	private String password;
	private String membername;
	private String mobile;
	private String email;
	private String status;
	private String invitationcode;
	private boolean isoffical;
	private String code;
	private long lastLoginDate;
	private boolean checkedMobile;

	// Constructors

	/** default constructor */
	public MemberPo() {
	}

	/** minimal constructor */
	public MemberPo(byte[] recid) {
		this.recid = recid;
	}

	/** full constructor */
	public MemberPo(byte[] recid, Long recver, String username,
			String password, String membername, String mobile, String email,
			String status, String invitationcode, boolean isoffical,
			String code, long lastLoginDate) {
		this.recid = recid;
		this.recver = recver;
		this.username = username;
		this.password = password;
		this.membername = membername;
		this.mobile = mobile;
		this.email = email;
		this.status = status;
		this.invitationcode = invitationcode;
		this.isoffical = isoffical;
		this.code = code;
		this.lastLoginDate = lastLoginDate;
	}

	// Property accessors
	@Id
	@Column(name = "RECID", unique = true, nullable = false)
	public byte[] getRecid() {
		return this.recid;
	}

	public void setRecid(byte[] bs) {
		this.recid = bs;
	}

	@Column(name = "RECVER")
	public Long getRecver() {
		return this.recver;
	}

	public void setRecver(Long recver) {
		this.recver = recver;
	}

	@Column(name = "USERNAME", length = 30)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "PASSWORD", length = 100)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "MEMBERNAME", length = 30)
	public String getMembername() {
		return this.membername;
	}

	public void setMembername(String membername) {
		this.membername = membername;
	}

	@Column(name = "MOBILE", length = 15)
	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "EMAIL", length = 30)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "STATUS", length = 2)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "INVITATIONCODE", length = 30)
	public String getInvitationcode() {
		return this.invitationcode;
	}

	public void setInvitationcode(String invitationcode) {
		this.invitationcode = invitationcode;
	}

	 
	public boolean isIsoffical() {
		return isoffical;
	}

	public void setIsoffical(boolean isoffical) {
		this.isoffical = isoffical;
	}

	@Column(name = "CODE")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "LASTLOGINDATE")
	public long getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(long lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}
	@Column(name = "checkedMobile")
	public boolean isCheckedMobile() {
		return checkedMobile;
	}

	public void setCheckedMobile(boolean checkedMobile) {
		this.checkedMobile = checkedMobile;
	}

}