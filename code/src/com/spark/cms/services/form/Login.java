/**
 * 
 */
package com.spark.cms.services.form;

import java.util.List;

import com.spark.base.common.utils.CheckIsNull;
import com.spark.cms.base.constant.UserType;
import com.spark.cms.services.ServiceMessage;
import com.spark.cms.services.vo.MemberVo;

/**
 * @author Jideas �û���½��Ϣ
 */
public class Login {

	public Login(UserExtForm admin) throws ServiceMessage {
		if (null == admin) {
			throw new ServiceMessage("���ȵ�¼��");
		}
		this.recid = admin.getId();
		this.name = admin.getName();
		this.mobile = admin.getMobile();
		this.roleList = admin.getRoleExtList();
		this.username = admin.getAccount();
		this.email = admin.getEmail();
		this.userType = UserType.SystemManager;
	}

	public Login(MemberVo member) throws ServiceMessage {
		if (null == member) {
			throw new ServiceMessage("���ȵ�¼��");
		}
		this.recid = member.getRecid();
		this.name = member.getMembername();
		this.username = CheckIsNull.isEmpty(member.getMembername()) ? member.getUsername() : member.getMembername();
		this.email = member.getEmail();
		this.userType = UserType.Member;
		this.mobile = member.getMobile();
	}

	/**
	 * ��ʶ
	 */
	private String recid;

	/**
	 * �û�����
	 */
	private UserType userType;

	/**
	 * ����
	 */
	private String name;
	/**
	 * �û���
	 */
	private String username;
	/**
	 * �ֻ�����
	 */
	private String mobile;

	/**
	 * ����
	 */
	private String email;

	/**
	 * ��ɫ�б�
	 */
	private List<RoleExtForm> roleList;

	/**
	 * ��ʶ
	 */
	public String getRecid() {
		return recid;
	}

	/**
	 * �û�����
	 */
	public UserType getUserType() {
		return userType;
	}

	/**
	 * ����
	 */
	public String getName() {
		return name;
	}

	/**
	 * ����
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * �û���
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * �ֻ�����
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * ��ɫ�б�
	 */
	public List<RoleExtForm> getRoleList() {
		return roleList;
	}

}
