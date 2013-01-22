package com.spark.cms.dao.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CmsFloor entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "cms_floor")
public class FloorPo implements java.io.Serializable {

	// Fields

	private byte[] recid;
	private Long recver;
	private Integer ordinal;
	private String title;
	private String theme;
	private String floortype;
	private byte[] goodsCategoryId;
	private String imageUrl;
	private String url;


	// Constructors

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public byte[] getGoodsCategoryId() {
		return goodsCategoryId;
	}

	public void setGoodsCategoryId(byte[] goodsCategoryId) {
		this.goodsCategoryId = goodsCategoryId;
	}

	/** default constructor */
	public FloorPo() {
	}

	/** minimal constructor */
	public FloorPo(byte[] recid) {
		this.recid = recid;
	}

	/** full constructor */
	public FloorPo(byte[] recid,byte[] goodsCategoryId, Long recver, Integer ordinal, String title, String theme, String floortype) {
		this.recid = recid;
		this.recver = recver;
		this.ordinal = ordinal;
		this.title = title;
		this.theme = theme;
		this.floortype = floortype;
		this.goodsCategoryId = goodsCategoryId;
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

	@Column(name = "ORDINAL")
	public Integer getOrdinal() {
		return this.ordinal;
	}

	public void setOrdinal(Integer ordinal) {
		this.ordinal = ordinal;
	}

	@Column(name = "TITLE", length = 50)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "THEME", length = 2)
	public String getTheme() {
		return this.theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	@Column(name = "FLOORTYPE", length = 2)
	public String getFloortype() {
		return this.floortype;
	}

	public void setFloortype(String floortype) {
		this.floortype = floortype;
	}

}