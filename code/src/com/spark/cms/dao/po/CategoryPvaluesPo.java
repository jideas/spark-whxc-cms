package com.spark.cms.dao.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CmsCategoryPvalues entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "cms_category_pvalues")
@SuppressWarnings("serial")
public class CategoryPvaluesPo implements java.io.Serializable {

	// Fields

	private byte[] recid;
	private Long recver;
	private byte[] propertyid;
	private byte[] categoryid;
	private String pvalue;
	private Integer ordinal;

	// Constructors

	/** default constructor */
	public CategoryPvaluesPo() {
	}

	/** minimal constructor */
	public CategoryPvaluesPo(byte[] recid) {
		this.recid = recid;
	}

	/** full constructor */
	public CategoryPvaluesPo(byte[] recid, Long recver, byte[] propertyid, byte[] categoryid, String pvalue,
			Integer ordinal) {
		this.recid = recid;
		this.recver = recver;
		this.propertyid = propertyid;
		this.categoryid = categoryid;
		this.pvalue = pvalue;
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

	@Column(name = "PROPERTYID")
	public byte[] getPropertyid() {
		return this.propertyid;
	}

	public void setPropertyid(byte[] propertyid) {
		this.propertyid = propertyid;
	}

	@Column(name = "CATEGORYID")
	public byte[] getCategoryid() {
		return this.categoryid;
	}

	public void setCategoryid(byte[] categoryid) {
		this.categoryid = categoryid;
	}

	@Column(name = "PVALUE")
	public String getPvalue() {
		return this.pvalue;
	}

	public void setPvalue(String pvalue) {
		this.pvalue = pvalue;
	}

	@Column(name = "ORDINAL")
	public Integer getOrdinal() {
		return this.ordinal;
	}

	public void setOrdinal(Integer ordinal) {
		this.ordinal = ordinal;
	}

}