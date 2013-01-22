package com.spark.cms.services.system;

import java.util.List;

import com.spark.cms.common.DataModel;
import com.spark.cms.dao.po.UserRole;
import com.spark.cms.services.form.ResourceExtForm;
import com.spark.cms.services.form.UserExtForm;
import com.spark.cms.services.vo.UserVo;

public interface UserService {
	/**
	 * ��ѯ�û��б�
	 */
	public DataModel getUsers(String ...args);
	
	/**
	 * ͨ����ɫ
	 */
	public UserExtForm getUserById(String userId);
	
	/**
	 * �����û�
	 * @return TODO
	 */
	public boolean addUser(UserExtForm userExtForm);
	
	/**
	 * �޸��û�
	 * @return TODO
	 */
	public boolean updateUser(UserExtForm userExtForm);
	
	/**
	 * ɾ���û�
	 */
	public void deleteUser(String  userIds);
	
	/**
	 * ��ȡ��ɫ
	 */
	public List<UserRole> getRolesByUserId(String userId);
	
	/**
	 * �����û�״̬
	 * @param state
	 * @param id
	 */
	public void updateUserState(String state,String ...id);
	
	/**
	 * ָ���û���ɫ
	 * @param userId
	 * @param roleId
	 */
	public void exeAssignRoles(String userId, String[] roleId);
	
	
	/**
	 * ��ȡ�û�������Դ��Ϣ
	 * @param userId
	 * @return
	 */
	public List<ResourceExtForm> getUserResource(String userId);
	
	/**
	 * ��ȡ��¼��Ϣ��
	 * ����ɹ��������û�Id�����򷵻�null;
	 */
	public String getLoginInfo(String loginName,String password);
	
	/**
	 * ��ȡ�û���Ϣ
	 * @param loginName
	 * @param password
	 * @return
	 */
	public UserVo getUserInfoByLoginNameAndPassword(String loginName, String password);
	
	/**
	 * ִ����������
	 * @param newspassword TODO
	 * @return TODO
	 */
	public boolean exeResetPassWord(String userid,String password, String newspassword);
	
	/**
	 * ����ʺ��Ƿ����
	 * @param id
	 * @param account
	 * @return
	 */
	boolean checkUserAccountExisted(String id, String account);
	
	/**
	 * ����ֻ������Ƿ����
	 * @param id
	 * @param mobile
	 * @return
	 */
	boolean checkUserMobileExisted(String id, String mobile);
	
	
	/**
	 * ���email�Ƿ���� 
	 * @param id
	 * @param email
	 * @return
	 */
	boolean checkUseEmailExisted(String id, String email);
}
