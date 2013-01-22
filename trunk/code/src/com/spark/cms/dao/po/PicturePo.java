package com.spark.cms.dao.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CmsPicture entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "cms_picture")
@SuppressWarnings("serial")
public class PicturePo implements java.io.Serializable {

	// Fields

	private String recid;
	private Long recver;
	private String picturetype;
	private String goodsid;
	private String picturepath;
	private String description;

	// Constructors

	/** default constructor */
	public PicturePo() {
	}

	/** minimal constructor */
	public PicturePo(String recid) {
		this.recid = recid;
	}

	/** full constructor */
	public PicturePo(String recid, Long recver, String picturetype, String goodsid, String picturepath,
			String description) {
		this.recid = recid;
		this.recver = recver;
		this.picturetype = picturetype;
		this.goodsid = goodsid;
		this.picturepath = picturepath;
		this.description = description;
	}

	// Property accessors
	@Id
	@Column(name = "RECID", unique = true, nullable = false)
	public String getRecid() {
		return this.recid;
	}

	public void setRecid(String recid) {
		this.recid = recid;
	}

	@Column(name = "RECVER")
	public Long getRecver() {
		return this.recver;
	}

	public void setRecver(Long recver) {
		this.recver = recver;
	}

	@Column(name = "PICTURETYPE", length = 2)
	public String getPicturetype() {
		return this.picturetype;
	}

	public void setPicturetype(String picturetype) {
		this.picturetype = picturetype;
	}

	@Column(name = "GOODSID")
	public String getGoodsid() {
		return this.goodsid;
	}

	public void setGoodsid(String goodsid) {
		this.goodsid = goodsid;
	}

	@Column(name = "PICTUREPATH", length = 500)
	public String getPicturepath() {
		return this.picturepath;
	}

	public void setPicturepath(String picturepath) {
		this.picturepath = picturepath;
	}

	@Column(name = "DESCRIPTION", length = 1000)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}