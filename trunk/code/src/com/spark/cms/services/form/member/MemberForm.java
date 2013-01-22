package com.spark.cms.services.form.member;


/**
 * CmsMember entity.
 * 
 * @author MyEclipse Persistence Tools
 */ 
@SuppressWarnings("serial")
public class MemberForm implements java.io.Serializable {

	// Fields

	private String recid;
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
	private double moneybalance;
	private double vantages;
	private String telephone;
	private long birthday;
	private String sex;
	private String address;

	// Constructors

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public long getBirthday() {
		return birthday;
	}

	public void setBirthday(long birthday) {
		this.birthday = birthday;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public double getMoneybalance() {
		return moneybalance;
	}

	public void setMoneybalance(double moneybalance) {
		this.moneybalance = moneybalance;
	}

	public double getVantages() {
		return vantages;
	}

	public void setVantages(double vantages) {
		this.vantages = vantages;
	}

	public String getStatusStr() {
		return statusStr;
	}

	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}

	/** default constructor */
	public MemberForm() {
	}

	/** minimal constructor */
	public MemberForm(String recid) {
		this.recid = recid;
	}

	/** full constructor */
	public MemberForm(String recid, String username, String password, String membername,
			String mobile, String email, String status, String invitationcode, boolean isoffical) {
		this.recid = recid;
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