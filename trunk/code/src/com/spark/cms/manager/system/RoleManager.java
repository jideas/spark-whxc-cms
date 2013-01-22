package com.spark.cms.manager.system;


import com.spark.cms.common.DataModel;
import com.spark.cms.common.tree.TreeModel;
import com.spark.cms.services.form.RoleExtForm;

public interface RoleManager {
	
	/**
	 * ��ѯ��ɫ�б�
	 */
	@SuppressWarnings("unchecked")
	public DataModel getRoles(String ...args);
	
	/**
	 * ͨ����ɫ
	 */
	public RoleExtForm getRoleById(String roleId);
	
	/**
	 * ���ӽ�ɫ
	 */
	public void addRole(RoleExtForm roleExtForm);
	
	/**
	 * �޸Ľ�ɫ
	 */
	public void updateRole(RoleExtForm roleExtForm);
	
	/**
	 * ɾ����ɫ
	 */
	public void deleteRole(String...  roleIds);
	
	/**
	 * ָ����Դ
	 */
	public void exeAssignResouces(String roleId,String[] resourceIds);
	
	/**
	 * ��ȡ��ɫ�����е���Դ��
	 */
	public  TreeModel getResourceOfRole(String roleId);
	
	
}
