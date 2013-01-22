package com.spark.cms.services.vo;

import java.util.List;

/**
 * CmsFloor entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
public class FloorVo implements java.io.Serializable {

	// Fields

	private String recid;
	private Long recver;
	private Integer ordinal;
	private String title;
	private String theme;
	private String floortype;
	private String goodsCategoryId;
	private String imageUrl;
	private String url;
	
	//Ôö¼ÓchannleVo
	private List<ChannelVo> channelVoList;
	
	


	// Constructors

	public List<ChannelVo> getChannelVoList() {
		return channelVoList;
	}

	public void setChannelVoList(List<ChannelVo> channelVoList) {
		this.channelVoList = channelVoList;
	}

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

	public String getGoodsCategoryId() {
		return goodsCategoryId;
	}

	public void setGoodsCategoryId(String goodsCategoryId) {
		this.goodsCategoryId = goodsCategoryId;
	}

	/** default constructor */
	public FloorVo() {
	}

	/** minimal constructor */
	public FloorVo(String recid) {
		this.recid = recid;
	}

	/** full constructor */
	public FloorVo(String recid, Long recver, Integer ordinal, String title, String theme, String floortype) {
		this.recid = recid;
		this.recver = recver;
		this.ordinal = ordinal;
		this.title = title;
		this.theme = theme;
		this.floortype = floortype;
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

	public Integer getOrdinal() {
		return this.ordinal;
	}

	public void setOrdinal(Integer ordinal) {
		this.ordinal = ordinal;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getTheme() {
		return this.theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getFloortype() {
		return this.floortype;
	}

	public void setFloortype(String floortype) {
		this.floortype = floortype;
	}

}