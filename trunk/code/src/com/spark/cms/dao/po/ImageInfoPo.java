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
@Table(name = "CMS_IMAGE_INFO")
@SuppressWarnings("serial")
public class ImageInfoPo implements Serializable {
	private byte[] recid;
	private Long recver;
	private String imgUrl;
	private String imgName;
	private int imgWidth;
	private int imgHeight;
	private String folderId;

	@Id
	@Column(name = "RECID", unique = true, nullable = false)
	public byte[] getRecid() {
		return recid;
	}

	@Column(name = "RECVER")
	public Long getRecver() {
		return recver;
	}

	@Column(name = "imgUrl")
	public String getImgUrl() {
		return imgUrl;
	}

	@Column(name = "imgName")
	public String getImgName() {
		return imgName;
	}

	@Column(name = "imgWidth")
	public int getImgWidth() {
		return imgWidth;
	}

	@Column(name = "imgHeight")
	public int getImgHeight() {
		return imgHeight;
	}

	@Column(name = "folderId")
	public String getFolderId() {
		return folderId;
	}

	public void setRecid(byte[] recid) {
		this.recid = recid;
	}

	public void setRecver(Long recver) {
		this.recver = recver;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public void setImgName(String imgName) {
		this.imgName = imgName;
	}

	public void setImgWidth(int imgWidth) {
		this.imgWidth = imgWidth;
	}

	public void setImgHeight(int imgHeight) {
		this.imgHeight = imgHeight;
	}

	public void setFolderId(String folderId) {
		this.folderId = folderId;
	}
}
