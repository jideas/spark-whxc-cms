package com.spark.cms.services.vo;


/**
 * CmsMemberAddress entity.
 * 
 * @author MyEclipse Persistence Tools
 */ 
@SuppressWarnings("serial")
public class MemberAddressVo implements java.io.Serializable {

	// Fields

	private String recid; 
	private Long recver;
	private String memberid;
	private String consignee;
	private String province;
	private String city;
	private String town;
	private String stationid;
	private String address;
	private String postcode;
	private String mobile;
	private String telephone;
	private boolean isdefault;

	private String stationName;
	private String provinceTitle;
	private String cityTitle;
	private String townTitle;
	// Constructors

	/** default constructor */
	public MemberAddressVo() {
	}

	/** minimal constructor */
	public MemberAddressVo(String recid) {
		this.recid = recid;
	}

	/** full constructor */
	public MemberAddressVo(String recid,Long recver, String memberid, String consignee, String province,
			String city, String town, String stationid, String address, String postcode, String mobile,
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

	public String getRecid() {
		return this.recid;
	}

	public Long getRecver() {
		return recver;
	}

	public void setRecver(Long recver) {
		this.recver = recver;
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

	public String getConsignee() {
		return this.consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
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

	public String getStationid() {
		return this.stationid;
	}

	public void setStationid(String stationid) {
		this.stationid = stationid;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostcode() {
		return this.postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
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

	public boolean isIsdefault() {
		return isdefault;
	}

	public void setIsdefault(boolean isdefault) {
		this.isdefault = isdefault;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public String getProvinceTitle() {
		return provinceTitle;
	}

	public void setProvinceTitle(String provinceTitle) {
		this.provinceTitle = provinceTitle;
	}

	public String getCityTitle() {
		return cityTitle;
	}

	public void setCityTitle(String cityTitle) {
		this.cityTitle = cityTitle;
	}

	public String getTownTitle() {
		return townTitle;
	}

	public void setTownTitle(String townTitle) {
		this.townTitle = townTitle;
	}
	
}