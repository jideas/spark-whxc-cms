/**
 * 
 */
package com.spark.cms.services.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Jideas
 * 
 */
@SuppressWarnings("serial")
public class StationVo implements Serializable {

	// Fields

	private String recid;
	private Long recver;
	private String stationno;
	private String stationname;
	private String namepy;
	private String shortname;
	private String telephone;
	private String fax;
	private String remark;
	private String town;
	private String province;
	private String city;
	private String address;
	private String managerid;
	private String deptid;
	private String manager;
	private String managerpersonid;
	private String managerphone;
	private String manageremail;
	private Date createdate;
	private String creatorid;
	private String creator;
	private boolean isstop;

	private String provinceTitle;
	private String cityTitle;
	private String townTitle;
	
	public String getTownTitle() {
		return townTitle;
	}

	public void setTownTitle(String townTitle) {
		this.townTitle = townTitle;
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

	public String getRecid() {
		return recid;
	}

	public Long getRecver() {
		return recver;
	}

	public String getStationno() {
		return stationno;
	}

	public String getStationname() {
		return stationname;
	}

	public String getNamepy() {
		return namepy;
	}

	public String getShortname() {
		return shortname;
	}

	public String getTelephone() {
		return telephone;
	}

	public String getFax() {
		return fax;
	}

	public String getRemark() {
		return remark;
	}

	public String getTown() {
		return town;
	}

	public String getProvince() {
		return province;
	}

	public String getCity() {
		return city;
	}

	public String getAddress() {
		return address;
	}

	public String getManagerid() {
		return managerid;
	}

	public String getDeptid() {
		return deptid;
	}

	public String getManager() {
		return manager;
	}

	public String getManagerpersonid() {
		return managerpersonid;
	}

	public String getManagerphone() {
		return managerphone;
	}

	public String getManageremail() {
		return manageremail;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public String getCreatorid() {
		return creatorid;
	}

	public String getCreator() {
		return creator;
	}

	public boolean isIsstop() {
		return isstop;
	}

	public void setRecid(String recid) {
		this.recid = recid;
	}

	public void setRecver(Long recver) {
		this.recver = recver;
	}

	public void setStationno(String stationno) {
		this.stationno = stationno;
	}

	public void setStationname(String stationname) {
		this.stationname = stationname;
	}

	public void setNamepy(String namepy) {
		this.namepy = namepy;
	}

	public void setShortname(String shortname) {
		this.shortname = shortname;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setManagerid(String managerid) {
		this.managerid = managerid;
	}

	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public void setManagerpersonid(String managerpersonid) {
		this.managerpersonid = managerpersonid;
	}

	public void setManagerphone(String managerphone) {
		this.managerphone = managerphone;
	}

	public void setManageremail(String manageremail) {
		this.manageremail = manageremail;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public void setCreatorid(String creatorid) {
		this.creatorid = creatorid;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public void setIsstop(boolean isstop) {
		this.isstop = isstop;
	}
}
