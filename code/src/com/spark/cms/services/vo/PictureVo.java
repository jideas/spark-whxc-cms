package com.spark.cms.services.vo;


/**
 * CmsPicture entity.
 * 
 * @author MyEclipse Persistence Tools
 */ 
@SuppressWarnings("serial")
public class PictureVo implements java.io.Serializable {

	private String recid;
	private Long recver;
	private String picturetype;
	private String goodsid;
	private String picturepath;
	private String description;

	// Constructors

	/** default constructor */
	public PictureVo() {
	}

	/** minimal constructor */
	public PictureVo(String recid) {
		this.recid = recid;
	}

	/** full constructor */
	public PictureVo(String recid, Long recver, String picturetype, String goodsid, String picturepath, String description) {
		this.recid = recid;
		this.recver = recver;
		this.picturetype = picturetype;
		this.goodsid = goodsid;
		this.picturepath = picturepath;
		this.description = description;
	}

	// Property accessors

	public String getRecid() {
		return this.recid;
	}

	public Long getRecver() {
		return recver;
	}

	public void setRecver(Long recver) {
		this.recver = recver;
	}

	public void setRecid(String recid) {
		this.recid = recid;
	} 

	public String getPicturetype() {
		return this.picturetype;
	}

	public void setPicturetype(String picturetype) {
		this.picturetype = picturetype;
	}

	public String getGoodsid() {
		return this.goodsid;
	}

	public void setGoodsid(String goodsid) {
		this.goodsid = goodsid;
	}

	public String getPicturepath() {
		return this.picturepath;
	}

	public void setPicturepath(String picturepath) {
		this.picturepath = picturepath;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}