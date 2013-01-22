package com.spark.cms.services.vo;

import java.util.Date;

/**
 * CmsChannelContent entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
public class ChannelContentVo implements java.io.Serializable {

	// Fields

	private String recid;
	private Long recver;
	private String channelid;
	private String code;
	private String title;
	private String content;
	private String creatorid;
	private String creator;
	private Date createdate;
	private String status;

	// Constructors

	/** default constructor */
	public ChannelContentVo() {
	}

	/** minimal constructor */
	public ChannelContentVo(String recid) {
		this.recid = recid;
	}

	/** full constructor */
	public ChannelContentVo(String recid, Long recver, String channelid, String code, String title,
			String content, String creatorid, String creator, Date createdate, String status) {
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

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
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

}