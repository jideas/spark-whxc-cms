package com.spark.cms.services.vo;

/**
 * CmsMemberInfo entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
public class MemberInfoVo implements java.io.Serializable {

	// Fields

	private String recid;
	private Long recver;
	private String memberid;
	private String membername;
	private String membertype;
	private String mobile;
	private String telephone;
	private long birthday;
	private String sex;
	private String province;
	private String city;
	private String town;
	private String address;
	private String identity;
	private String maritalstatus;
	private String livingconditions;

	// Constructors

	/** default constructor */
	public MemberInfoVo() {
	}

	/** minimal constructor */
	public MemberInfoVo(String recid) {
		this.recid = recid;
	}

	/** full constructor */
	public MemberInfoVo(String recid, Long recver, String memberid, String membername, String membertype,
			String mobile, String telephone, long birthday, String sex, String province, String city, String town,
			String address, String identity, String maritalstatus, String livingconditions) {
		this.recid = recid;
		this.recver = recver;
		this.memberid = memberid;
		this.membername = membername;
		this.membertype = membertype;
		this.mobile = mobile;
		this.telephone = telephone;
		this.birthday = birthday;
		this.sex = sex;
		this.province = province;
		this.city = city;
		this.town = town;
		this.address = address;
		this.identity = identity;
		this.maritalstatus = maritalstatus;
		this.livingconditions = livingconditions;
	}

	// Property accessors

	public Long getRecver() {
		return recver;
	}

	public void setRecver(Long recver) {
		this.recver = recver;
	}

	public String getRecid() {
		return this.recid;
	}

	public void setRecid(String recid) {
		this.recid = recid;
	}

	public String getMemberid() {
		return this.memberid;
	}

	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}

	public String getMembername() {
		return this.membername;
	}

	public void setMembername(String membername) {
		this.membername = membername;
	}

	public String getMembertype() {
		return this.membertype;
	}

	public void setMembertype(String membertype) {
		this.membertype = membertype;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public long getBirthday() {
		return this.birthday;
	}

	public void setBirthday(long birthday) {
		this.birthday = birthday;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getTown() {
		return this.town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getIdentity() {
		return this.identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public String getMaritalstatus() {
		return this.maritalstatus;
	}

	public void setMaritalstatus(String maritalstatus) {
		this.maritalstatus = maritalstatus;
	}

	public String getLivingconditions() {
		return this.livingconditions;
	}

	public void setLivingconditions(String livingconditions) {
		this.livingconditions = livingconditions;
	}

}