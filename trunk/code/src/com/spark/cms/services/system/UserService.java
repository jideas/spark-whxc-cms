package com.spark.cms.services.system;

import java.util.List;

import com.spark.cms.common.DataModel;
import com.spark.cms.dao.po.UserRole;
import com.spark.cms.services.form.ResourceExtForm;
import com.spark.cms.services.form.UserExtForm;
import com.spark.cms.services.vo.UserVo;

public interface UserService {
	/**
	 * 查询用户列表
	 */
	public DataModel getUsers(String ...args);
	
	/**
	 * 通过脚色
	 */
	public UserExtForm getUserById(String userId);
	
	/**
	 * 增加用户
	 * @return TODO
	 */
	public boolean addUser(UserExtForm userExtForm);
	
	/**
	 * 修改用户
	 * @return TODO
	 */
	public boolean updateUser(UserExtForm userExtForm);
	
	/**
	 * 删除用户
	 */
	public void deleteUser(String  userIds);
	
	/**
	 * 获取角色
	 */
	public List<UserRole> getRolesByUserId(String userId);
	
	/**
	 * 更新用户状态
	 * @param state
	 * @param id
	 */
	public void updateUserState(String state,String ...id);
	
	/**
	 * 指定用户角色
	 * @param userId
	 * @param roleId
	 */
	public void exeAssignRoles(String userId, String[] roleId);
	
	
	/**
	 * 获取用户所有资源信息
	 * @param userId
	 * @return
	 */
	public List<ResourceExtForm> getUserResource(String userId);
	
	/**
	 * 获取登录信息，
	 * 如果成功，返回用户Id，否则返回null;
	 */
	public String getLoginInfo(String loginName,String password);
	
	/**
	 * 获取用户信息
	 * @param loginName
	 * @param password
	 * @return
	 */
	public UserVo getUserInfoByLoginNameAndPassword(String loginName, String password);
	
	/**
	 * 执行重置密码
	 * @param newspassword TODO
	 * @return TODO
	 */
	public boolean exeResetPassWord(String userid,String password, String newspassword);
	
	/**
	 * 检查帐号是否存在
	 * @param id
	 * @param account
	 * @return
	 */
	boolean checkUserAccountExisted(String id, String account);
	
	/**
	 * 检查手机号码是否存在
	 * @param id
	 * @param mobile
	 * @return
	 */
	boolean checkUserMobileExisted(String id, String mobile);
	
	
	/**
	 * 检查email是否存在 
	 * @param id
	 * @param email
	 * @return
	 */
	boolean checkUseEmailExisted(String id, String email);
}
