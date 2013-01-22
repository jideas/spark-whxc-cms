package com.spark.cms.action.system;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spark.cms.action.BaseAction;
import com.spark.cms.common.DataModel;
import com.spark.cms.common.MessageModel;
import com.spark.cms.common.ResponseEntityUtil;
import com.spark.cms.common.tree.TreeModel;
import com.spark.cms.manager.system.RoleManager;
import com.spark.cms.services.form.RoleExtForm;

@Controller
public class RoleAction extends BaseAction {
	
	@Autowired
	private RoleManager roleManager;

	/**
	 * ��ȡ��ɫ�б�
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/role/getRoles")
	@ResponseBody
	public DataModel getRoles(HttpServletRequest requert,
			HttpServletResponse response) {
		try {
			DataModel model = roleManager.getRoles();
			return model;
		} catch (Exception e) {
			log.error("��ȡ��ɫ�����쳣===="+e.getStackTrace());
		}
		return null;
	}
	
	/**
	 * ������ɫ
	 */
	@RequestMapping("/role/addRole")
	@ResponseBody
	public ResponseEntity<MessageModel> addRole(RoleExtForm role){
		try{
			roleManager.addRole(role);
			return ResponseEntityUtil.getResponseEntity(Success);
		}catch (Exception e) {
			log.error("������ɫ�����쳣===="+e.getStackTrace());
			return  null;
		}
	}
		
	/**
	 * �޸Ľ�ɫ
	 */
	@RequestMapping("/role/updateRole")
	@ResponseBody
	public  ResponseEntity<MessageModel>  updateRole(RoleExtForm role){
		try{
			roleManager.updateRole(role);
			return ResponseEntityUtil.getResponseEntity(Success);
		}catch (Exception e) {
			log.error("�޸Ľ�ɫ�����쳣===="+e.getStackTrace());
			return null;
		}
	}
	
	/**
	 * ɾ����ɫ
	 * @param id
	 * @return
	 */
	@RequestMapping("/role/deleteRole")
	@ResponseBody
	public MessageModel deleteRole2(@RequestParam(value = "id", required = true) String id){
		try{
			roleManager.deleteRole(id);
			return Success;
		}catch (Exception e) {
			log.error("ɾ����ɫ�����쳣===="+e.getStackTrace());
			return Failure;
		}
	}
	
	@RequestMapping("/role/getResourceOfRole")
	@ResponseBody
	public List<TreeModel> getResourceOfRole(@RequestParam(value = "roleId", required = false) String roleId,@RequestParam(value = "id", required = false) String resouceId) {
		try {
//			if(StringUtil.isEmpty(resouceId)||"root".equals(resouceId)){
//				TreeModel treeModel = new TreeModel();
//				treeModel.setId("root");
//				treeModel.setText("ϵͳ��Դ");
//				treeModel.setState("open");
//				
//				List<TreeModel> list = new ArrayList<TreeModel>();
//				list.add(treeModel);
//				List<TreeModel> childList = roleManager.getResourceOfRole(roleId);
//				if(childList!=null&&childList.size()>0){
//					treeModel.setChildren(childList.toArray(new TreeModel[childList.size()]));
//				}
//				return list;
//			}else{
//				return roleManager.getResourceOfRole(roleId);
//			}
			
			List<TreeModel> list = new ArrayList<TreeModel>();
			list.add(roleManager.getResourceOfRole(roleId));
			return list;
		} catch (Exception e) {
			log.error("��ȡ��ɫ��Դ�����쳣====" + e.getStackTrace());
		}
		return null;
	}
	
	/**
	 * ��ɫ������Դ
	 */
	@RequestMapping("/role/roleConfigResource")
	@ResponseBody
	public MessageModel roleConfigResouce(@RequestParam(value = "ids[]", required = true) String[] resourceIds,@RequestParam(value = "id", required = true) String roleId){
		try { 
			roleManager.exeAssignResouces(roleId, resourceIds);
			return Success;
		}catch(Exception e){
			log.error("�����ɫ��Դ�����쳣====" + e.getStackTrace());
			return Failure;
		}
	}

}
