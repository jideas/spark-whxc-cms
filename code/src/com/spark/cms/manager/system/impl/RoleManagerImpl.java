package com.spark.cms.manager.system.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spark.cms.common.DataModel;
import com.spark.cms.common.tree.TreeModel;
import com.spark.cms.dao.po.RoleResource;
import com.spark.cms.manager.system.RoleManager;
import com.spark.cms.services.form.ResourceExtForm;
import com.spark.cms.services.form.RoleExtForm;
import com.spark.cms.services.system.ResourceService;
import com.spark.cms.services.system.RoleService;
@Component
public class RoleManagerImpl  implements RoleManager{
	
	@Autowired
	private RoleService roleServices;
	
	@Autowired
	private ResourceService resourceService;

	@Override
	public void addRole(RoleExtForm roleExtForm) {
		roleServices.addRole(roleExtForm);
	}

	@Override
	public void exeAssignResouces(String roleId,String[] resourceIds) {
		roleServices.exeAssignResouces(roleId,resourceIds);
		
	}

	@Override
	public void deleteRole(String... roleIds) {
		roleServices.deleteRole(roleIds);
	}

	@Override
	public RoleExtForm getRoleById(String roleId) {
		return roleServices.getRoleById(roleId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public DataModel getRoles(String... args) {
		DataModel model = new DataModel();
		model.setRows(roleServices.getRoles(args));
		return model;
	}

	@Override
	public void updateRole(RoleExtForm roleExtForm) {
		 roleServices.updateRole(roleExtForm);
	}

	@Override
	public TreeModel getResourceOfRole(String roleId) {
		
		List<ResourceExtForm> r = resourceService.getResources();
		List<RoleResource> ur = roleServices.getResoucerOfRole(roleId);
		
		for(ResourceExtForm ref:r){
			if(ur!=null && ur.size()>0){
				boolean checked = false;
				for(RoleResource uref:ur){
					if(ref.getId().equals(uref.getResourceId())){
						ref.setChecked(true);
						checked = true;
						break;
					}
				}
				if(!checked)ref.setChecked(false);
			}else{
				ref.setChecked(false);
			}
		}
		return roleServices.generateResourceTree(r);
		 
	}

}
