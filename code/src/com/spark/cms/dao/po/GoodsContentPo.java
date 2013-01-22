package com.spark.cms.dao.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CmsGoodsContent entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "cms_goods_content")
@SuppressWarnings("serial")
public class GoodsContentPo implements java.io.Serializable {

	// Fields

	private byte[] recid;
	private Long recver;
	private byte[] goodsid;
	private String contenttitle;
	private Integer ordinal;
	private String goodscontent;

	// Constructors

	/** default constructor */
	public GoodsContentPo() {
	}

	/** minimal constructor */
	public GoodsContentPo(byte[] recid) {
		this.recid = recid;
	}

	/** full constructor */
	public GoodsContentPo(byte[] recid, Long recver, byte[] goodsid, String contenttitle, Integer ordinal,
			String goodscontent) {
		this.recid = recid;
		this.recver = recver;
		this.goodsid = goodsid;
		this.contenttitle = contenttitle;
		this.ordinal = ordinal;
		this.goodscontent = goodscontent;
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

	@Column(name = "GOODSID")
	public byte[] getGoodsid() {
		return this.goodsid;
	}

	public void setGoodsid(byte[] goodsid) {
		this.goodsid = goodsid;
	}

	@Column(name = "CONTENTTITLE", length = 10)
	public String getContenttitle() {
		return this.contenttitle;
	}

	public void setContenttitle(String contenttitle) {
		this.contenttitle = contenttitle;
	}

	@Column(name = "ORDINAL")
	public Integer getOrdinal() {
		return this.ordinal;
	}

	public void setOrdinal(Integer ordinal) {
		this.ordinal = ordinal;
	}

	@Column(name = "GOODSCONTENT")
	public String getGoodscontent() {
		return this.goodscontent;
	}

	public void setGoodscontent(String goodscontent) {
		this.goodscontent = goodscontent;
	}

}