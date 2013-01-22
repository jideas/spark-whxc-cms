package com.spark.cms.services.vo;


/**
 * CmsCategoryProperties entity.
 * 
 * @author MyEclipse Persistence Tools
 */ 
@SuppressWarnings("serial")
public class CategoryPropertiesVo implements java.io.Serializable {

	// Fields

	private String recid;
	private Long recver;
	private String categoryid;
	private String propertiyname;
	private Integer ordinal;

	// Constructors

	/** default constructor */
	public CategoryPropertiesVo() {
	}

	/** minimal constructor */
	public CategoryPropertiesVo(String recid) {
		this.recid = recid;
	}

	/** full constructor */
	public CategoryPropertiesVo(String recid, Long recver, String categoryid, String propertiyname,
			Integer ordinal) {
		this.recid = recid;
		this.recver = recver;
		this.categoryid = categoryid;
		this.propertiyname = propertiyname;
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

	public String getCategoryid() {
		return this.categoryid;
	}

	public void setCategoryid(String categoryid) {
		this.categoryid = categoryid;
	}

	public String getPropertiyname() {
		return this.propertiyname;
	}

	public void setPropertiyname(String propertiyname) {
		this.propertiyname = propertiyname;
	}

	public Integer getOrdinal() {
		return this.ordinal;
	}

	public void setOrdinal(Integer ordinal) {
		this.ordinal = ordinal;
	}

}