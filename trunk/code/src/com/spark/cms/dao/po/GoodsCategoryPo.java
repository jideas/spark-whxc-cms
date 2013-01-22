package com.spark.cms.dao.po;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * CmsGoodsCategory entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "cms_goods_category")
@SuppressWarnings("serial")
public class GoodsCategoryPo implements java.io.Serializable {

	// Fields

	private byte[] recid;
	private Long recver;
	private String categoryno;
	private String categoryname;
	private byte[] parentid;
	private String path;
	private Integer levelno;
	private Date createdate;
	private Date lastmodifydate;
	private String lastmodifyperson;
	private byte[] creatorid;
	private String creator;

	// Constructors

	/** default constructor */
	public GoodsCategoryPo() {
	}

	/** minimal constructor */
	public GoodsCategoryPo(byte[] recid) {
		this.recid = recid;
	}

	/** full constructor */
	public GoodsCategoryPo(byte[] recid, Long recver, String categoryno, String categoryname,
			byte[] parentid, String path, Integer levelno, Date createdate, Date lastmodifydate,
			String lastmodifyperson, byte[] creatorid, String creator) {
		this.recid = recid;
		this.recver = recver;
		this.categoryno = categoryno;
		this.categoryname = categoryname;
		this.parentid = parentid;
		this.path = path;
		this.levelno = levelno;
		this.createdate = createdate;
		this.lastmodifydate = lastmodifydate;
		this.lastmodifyperson = lastmodifyperson;
		this.creatorid = creatorid;
		this.creator = creator;
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

	@Column(name = "CATEGORYNO", length = 25)
	public String getCategoryno() {
		return this.categoryno;
	}

	public void setCategoryno(String categoryno) {
		this.categoryno = categoryno;
	}

	@Column(name = "CATEGORYNAME", length = 100)
	public String getCategoryname() {
		return this.categoryname;
	}

	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}

	@Column(name = "PARENTID")
	public byte[] getParentid() {
		return this.parentid;
	}

	public void setParentid(byte[] parentid) {
		this.parentid = parentid;
	}

	@Column(name = "PATH")
	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Column(name = "LEVELNO")
	public Integer getLevelno() {
		return this.levelno;
	}

	public void setLevelno(Integer levelno) {
		this.levelno = levelno;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CREATEDATE", length = 19)
	public Date getCreatedate() {
		return this.createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "LASTMODIFYDATE", length = 19)
	public Date getLastmodifydate() {
		return this.lastmodifydate;
	}

	public void setLastmodifydate(Date lastmodifydate) {
		this.lastmodifydate = lastmodifydate;
	}

	@Column(name = "LASTMODIFYPERSON", length = 30)
	public String getLastmodifyperson() {
		return this.lastmodifyperson;
	}

	public void setLastmodifyperson(String lastmodifyperson) {
		this.lastmodifyperson = lastmodifyperson;
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

}