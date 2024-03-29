package com.spark.cms.dao.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CmsResource entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "cms_resource")
public class Resource implements java.io.Serializable {

	// Fields

	private String id;
	private String parentId;
	private String code;
	private String title;
	private String href;

	// Constructors

	/** default constructor */
	public Resource() {
	}

	/** minimal constructor */
	public Resource(String id) {
		this.id = id;
	}

	/** full constructor */
	public Resource(String id, String parentId, String code, String title,
			String href) {
		this.id = id;
		this.parentId = parentId;
		this.code = code;
		this.title = title;
		this.href = href;
	}

	// Property accessors
	@Id
	@Column(name = "id", unique = true, nullable = false, length = 32)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "parentId", length = 32)
	public String getParentId() {
		return this.parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	@Column(name = "code", length = 50)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "title", length = 200)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "href", length = 500)
	public String getHref() {
		return this.href;
	}

	public void setHref(String href) {
		this.href = href;
	}

}