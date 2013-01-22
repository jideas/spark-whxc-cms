package com.spark.cms.services.vo;

import java.util.Date;

/**
 * CmsChannelAdvertising entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
public class ChannelAdvertisingVo implements java.io.Serializable {

	// Fields

	private String recid;
	private Long recver;
	private String channelid;
	private String creatorid;
	private String creator;
	private Date createdate;
	private String status;
	private String imageurl;
	private String url;

	// Constructors

	/** default constructor */
	public ChannelAdvertisingVo() {
	}

	/** minimal constructor */
	public ChannelAdvertisingVo(String recid) {
		this.recid = recid;
	}

	/** full constructor */
	public ChannelAdvertisingVo(String recid, Long recver, String channelid, String creatorid,
			String creator, Date createdate, String status, String imageurl, String url) {
		this.recid = recid;
		this.recver = recver;
		this.channelid = channelid;
		this.creatorid = creatorid;
		this.creator = creator;
		this.createdate = createdate;
		this.status = status;
		this.imageurl = imageurl;
		this.url = url;
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

	public String getChannelid() {
		return this.channelid;
	}

	public void setChannelid(String channelid) {
		this.channelid = channelid;
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

	public Date getCreatedate() {
		return this.createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getImageurl() {
		return this.imageurl;
	}

	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}