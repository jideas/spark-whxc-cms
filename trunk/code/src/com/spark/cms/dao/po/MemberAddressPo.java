package com.spark.cms.dao.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CmsMemberAddress entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "cms_member_address")
@SuppressWarnings("serial")
public class MemberAddressPo implements java.io.Serializable {

	// Fields

	private byte[] recid;
	private Long recver;
	private byte[] memberid;
	private String consignee;
	private String province;
	private String city;
	private String town;
	private byte[] stationid;
	private String address;
	private String postcode;
	private String mobile;
	private String telephone;
	private boolean isdefault;

	// Constructors

	/** default constructor */
	public MemberAddressPo() {
	}

	/** minimal constructor */
	public MemberAddressPo(byte[] recid) {
		this.recid = recid;
	}

	/** full constructor */
	public MemberAddressPo(byte[] recid, Long recver, byte[] memberid, String consignee, String province,
			String city, String town, byte[] stationid, String address, String postcode, String mobile,
			String telephone, boolean isdefault) {
		this.recid = recid;
		this.recver = recver;
		this.memberid = memberid;
		this.consignee = consignee;
		this.province = province;
		this.city = city;
		this.town = town;
		this.stationid = stationid;
		this.address = address;
		this.postcode = postcode;
		this.mobile = mobile;
		this.telephone = telephone;
		this.isdefault = isdefault;
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
	public byte[] getMemberid() {
		return this.memberid;
	}

	public void setMemberid(byte[] memberid) {
		this.memberid = memberid;
	}

	@Column(name = "CONSIGNEE", length = 20)
	public String getConsignee() {
		return this.consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
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

	@Column(name = "STATIONID")
	public byte[] getStationid() {
		return this.stationid;
	}

	public void setStationid(byte[] stationid) {
		this.stationid = stationid;
	}

	@Column(name = "ADDRESS", length = 200)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "POSTCODE", length = 6)
	public String getPostcode() {
		return this.postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
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
	@Column(name = "ISDEFAULT")
	public boolean isIsdefault() {
		return isdefault;
	}

	public void setIsdefault(boolean isdefault) {
		this.isdefault = isdefault;
	}

}