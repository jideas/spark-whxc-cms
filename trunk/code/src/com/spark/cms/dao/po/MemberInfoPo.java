package com.spark.cms.dao.po;
 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * CmsMemberInfo entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "cms_member_info")
@SuppressWarnings("serial")
public class MemberInfoPo implements java.io.Serializable {

	// Fields

	private byte[] recid;
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
	public MemberInfoPo() {
	}

	/** minimal constructor */
	public MemberInfoPo(byte[] recid) {
		this.recid = recid;
	}

	/** full constructor */
	public MemberInfoPo(byte[] recid, Long recver, String memberid, String membername, String membertype,
			String mobile, String telephone, long birthday, String sex, String province, String city,
			String town, String address, String identity, String maritalstatus, String livingconditions) {
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
	@Id
	@Column(name = "RECID", unique = true, nullable = false)
	public byte[] getRecid() {
		return this.recid;
	}

	public void setRecid(byte[] recid) {
		this.recid = recid;
	}

	@Column(name = "RECVER")
	public Long getRecver() {
		return this.recver;
	}

	public void setRecver(Long recver) {
		this.recver = recver;
	}

	@Column(name = "MEMBERID")
	public String getMemberid() {
		return this.memberid;
	}

	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}

	@Column(name = "MEMBERNAME", length = 30)
	public String getMembername() {
		return this.membername;
	}

	public void setMembername(String membername) {
		this.membername = membername;
	}

	@Column(name = "MEMBERTYPE", length = 10)
	public String getMembertype() {
		return this.membertype;
	}

	public void setMembertype(String membertype) {
		this.membertype = membertype;
	}

	@Column(name = "MOBILE", length = 15)
	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "TELEPHONE", length = 20)
	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
 
	@Column(name = "BIRTHDAY", length = 19)
	public long getBirthday() {
		return this.birthday;
	}

	public void setBirthday(long birthday) {
		this.birthday = birthday;
	}

	@Column(name = "SEX")
	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name = "PROVINCE", length = 10)
	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	@Column(name = "CITY", length = 10)
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "TOWN", length = 10)
	public String getTown() {
		return this.town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	@Column(name = "ADDRESS", length = 10)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "IDENTITY", length = 10)
	public String getIdentity() {
		return this.identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	@Column(name = "MARITALSTATUS", length = 10)
	public String getMaritalstatus() {
		return this.maritalstatus;
	}

	public void setMaritalstatus(String maritalstatus) {
		this.maritalstatus = maritalstatus;
	}

	@Column(name = "LIVINGCONDITIONS", length = 10)
	public String getLivingconditions() {
		return this.livingconditions;
	}

	public void setLivingconditions(String livingconditions) {
		this.livingconditions = livingconditions;
	}

}