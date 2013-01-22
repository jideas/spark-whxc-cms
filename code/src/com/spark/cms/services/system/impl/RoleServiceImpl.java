package com.spark.cms.services.system.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spark.base.common.utils.BeanUtils;
import com.spark.base.common.utils.ListUtils;
import com.spark.base.common.utils.RandomGUID;
import com.spark.base.hibernate.GenericDAO;
import com.spark.cms.common.tree.TreeModel;
import com.spark.cms.common.tree.TreeModel.AttributesModel;
import com.spark.cms.dao.po.Role;
import com.spark.cms.dao.po.RoleResource;
import com.spark.cms.services.form.ResourceExtForm;
import com.spark.cms.services.form.RoleExtForm;
import com.spark.cms.services.system.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private GenericDAO genericDAO;
	 
	
	@Override
	public void addRole(RoleExtForm roleExtForm) {
		Role role = BeanUtils.copyProperties(Role.class, roleExtForm);
		role.setId(RandomGUID.newGuid());
		genericDAO.save(role);
	}

	@Override
	public void exeAssignResouces(String roleId,String[] resourceIds) {
		 String sql = "delete from RoleResource rr where rr.roleId='"+roleId+"' ";
		 genericDAO.execteBulk(sql);
		 RoleResource cmsRoleResource;
		 for(String rrid:resourceIds){
		 cmsRoleResource = new RoleResource();
		 cmsRoleResource.setId(RandomGUID.newGuid());
		 cmsRoleResource.setRoleId(roleId);
		 cmsRoleResource.setResourceId(rrid);
		 genericDAO.save(cmsRoleResource);
		 }
	}

	@Override
	public void deleteRole(String...roleIds) {
		 String sql = null;
		for(String id:roleIds){
			 sql = "delete from RoleResource rr where rr.roleId='"+id+"'  ";
			 genericDAO.execteBulk(sql);
			 genericDAO.delete(Role.class,id);
		}
	}

	@Override
	public RoleExtForm getRoleById(String roleId) {
		return BeanUtils.copyProperties(RoleExtForm.class,genericDAO.get(Role.class,roleId));
	}

	@Override
	public List<RoleExtForm> getRoles(String... args) {
		String sql = " from Role r  order by r.code";
		return ListUtils.transform(genericDAO.getGenericByHql(sql), RoleExtForm.class);
	}

	@Override
	public void updateRole(RoleExtForm roleExtForm) {
		Role role = genericDAO.get(Role.class,roleExtForm.getId());
		role.setCode(roleExtForm.getCode());
		role.setTitle(roleExtForm.getTitle());
		genericDAO.update(role);
	}

	@Override
	public List<RoleResource> getResoucerOfRole(String roleId) {
		String sql = " from RoleResource rr where  rr.roleId='"+roleId+"'";
		List<RoleResource> list = genericDAO.getGenericByHql(sql);
		return list;
	}
	
	@Override
	public TreeModel  generateResourceTree(List<ResourceExtForm> resourceExtForms){
		TreeModel root = new TreeModel();
		List<TreeModel> treeModeList = new ArrayList<TreeModel>();
		List<TreeModel> parentList = new ArrayList<TreeModel>();
		HashMap<String,ResourceExtForm> treemMap = new LinkedHashMap<String,ResourceExtForm>();
		HashMap<String, List<ResourceExtForm>> treeMapList =new LinkedHashMap<String, List<ResourceExtForm>>();
		for(ResourceExtForm form:resourceExtForms){
			String parentId = form.getParentId();
			if(!com.spark.base.common.utils.StringUtil.isEmpty(parentId)){
					if(treeMapList.containsKey(parentId)){
						treeMapList.get(parentId).add(form);
					}else{
						List<ResourceExtForm> list = new ArrayList<ResourceExtForm>();
						list.add(form);
						treeMapList.put(parentId,list);
					}
					treemMap.put(form.getId(), form);
			}else{
				TreeModel teemoModel = convertResouce2TreeNode(form);
				treeModeList.add(teemoModel);
				parentList.add(teemoModel);
			}
		}
		
		if(treeModeList.size()>0){
			root.setChildren(treeModeList.toArray(new TreeModel[treeModeList.size()]));
			root.setState("open");
			root.setId("root");
			root.setText("资源管理");
		}
		
		boolean hasChidren = true;
		List<TreeModel> nextTreeModelList = new ArrayList<TreeModel>();
		Set<String> parentIdKeySet = treeMapList.keySet();
		while(hasChidren){
			hasChidren = false;
			nextTreeModelList.clear();
			for(TreeModel parentTreeModel : parentList){
				Iterator<String> it = parentIdKeySet.iterator();
				while(it.hasNext()){
					String key = it.next();
					if(key.equals(parentTreeModel.getId())){
						List<ResourceExtForm> innerResouceList = treeMapList.get(key);
						List<TreeModel> childrenModelList = new ArrayList<TreeModel>();
						for(ResourceExtForm ref : innerResouceList){
							TreeModel tm = convertResouce2TreeNode(ref);
							tm.setState("open");
							nextTreeModelList.add(tm);
							childrenModelList.add(tm);
							hasChidren = true;
						}
						parentTreeModel.setChildren(childrenModelList.toArray(new TreeModel[childrenModelList.size()]));
						parentTreeModel.setState("open");
					}
				}
				if(!hasChidren){
					parentTreeModel.setState("closed");
				}else{
					/**
					 * 解决树显示问题
					 */
					parentTreeModel.setChecked(false);
				}
			}
			parentList = nextTreeModelList;
		}
		return root;
	}
	
	private TreeModel convertResouce2TreeNode(ResourceExtForm ref){
		TreeModel treeModel = new TreeModel();
		treeModel.setId(ref.getId());
		treeModel.setText(ref.getTitle());
		treeModel.setChecked(ref.isChecked());
		AttributesModel attributeModel = treeModel.new AttributesModel();
		attributeModel.setCode(ref.getCode());
		attributeModel.setParentId(ref.getParentId());
		attributeModel.setHref(ref.getHref());
		treeModel.setAttributes(attributeModel);
		return treeModel;
	}
	

}
