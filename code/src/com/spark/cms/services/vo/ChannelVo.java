package com.spark.cms.services.vo;

import com.spark.cms.common.DataModel;


/**
 * CmsChannel entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
public class ChannelVo implements java.io.Serializable {

	// Fields

	private String recid;
//	private Long recver;
	private String code;
	private String name;
	private String floorid;
	private String channeltype;
	private String pageType;
	private boolean isMainMenu;
	
	private DataModel<ChannelGoodsVo> goodsModel;

	// Constructors

	public DataModel<ChannelGoodsVo> getGoodsModel() {
		return goodsModel;
	}

	public void setGoodsModel(DataModel<ChannelGoodsVo> goodsModel) {
		this.goodsModel = goodsModel;
	}

	public boolean isMainMenu() {
		return isMainMenu;
	}

	public void setMainMenu(boolean isMainMenu) {
		this.isMainMenu = isMainMenu;
	}

	// Constructors

	public String getPageType() {
		return pageType;
	}

	public void setPageType(String pageType) {
		this.pageType = pageType;
	}

	/** default constructor */
	public ChannelVo() {
	}

	/** minimal constructor */
	public ChannelVo(String recid) {
		this.recid = recid;
	}

	/** full constructor */
	public ChannelVo(String recid, String code, String name, String floorid, String channeltype) {
		this.recid = recid;
//		this.recver = recver;
		this.code = code;
		this.name = name;
		this.floorid = floorid;
		this.channeltype = channeltype;
	}

	// Property accessors
	public String getRecid() {
		return this.recid;
	}

	public void setRecid(String recid) {
		this.recid = recid;
	}

//	public Long getRecver() {
//		return this.recver;
//	}
//
//	public void setRecver(Long recver) {
//		this.recver = recver;
//	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFloorid() {
		return this.floorid;
	}

	public void setFloorid(String floorid) {
		this.floorid = floorid;
	}

	public String getChanneltype() {
		return this.channeltype;
	}

	public void setChanneltype(String channeltype) {
		this.channeltype = channeltype;
	}

}