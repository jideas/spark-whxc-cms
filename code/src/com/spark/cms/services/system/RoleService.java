package com.spark.cms.services.system;

import java.util.List;

import com.spark.cms.common.tree.TreeModel;
import com.spark.cms.dao.po.RoleResource;
import com.spark.cms.services.form.ResourceExtForm;
import com.spark.cms.services.form.RoleExtForm;

public interface RoleService {
	/**
	 * 查询角色列表
	 */
	public List<RoleExtForm> getRoles(String ...args);
	
	/**
	 * 通过角色
	 */
	public RoleExtForm getRoleById(String roleId);
	
	/**
	 * 增加角色
	 */
	public void addRole(RoleExtForm roleExtForm);
	
	/**
	 * 修改角色
	 */
	public void updateRole(RoleExtForm roleExtForm);
	
	/**
	 * 删除角色
	 */
	public void deleteRole(String...  roleIds);
	
	/**
	 * 指定资源
	 */
	public void exeAssignResouces(String roleId,String[] resourceIds);
	
	
	/**
	 * 获取角色所具有的资源
	 */
	public List<RoleResource> getResoucerOfRole(String roleId);

	/**
	 * 构建资源树型结构
	 * @param resourceExtForms
	 * @return
	 */
	public TreeModel generateResourceTree(List<ResourceExtForm> resourceExtForms);
}
