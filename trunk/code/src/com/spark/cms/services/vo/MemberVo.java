package com.spark.cms.services.vo;


/**
 * CmsMember entity.
 * 
 * @author MyEclipse Persistence Tools
 */ 
@SuppressWarnings("serial")
public class MemberVo implements java.io.Serializable {

	// Fields

	private String recid;
	private Long recver;
	private String username;
	private String password;
	private String membername;
	private String mobile;
	private String email;
	private String status;
	private String statusStr;
	private String invitationcode;
	private boolean isoffical;
	private String code;
	private boolean checkedMobile;

	// Constructors

	public String getStatusStr() {
		return statusStr;
	}

	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}

	/** default constructor */
	public MemberVo() {
	}

	/** minimal constructor */
	public MemberVo(String recid) {
		this.recid = recid;
	}

	/** full constructor */
	public MemberVo(String recid, Long recver, String username, String password, String membername,
			String mobile, String email, String status, String invitationcode, boolean isoffical) {
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
	}

	// Property accessors

	public String getRecid() {
		return this.recid;
	}

	public void setRecid(String recid) {
		this.recid = recid;
	}

	public Long getRecver() {
		return this.recver;
	}

	public void setRecver(Long recver) {
		this.recver = recver;
	}

	public boolean isCheckedMobile() {
		return checkedMobile;
	}

	public void setCheckedMobile(boolean checkedMobile) {
		this.checkedMobile = checkedMobile;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMembername() {
		return this.membername;
	}

	public void setMembername(String membername) {
		this.membername = membername;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}