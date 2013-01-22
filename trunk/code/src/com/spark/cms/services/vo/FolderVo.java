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
public class FolderVo implements Serializable {

	private String recid;
	private String code;
	private String parentId;
	private Long recver;
	private String title;

	// private boolean hasImages;

	public String getRecid() {
		return recid;
	}

	public Long getRecver() {
		return recver;
	}

	public String getTitle() {
		return title;
	}

	// public boolean isHasImages() {
	// return hasImages;
	// }

	public void setRecid(String recid) {
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

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
