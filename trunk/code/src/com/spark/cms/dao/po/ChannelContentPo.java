package com.spark.cms.dao.po;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * CmsChannelContent entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "cms_channel_content")
public class ChannelContentPo implements java.io.Serializable {

	// Fields

	private byte[] recid;
	private Long recver;
	private byte[] channelid;
	private String code;
	private String title;
	private String content;
	private byte[] creatorid;
	private String creator;
	private Date createdate;
	private String status;

	// Constructors

	/** default constructor */
	public ChannelContentPo() {
	}

	/** minimal constructor */
	public ChannelContentPo(byte[] recid) {
		this.recid = recid;
	}

	/** full constructor */
	public ChannelContentPo(byte[] recid, Long recver, byte[] channelid, String code, String title,
			String content, byte[] creatorid, String creator, Date createdate, String status) {
		this.recid = recid;
		this.recver = recver;
		this.channelid = channelid;
		this.code = code;
		this.title = title;
		this.content = content;
		this.creatorid = creatorid;
		this.creator = creator;
		this.createdate = createdate;
		this.status = status;
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

	@Column(name = "CODE", length = 20)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "TITLE", length = 50)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "CONTENT")
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
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

}