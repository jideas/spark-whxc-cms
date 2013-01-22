package com.spark.cms.manager.system;

import com.spark.cms.common.DataModel;
import com.spark.cms.services.form.UserExtForm;
import com.spark.cms.services.vo.UserVo;

public interface UserManage {
	/**
	 * ��ѯ�û��б�
	 */
	@SuppressWarnings("unchecked")
	public DataModel getUsers(String ...args);
	
	/**
	 * ��ȡ�û�������Ϣ
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
	 * ָ����ɫ
	 */
	public void assignRoles(String userId,String []roleIds);
	
    
	/**
	 * ��ȡ�û����н�ɫ�Ľ�ɫ�б�
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public DataModel getRolesOfUser(String userId);
	
	/**
	 * �����û�������״̬
	 */
	public void setUserState(String state,String ...id);
	
	/**
	 * ��ȡ�û�������Ϣ
	 * @return TODO
	 */
	public UserExtForm getUserInfo(String userId);
	
	
	/**
	 * ��ȡLogin��Ϣ
	 * @param loginName
	 * @param password
	 * @param checkCode
	 * @return TODO
	 */
	public String getLoginInfo(String loginName, String password);
	
	/**
	 * ���ݵ�¼���ƺ����������û���Ϣ
	 * @param loginName
	 * @param password
	 * @return
	 */
	public UserVo getUserInfoByLoginNameAndPassword(String loginName, String password);
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
	
	/**
	 * �޸�����
	 * @param userid
	 * @param password
	 * @param newspassword
	 * @return
	 */
	boolean exeResetPassWord(String userid, String password, String newspassword);
}
