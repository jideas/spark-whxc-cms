/**
 * 
 */
package com.spark.cms.services.form.folder;

/**
 * @author Jideas
 * 
 */
public class ImageForm {

	private String title, imgUrl, width, height,realWidth,realHeight;

	public String getTitle() {
		return title;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public String getWidth() {
		return width;
	}

	public String getHeight() {
		return height;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getRealWidth() {
		return realWidth;
	}

	public String getRealHeight() {
		return realHeight;
	}

	public void setRealWidth(String realWidth) {
		this.realWidth = realWidth;
	}

	public void setRealHeight(String realHeight) {
		this.realHeight = realHeight;
	}
}
