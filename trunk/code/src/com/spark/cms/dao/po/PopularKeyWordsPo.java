package com.spark.cms.dao.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CmsFloor entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "cms_PopularKeyWords")
public class PopularKeyWordsPo implements java.io.Serializable {

	// Fields

	private byte[] recid;
	private String popularKeyWords;
	
	// Constructors

	@Column(name = "PopularKeyWords")
	public String getPopularKeyWords() {
		return popularKeyWords;
	}

	public void setPopularKeyWords(String popularKeyWords) {
		this.popularKeyWords = popularKeyWords;
	}

	/** default constructor */
	public PopularKeyWordsPo() {
	}

	/** minimal constructor */
	public PopularKeyWordsPo(byte[] recid) {
		this.recid = recid;
	}

	/** full constructor */
	public PopularKeyWordsPo(byte[] recid, String popularKeyWords) {
		this.recid = recid;
		this.popularKeyWords = popularKeyWords;
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

}