package com.spark.cms.dao.po;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "cms_floor_advertising")
public class FloorAdvertisingPo implements Serializable {
	private byte[] recid;
	private Long recver;
	private byte[] floorId;
	private String imageurl;
	private String url;
	private String title;
	
	/** default constructor */
	public FloorAdvertisingPo() {
	}

	/** minimal constructor */
	public FloorAdvertisingPo(byte[] recid) {
		this.recid = recid;
	}

	/** full constructor */
	public FloorAdvertisingPo(byte[] recid, Long recver, byte[] floorId, 
			String imageurl, String url, String title) {
		this.recid = recid;
		this.recver = recver;
		this.floorId = floorId;
		this.imageurl = imageurl;
		this.url = url;
		this.title = title;
	}

	@Id
	@Column(name = "RECID", unique = true, nullable = false)
	public byte[] getRecid() {
		return recid;
	}

	public void setRecid(byte[] recid) {
		this.recid = recid;
	}
	@Column(name = "RECVER")
	public Long getRecver() {
		return recver;
	}

	public void setRecver(Long recver) {
		this.recver = recver;
	}
	@Column(name = "floorId")
	public byte[] getFloorId() {
		return floorId;
	}

	public void setFloorId(byte[] floorId) {
		this.floorId = floorId;
	}
	@Column(name = "IMAGEURL", length = 200)
	public String getImageurl() {
		return imageurl;
	}

	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}
	@Column(name = "url", length = 200)
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	@Column(name = "title")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
}
