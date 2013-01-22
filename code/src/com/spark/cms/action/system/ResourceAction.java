package com.spark.cms.action.system;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spark.base.common.utils.StringUtil;
import com.spark.cms.action.BaseAction;
import com.spark.cms.common.MessageModel;
import com.spark.cms.common.ResponseEntityUtil;
import com.spark.cms.common.tree.TreeModel;
import com.spark.cms.common.tree.TreeModel.AttributesModel;
import com.spark.cms.manager.system.ResourceManage;
import com.spark.cms.services.form.ResourceExtForm;

@Controller
public class ResourceAction extends BaseAction {

	@Autowired
	private ResourceManage resourceManager;
	
	/**
	 * ��ȡ��Դ�б�
	 */
	@RequestMapping("/resource/getResources")
	@ResponseBody
	public List<TreeModel> getResources(@RequestParam(value = "id", required = false) String id) {
		try {
			if(id==null||"root".equals(id)){
				TreeModel treeModel = new TreeModel();
				treeModel.setId("root");
				treeModel.setText("ϵͳ��Դ");
				treeModel.setState("open");
				
				List<TreeModel> list = new ArrayList<TreeModel>();
				list.add(treeModel);
				List<ResourceExtForm> resourceList = resourceManager.getResourcesByParentId(null);
				resourceConventerToTreeModel(resourceList);
				treeModel.setChildren(resourceConventerToTreeModel(resourceList).toArray(new TreeModel[resourceList.size()]));
				return list;
			}else{
				List<ResourceExtForm> resourceList = resourceManager.getResourcesByParentId(id);
				return resourceConventerToTreeModel(resourceList);
			}
		} catch (Exception e) {
			log.error("��ȡ��Դ�����쳣====" + e.getStackTrace());
		}
		return null;
	}

	/**
	 * �����������Դ
	 */
	@RequestMapping("/resource/saveResource")
	@ResponseBody
	public ResponseEntity<List<TreeModel>> addResource(ResourceExtForm resource) {
		ResourceExtForm ref = null;
		try {
			if(StringUtil.isEmpty(resource.getId())){
				ref = resourceManager.addResource(resource);
			}else{
				ref = resourceManager.updateResource(resource);
			}
			List<ResourceExtForm> list = new ArrayList<ResourceExtForm>();
			list.add(ref);
			
			return ResponseEntityUtil.getResponseEntity(resourceConventerToTreeModel(list));
		} catch (Exception e) {
			log.error("������Դ�����쳣====" + e.getStackTrace());
		}
		return null;
	}
	
	
	@RequestMapping("/resource/checkResourceCode")
	@ResponseBody
	public MessageModel checkResourceCode(@RequestParam(value = "id", required = false)String id,
			@RequestParam(value = "code", required = true)String code) {
		try {
			boolean r = resourceManager.checkResouceCode(id, code);
			if(r){
				return new MessageModel(false,"��Դ����Ѿ�����");
			}else{
				return new MessageModel(true,"��Դ����Ѿ�������");
			}
		} catch (Exception e) {
			log.error("ɾ����Դ�����쳣====" + e.getStackTrace());
			return Failure;
		}
	}

	/**
	 * ɾ����Դ
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/resource/deleteResource")
	@ResponseBody
	public MessageModel deleteResource2(@RequestParam(value = "id", required = true)
			String id) {
		try {
			resourceManager.deleteResource(id);
			return Success;
		} catch (Exception e) {
			log.error("ɾ����Դ�����쳣====" + e.getStackTrace());
			return Failure;
		}
	}
	
	/**
	 * ����Դת��Ϊ��Ӧ��TreeModel
	 */
	private List<TreeModel> resourceConventerToTreeModel(List<ResourceExtForm> resourceList) {
		List<TreeModel> treeModelList = new ArrayList<TreeModel>();
		ResourceExtForm resource;
		
		TreeModel treeModel;
		AttributesModel attributeModel;
		for (int i = 0; i < resourceList.size(); i++) {
			resource = resourceList.get(i);
			
			treeModel = new TreeModel();
			treeModel.setId(resource.getId());
			treeModel.setText(resource.getTitle());
			treeModel.setState(isHasNodes(resource.getId()));
			
			attributeModel = treeModel.new AttributesModel();
			attributeModel.setCode(resource.getCode());
			attributeModel.setParentId(resource.getParentId());
			attributeModel.setHref(resource.getHref());
			
			treeModel.setAttributes(attributeModel);
			treeModelList.add(treeModel);
		}
		return treeModelList;
	}
	
	/**
	 * �жϽڵ��Ƿ����ӽڵ�
	 */
	private String isHasNodes(String id){
		if(resourceManager.getResourcesByParentId(id).size() < 1){
			return "open";
		}
		return "closed";
	}
}
