package com.spark.cms.services.vo;

/**
 * CmsFloor entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
public class PopularKeyWordsVo implements java.io.Serializable {

	// Fields

	private String recid;
	private String popularKeyWords;
	
	// Constructors

	public String getPopularKeyWords() {
		return popularKeyWords;
	}

	public void setPopularKeyWords(String popularKeyWords) {
		this.popularKeyWords = popularKeyWords;
	}

	/** default constructor */
	public PopularKeyWordsVo() {
	}

	/** minimal constructor */
	public PopularKeyWordsVo(String recid) {
		this.recid = recid;
	}

	/** full constructor */
	public PopularKeyWordsVo(String recid, String popularKeyWords) {
		this.recid = recid;
		this.popularKeyWords = popularKeyWords;
	}

	// Property accessors
	public String getRecid() {
		return this.recid;
	}

	public void setRecid(String recid) {
		this.recid = recid;
	}

}