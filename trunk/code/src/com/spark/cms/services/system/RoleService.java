package com.spark.cms.services.system;

import java.util.List;

import com.spark.cms.common.tree.TreeModel;
import com.spark.cms.dao.po.RoleResource;
import com.spark.cms.services.form.ResourceExtForm;
import com.spark.cms.services.form.RoleExtForm;

public interface RoleService {
	/**
	 * ��ѯ��ɫ�б�
	 */
	public List<RoleExtForm> getRoles(String ...args);
	
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
	 * ��ȡ��ɫ�����е���Դ
	 */
	public List<RoleResource> getResoucerOfRole(String roleId);

	/**
	 * ������Դ���ͽṹ
	 * @param resourceExtForms
	 * @return
	 */
	public TreeModel generateResourceTree(List<ResourceExtForm> resourceExtForms);
}
