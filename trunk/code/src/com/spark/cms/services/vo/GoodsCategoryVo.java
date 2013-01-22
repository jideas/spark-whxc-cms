package com.spark.cms.services.vo;

import java.util.Date;

/**
 * CmsGoodsCategory entity.
 * 
 * @author MyEclipse Persistence Tools
 */ 
@SuppressWarnings("serial")
public class GoodsCategoryVo implements java.io.Serializable {

	// Fields

	private String recid;
	private Long recver;
	private String categoryno;
	private String categoryname;
	private String parentid;
	private String path;
	private Integer levelno;  // 0, 1, 2
	private Date createdate;
	private Date lastmodifydate;
	private String lastmodifyperson;
	private String creatorid;
	private String creator;

	// Constructors

	/** default constructor */
	public GoodsCategoryVo() {
	}

	/** minimal constructor */
	public GoodsCategoryVo(String recid) {
		this.recid = recid;
	}

	/** full constructor */
	public GoodsCategoryVo(String recid, Long recver, String categoryno, String categoryname,
			String parentid, String path, Integer levelno, Date createdate, Date lastmodifydate,
			String lastmodifyperson, String creatorid, String creator) {
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

	public String getCategoryno() {
		return this.categoryno;
	}

	public void setCategoryno(String categoryno) {
		this.categoryno = categoryno;
	}

	public String getCategoryname() {
		return this.categoryname;
	}

	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}

	public String getParentid() {
		return this.parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Integer getLevelno() {
		return this.levelno;
	}

	public void setLevelno(Integer levelno) {
		this.levelno = levelno;
	}

	public Date getCreatedate() {
		return this.createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public Date getLastmodifydate() {
		return this.lastmodifydate;
	}

	public void setLastmodifydate(Date lastmodifydate) {
		this.lastmodifydate = lastmodifydate;
	}

	public String getLastmodifyperson() {
		return this.lastmodifyperson;
	}

	public void setLastmodifyperson(String lastmodifyperson) {
		this.lastmodifyperson = lastmodifyperson;
	}

	public String getCreatorid() {
		return this.creatorid;
	}

	public void setCreatorid(String creatorid) {
		this.creatorid = creatorid;
	}

	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

}