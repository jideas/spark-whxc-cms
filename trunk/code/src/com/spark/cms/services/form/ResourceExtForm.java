package com.spark.cms.services.form;


/**
 * ��Դ
 */

public class ResourceExtForm {

	/**
	 * ��ԴId
	 */
	private String id;

	/**
	 * ���ڵ�
	 */
	private String parentId;

	/**
	 * ��Դ����
	 */
	private String code;

	/**
	 * ����
	 */
	private String title;

	/**
	 * ��Դλ��
	 */
	private String href;
	
	/**
	 * ��Դ��ʹ��
	 * @return
	 */
	private boolean isChecked = false;
	
	public boolean isChecked() {
		return isChecked;
	}

	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

}
