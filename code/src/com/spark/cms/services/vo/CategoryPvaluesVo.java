package com.spark.cms.services.vo;


/**
 * CmsCategoryPvalues entity.
 * 
 * @author MyEclipse Persistence Tools
 */ 
@SuppressWarnings("serial")
public class CategoryPvaluesVo implements java.io.Serializable {

	// Fields

	private String recid;
	private Long recver;
	private String propertyid;
	private String categoryid;
	private String pvalue;
	private Integer ordinal;

	// Constructors

	/** default constructor */
	public CategoryPvaluesVo() {
	}

	/** minimal constructor */
	public CategoryPvaluesVo(String recid) {
		this.recid = recid;
	}

	/** full constructor */
	public CategoryPvaluesVo(String recid, Long recver, String propertyid, String categoryid, String pvalue,
			Integer ordinal) {
		this.recid = recid;
		this.recver = recver;
		this.propertyid = propertyid;
		this.categoryid = categoryid;
		this.pvalue = pvalue;
		this.ordinal = ordinal;
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

	public String getPropertyid() {
		return this.propertyid;
	}

	public void setPropertyid(String propertyid) {
		this.propertyid = propertyid;
	}

	public String getCategoryid() {
		return this.categoryid;
	}

	public void setCategoryid(String categoryid) {
		this.categoryid = categoryid;
	}

	public String getPvalue() {
		return this.pvalue;
	}

	public void setPvalue(String pvalue) {
		this.pvalue = pvalue;
	}

	public Integer getOrdinal() {
		return this.ordinal;
	}

	public void setOrdinal(Integer ordinal) {
		this.ordinal = ordinal;
	}

}