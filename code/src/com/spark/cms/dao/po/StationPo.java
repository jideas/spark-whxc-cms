package com.spark.cms.dao.po;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * StationPo entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "psi_base_station")
public class StationPo implements java.io.Serializable {

	// Fields

	private byte[] recid;
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
	private byte[] managerid;
	private byte[] deptid;
	private String manager;
	private String managerpersonid;
	private String managerphone;
	private String manageremail;
	private Date createdate;
	private byte[] creatorid;
	private String creator;
	private boolean isstop;

	// Constructors

	/** default constructor */
	public StationPo() {
	}

	/** minimal constructor */
	public StationPo(byte[] recid, boolean isstop) {
		this.recid = recid;
		this.isstop = isstop;
	}

	/** full constructor */
	public StationPo(byte[] recid, Long recver, String stationno, String stationname, String namepy, String shortname,
			String telephone, String fax, String remark, String town, String province, String city, String address,
			byte[] managerid, byte[] deptid, String manager, String managerpersonid, String managerphone, String manageremail,
			Date createdate, byte[] creatorid, String creator, boolean isstop) {
		this.recid = recid;
		this.recver = recver;
		this.stationno = stationno;
		this.stationname = stationname;
		this.namepy = namepy;
		this.shortname = shortname;
		this.telephone = telephone;
		this.fax = fax;
		this.remark = remark;
		this.town = town;
		this.province = province;
		this.city = city;
		this.address = address;
		this.managerid = managerid;
		this.deptid = deptid;
		this.manager = manager;
		this.managerpersonid = managerpersonid;
		this.managerphone = managerphone;
		this.manageremail = manageremail;
		this.createdate = createdate;
		this.creatorid = creatorid;
		this.creator = creator;
		this.isstop = isstop;
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

	@Column(name = "STATIONNO", length = 30)
	public String getStationno() {
		return this.stationno;
	}

	public void setStationno(String stationno) {
		this.stationno = stationno;
	}

	@Column(name = "STATIONNAME", length = 50)
	public String getStationname() {
		return this.stationname;
	}

	public void setStationname(String stationname) {
		this.stationname = stationname;
	}

	@Column(name = "NAMEPY", length = 50)
	public String getNamepy() {
		return this.namepy;
	}

	public void setNamepy(String namepy) {
		this.namepy = namepy;
	}

	@Column(name = "SHORTNAME", length = 20)
	public String getShortname() {
		return this.shortname;
	}

	public void setShortname(String shortname) {
		this.shortname = shortname;
	}

	@Column(name = "TELEPHONE", length = 20)
	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@Column(name = "FAX", length = 20)
	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	@Column(name = "REMARK", length = 1000)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "TOWN", length = 20)
	public String getTown() {
		return this.town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	@Column(name = "PROVINCE", length = 20)
	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	@Column(name = "CITY", length = 20)
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "ADDRESS", length = 200)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "MANAGERID")
	public byte[] getManagerid() {
		return this.managerid;
	}

	public void setManagerid(byte[] managerid) {
		this.managerid = managerid;
	}

	@Column(name = "DEPTID")
	public byte[] getDeptid() {
		return this.deptid;
	}

	public void setDeptid(byte[] deptid) {
		this.deptid = deptid;
	}

	@Column(name = "MANAGER", length = 20)
	public String getManager() {
		return this.manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	@Column(name = "MANAGERPERSONID", length = 30)
	public String getManagerpersonid() {
		return this.managerpersonid;
	}

	public void setManagerpersonid(String managerpersonid) {
		this.managerpersonid = managerpersonid;
	}

	@Column(name = "MANAGERPHONE", length = 20)
	public String getManagerphone() {
		return this.managerphone;
	}

	public void setManagerphone(String managerphone) {
		this.managerphone = managerphone;
	}

	@Column(name = "MANAGEREMAIL", length = 50)
	public String getManageremail() {
		return this.manageremail;
	}

	public void setManageremail(String manageremail) {
		this.manageremail = manageremail;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CREATEDATE", length = 19)
	public Date getCreatedate() {
		return this.createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	@Column(name = "CREATORID")
	public byte[] getCreatorid() {
		return this.creatorid;
	}

	public void setCreatorid(byte[] creatorid) {
		this.creatorid = creatorid;
	}

	@Column(name = "CREATOR", length = 30)
	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	@Column(name = "ISSTOP", nullable = false)
	public boolean isIsstop() {
		return this.isstop;
	}

	public void setIsstop(boolean isstop) {
		this.isstop = isstop;
	}

}