package com.spark.cms.services.form;

import java.util.List;

/**
 * ��ɫ
 */
public class RoleExtForm {
	/**
	 * id��ʶ
	 */
	private String id;
	
	/**
	 * ����
	 */
	private String title;
	
	/**
	 * ����
	 */
	private String code;
	
	/**
	 * ��ѡ��ѡ��״̬������չ��
	 */
	private boolean ck;

	/**
	 * ��Դ�б�
	 * @return
	 */
	private List<ResourceExtForm> resourcesList;
	
	
	public boolean isCk() {
		return ck;
	}

	public void setCk(boolean ck) {
		this.ck = ck;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<ResourceExtForm> getResourcesList() {
		return resourcesList;
	}

	public void setResourcesList(List<ResourceExtForm> resourcesList) {
		this.resourcesList = resourcesList;
	}
}
