package com.spark.cms.services.form;

import java.util.List;

import com.spark.cms.common.tree.TreeModel;

/**
 * �û�
 */

public class UserExtForm {
	
	/**
	 * ��ʶ
	 */
	private  String id;
	
	/**
	 * ����
	 */
	private String name;
	
	/**
	 * ϵͳ��¼�ʺ�
	 */
	private String account;
	
	/**
	 * �ֻ�����
	 */
	private String tele;
	
	/**
	 * �ֻ�����
	 */
	private String mobile;
	
	/**
	 * email
	 */
	private String email;
	
	/**
	 * ����
	 */
	private String pwd;
	
	/**
	 * ����״̬
	 */
	private String isEnabled;
	
	/**
	 * ��ɫ�б�
	 */
	private List<RoleExtForm> roleExtList;
	
	/**
	 * ��Դ�б�
	 * @return
	 */
	private List<ResourceExtForm> resourceExtFormsList;
	
	/**
	 * ��Դ���б�
	 * @return
	 */
	private TreeModel resourceTree;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getTele() {
		return tele;
	}

	public void setTele(String tele) {
		this.tele = tele;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public List<RoleExtForm> getRoleExtList() {
		return roleExtList;
	}

	public void setRoleExtList(List<RoleExtForm> roleExtList) {
		this.roleExtList = roleExtList;
	}

	public String getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(String isEnabled) {
		this.isEnabled = isEnabled;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public List<ResourceExtForm> getResourceExtFormsList() {
		return resourceExtFormsList;
	}

	public void setResourceExtFormsList(List<ResourceExtForm> resourceExtFormsList) {
		this.resourceExtFormsList = resourceExtFormsList;
	}

	public TreeModel getResourceTree() {
		return resourceTree;
	}

	public void setResourceTree(TreeModel resourceTree) {
		this.resourceTree = resourceTree;
	}
	
}
