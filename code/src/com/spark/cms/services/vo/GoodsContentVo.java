package com.spark.cms.services.vo;


/**
 * CmsGoodsContent entity.
 * 
 * @author MyEclipse Persistence Tools
 */ 
@SuppressWarnings("serial")
public class GoodsContentVo implements java.io.Serializable {

	// Fields

	private String recid;
	private Long recver;
	private String goodsid;
	private String contenttitle;
	private Integer ordinal;
	private String goodscontent;

	// Constructors

	/** default constructor */
	public GoodsContentVo() {
	}

	/** minimal constructor */
	public GoodsContentVo(String recid) {
		this.recid = recid;
	}

	/** full constructor */
	public GoodsContentVo(String recid, Long recver, String goodsid, String contenttitle, Integer ordinal,
			String goodscontent) {
		this.recid = recid;
		this.recver = recver;
		this.goodsid = goodsid;
		this.contenttitle = contenttitle;
		this.ordinal = ordinal;
		this.goodscontent = goodscontent;
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

	public String getGoodsid() {
		return this.goodsid;
	}

	public void setGoodsid(String goodsid) {
		this.goodsid = goodsid;
	}

	public String getContenttitle() {
		return this.contenttitle;
	}

	public void setContenttitle(String contenttitle) {
		this.contenttitle = contenttitle;
	}

	public Integer getOrdinal() {
		return this.ordinal;
	}

	public void setOrdinal(Integer ordinal) {
		this.ordinal = ordinal;
	}

	public String getGoodscontent() {
		return this.goodscontent;
	}

	public void setGoodscontent(String goodscontent) {
		this.goodscontent = goodscontent;
	}

}