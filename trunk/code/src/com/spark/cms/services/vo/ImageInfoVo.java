/**
 * 
 */
package com.spark.cms.services.vo;

import java.io.Serializable;

/**
 * @author Jideas
 *
 */
@SuppressWarnings("serial")
public class ImageInfoVo implements Serializable {
private String recid;	 
	private Long recver;
	private String imgUrl;
	private String imgName;
	private int imgWidth;
	private int imgHeight;
	private String folderId;
	public String getRecid() {
		return recid;
	}
	public Long getRecver() {
		return recver;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public String getImgName() {
		return imgName;
	}
	public int getImgWidth() {
		return imgWidth;
	}
	public int getImgHeight() {
		return imgHeight;
	}
	public String getFolderId() {
		return folderId;
	}
	public void setRecid(String recid) {
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
