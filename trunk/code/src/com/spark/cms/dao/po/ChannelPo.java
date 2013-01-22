package com.spark.cms.dao.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CmsChannel entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "cms_channel")
public class ChannelPo implements java.io.Serializable {

	// Fields

	private byte[] recid;
	private Long recver;
	private String code;
	private String name;
	private byte[] floorid;
	private String channeltype;
	private String pageType;
	private boolean isMainMenu;

	// Constructors

	@Column(name = "isMainMenu")
	public boolean isMainMenu() {
		return isMainMenu;
	}

	public void setMainMenu(boolean isMainMenu) {
		this.isMainMenu = isMainMenu;
	}

	@Column(name = "pageType")
	public String getPageType() {
		return pageType;
	}

	public void setPageType(String pageType) {
		this.pageType = pageType;
	}

	/** default constructor */
	public ChannelPo() {
	}

	/** minimal constructor */
	public ChannelPo(byte[] recid) {
		this.recid = recid;
	}

	/** full constructor */
	public ChannelPo(byte[] recid, Long recver, String code, String name, byte[] floorid, String channeltype) {
		this.recid = recid;
		this.recver = recver;
		this.code = code;
		this.name = name;
		this.floorid = floorid;
		this.channeltype = channeltype;
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

	@Column(name = "CODE", length = 20)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "NAME", length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "FLOORID", length = 10)
	public byte[] getFloorid() {
		return this.floorid;
	}

	public void setFloorid(byte[] floorid) {
		this.floorid = floorid;
	}

	@Column(name = "CHANNELTYPE", length = 2)
	public String getChanneltype() {
		return this.channeltype;
	}

	public void setChanneltype(String channeltype) {
		this.channeltype = channeltype;
	}

}