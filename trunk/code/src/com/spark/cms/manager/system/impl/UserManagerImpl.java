package com.spark.cms.manager.system.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spark.cms.common.DataModel;
import com.spark.cms.dao.po.UserRole;
import com.spark.cms.manager.system.UserManage;
import com.spark.cms.services.form.RoleExtForm;
import com.spark.cms.services.form.UserExtForm;
import com.spark.cms.services.system.RoleService;
import com.spark.cms.services.system.UserService;
import com.spark.cms.services.vo.UserVo;

@Component
public class UserManagerImpl implements UserManage{
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;

	@Override
	public boolean addUser(UserExtForm userExtForm) {
		return userService.addUser(userExtForm);
		
	}


	@Override
	public void deleteUser(String userIds) {
		userService.deleteUser(userIds);
	}

	@Override
	public UserExtForm getUserById(String userId) {
		return userService.getUserById(userId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public DataModel getUsers(String... args) {
		
		return userService.getUsers(args);
	}

	@Override
	public boolean updateUser(UserExtForm userExtForm) {
		return userService.updateUser(userExtForm);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public DataModel getRolesOfUser(String userId){
		List<RoleExtForm> roleList =  roleService.getRoles();
		List<UserRole> userRoleList = userService.getRolesByUserId(userId);
		DataModel  mode = new DataModel();
		for(RoleExtForm r:roleList){
			for(UserRole ur:userRoleList){
				if(r.getId().equals(ur.getRoleId())){
					r.setCk(true);
					break;
				}
			}
		}
		mode.setRows(roleList);
		mode.setTotal(roleList.size());
		return mode;
	}
	
	@Override
	public boolean exeResetPassWord(String userid,String password, String newspassword){
		return userService.exeResetPassWord(userid, password, newspassword);
	}
	
	
	@Override
	public void setUserState(String state,String ...id){
		userService.updateUserState(state, id);
	}


	@Override
	public void assignRoles(String userId, String[] roleIds) {
		userService.exeAssignRoles(userId, roleIds);
	}
	
	@Override
	public String getLoginInfo(String loginName,String password){
		return this.userService.getLoginInfo(loginName, password);
	}

	public UserVo getUserInfoByLoginNameAndPassword(String loginName, String password) {
		return userService.getUserInfoByLoginNameAndPassword(loginName, password);
	}
	
	@Override
	public UserExtForm getUserInfo(String userId) {
		UserExtForm userExtForm = userService.getUserById(userId);
		userExtForm.setRoleExtList(getRoleofUser(userId));
		userExtForm.setResourceExtFormsList(userService.getUserResource(userId));
		userExtForm.setResourceTree(roleService.generateResourceTree(userExtForm.getResourceExtFormsList()));
		return userExtForm;
	}
	
	
	private List<RoleExtForm> getRoleofUser(String userId){
		List<RoleExtForm> roleList =  roleService.getRoles();
		List<UserRole> userRoleList = userService.getRolesByUserId(userId);
		List<RoleExtForm> retlist = new ArrayList<RoleExtForm>();
		for(RoleExtForm r:roleList){
			for(UserRole ur:userRoleList){
				if(r.getId().equals(ur.getRoleId())){
					r.setCk(true);
					retlist.add(r);
					break;
				}
			}
		}
	
		return retlist;
	}


	@Override
	public boolean checkUserAccountExisted(String id, String account) {
		return this.userService.checkUserAccountExisted(id, account);
	}


	@Override
	public boolean checkUserMobileExisted(String id, String mobile) {
		return this.userService.checkUserMobileExisted(id, mobile);
	}


	@Override
	public boolean checkUseEmailExisted(String id, String email) {
		return this.userService.checkUseEmailExisted(id, email);
	}
	
	
	
	

}
