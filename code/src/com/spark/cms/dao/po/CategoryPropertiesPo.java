package com.spark.cms.dao.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CmsCategoryProperties entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "cms_category_properties")
@SuppressWarnings("serial")
public class CategoryPropertiesPo implements java.io.Serializable {

	// Fields

	private byte[] recid;
	private Long recver;
	private byte[] categoryid;
	private String propertiyname;
	private Integer ordinal;

	// Constructors

	/** default constructor */
	public CategoryPropertiesPo() {
	}

	/** minimal constructor */
	public CategoryPropertiesPo(byte[] recid) {
		this.recid = recid;
	}

	/** full constructor */
	public CategoryPropertiesPo(byte[] recid, Long recver, byte[] categoryid, String propertiyname,
			Integer ordinal) {
		this.recid = recid;
		this.recver = recver;
		this.categoryid = categoryid;
		this.propertiyname = propertiyname;
		this.ordinal = ordinal;
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

	@Column(name = "CATEGORYID")
	public byte[] getCategoryid() {
		return this.categoryid;
	}

	public void setCategoryid(byte[] categoryid) {
		this.categoryid = categoryid;
	}

	@Column(name = "PROPERTIYNAME")
	public String getPropertiyname() {
		return this.propertiyname;
	}

	public void setPropertiyname(String propertiyname) {
		this.propertiyname = propertiyname;
	}

	@Column(name = "ORDINAL")
	public Integer getOrdinal() {
		return this.ordinal;
	}

	public void setOrdinal(Integer ordinal) {
		this.ordinal = ordinal;
	}

}