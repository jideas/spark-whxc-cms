/**
 * 
 */
package com.spark.front.form.member;

import java.util.List;

import com.spark.base.common.system.dic.DicItem;

/**
 * @author Jideas
 */
public class InfoForm {
	String username, email, mobile, realName, sex, province, city, town, address, telephone, identity, maritalstatus,
			birthday01, birthday02, birthday03, livingconditions;

	private List<DicItem> identityList;
	private List<DicItem> maritalstatusList;
	private List<DicItem> livingconditionsList;

	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}

	public String getMobile() {
		return mobile;
	}

	public String getRealName() {
		return realName;
	}

	public String getSex() {
		return sex;
	} 

	public String getCity() {
		return city;
	}

	public String getTown() {
		return town;
	}

	public String getAddress() {
		return address;
	}

	public String getTelephone() {
		return telephone;
	}

	public String getIdentity() {
		return identity;
	}

	public String getMaritalstatus() {
		return maritalstatus;
	}

	public String getLivingconditions() {
		return livingconditions;
	}

	public String getBirthday01() {
		return birthday01;
	}

	public String getBirthday02() {
		return birthday02;
	}

	public String getBirthday03() {
		return birthday03;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public void setBirthday01(String birthday01) {
		this.birthday01 = birthday01;
	}

	public void setBirthday02(String birthday02) {
		this.birthday02 = birthday02;
	}

	public void setBirthday03(String birthday03) {
		this.birthday03 = birthday03;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public void setSex(String sex) {
		this.sex = sex;
	} 

	public void setCity(String city) {
		this.city = city;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public void setMaritalstatus(String maritalstatus) {
		this.maritalstatus = maritalstatus;
	}

	public void setLivingconditions(String livingconditions) {
		this.livingconditions = livingconditions;
	}

	public List<DicItem> getIdentityList() {
		return identityList;
	}

	public List<DicItem> getMaritalstatusList() {
		return maritalstatusList;
	}

	public List<DicItem> getLivingconditionsList() {
		return livingconditionsList;
	}

	public void setIdentityList(List<DicItem> identityList) {
		this.identityList = identityList;
	}

	public void setMaritalstatusList(List<DicItem> maritalstatusList) {
		this.maritalstatusList = maritalstatusList;
	}

	public void setLivingconditionsList(List<DicItem> livingconditionsList) {
		this.livingconditionsList = livingconditionsList;
	}

}
