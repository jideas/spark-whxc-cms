package com.spark.cms.dao.po;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * CmsChannelAdvertising entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "cms_channel_advertising")
public class ChannelAdvertisingPo implements java.io.Serializable {

	// Fields

	private byte[] recid;
	private Long recver;
	private byte[] channelid;
	private byte[] creatorid;
	private String creator;
	private Date createdate;
	private String status;
	private String imageurl;
	private String url;

	// Constructors

	/** default constructor */
	public ChannelAdvertisingPo() {
	}

	/** minimal constructor */
	public ChannelAdvertisingPo(byte[] recid) {
		this.recid = recid;
	}

	/** full constructor */
	public ChannelAdvertisingPo(byte[] recid, Long recver, byte[] channelid, byte[] creatorid,
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

	@Column(name = "CHANNELID")
	public byte[] getChannelid() {
		return this.channelid;
	}

	public void setChannelid(byte[] channelid) {
		this.channelid = channelid;
	}

	@Column(name = "CREATORID")
	public byte[] getCreatorid() {
		return this.creatorid;
	}

	public void setCreatorid(byte[] creatorid) {
		this.creatorid = creatorid;
	}

	@Column(name = "CREATOR", length = 30)
	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CREATEDATE", length = 19)
	public Date getCreatedate() {
		return this.createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	@Column(name = "STATUS", length = 2)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "IMAGEURL", length = 200)
	public String getImageurl() {
		return this.imageurl;
	}

	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}

	@Column(name = "URL", length = 200)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}