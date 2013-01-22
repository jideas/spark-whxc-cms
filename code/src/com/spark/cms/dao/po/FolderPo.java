/**
 * 
 */
package com.spark.cms.dao.po;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Jideas
 * 
 */
@Entity
@Table(name = "cms_folder")
@SuppressWarnings("serial")
public class FolderPo implements Serializable {

	private byte[] recid;
	private String code;
	private byte[] parentId;
	private Long recver;
	private String title;
//	private boolean hasImages;

	@Id
	@Column(name = "RECID", unique = true, nullable = false)
	public byte[] getRecid() {
		return recid;
	}
	@Column(name = "code")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "RECVER")
	public Long getRecver() {
		return recver;
	}

	@Column(name = "title")
	public String getTitle() {
		return title;
	}

//	@Column(name = "hasImages")
//	public boolean isHasImages() {
//		return hasImages;
//	}

	public void setRecid(byte[] recid) {
		this.recid = recid;
	}

	public void setRecver(Long recver) {
		this.recver = recver;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	// public void setHasImages(boolean hasImages) {
	// this.hasImages = hasImages;
	// }
	@Column(name = "parentId")
	public byte[] getParentId() {
		return parentId;
	}

	public void setParentId(byte[] parentId) {
		this.parentId = parentId;
	}
}
