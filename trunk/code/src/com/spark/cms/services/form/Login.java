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
 * @author Jideas 用户登陆信息
 */
public class Login {

	public Login(UserExtForm admin) throws ServiceMessage {
		if (null == admin) {
			throw new ServiceMessage("请先登录！");
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
			throw new ServiceMessage("请先登录！");
		}
		this.recid = member.getRecid();
		this.name = member.getMembername();
		this.username = CheckIsNull.isEmpty(member.getMembername()) ? member.getUsername() : member.getMembername();
		this.email = member.getEmail();
		this.userType = UserType.Member;
		this.mobile = member.getMobile();
	}

	/**
	 * 标识
	 */
	private String recid;

	/**
	 * 用户类型
	 */
	private UserType userType;

	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 手机号码
	 */
	private String mobile;

	/**
	 * 邮箱
	 */
	private String email;

	/**
	 * 角色列表
	 */
	private List<RoleExtForm> roleList;

	/**
	 * 标识
	 */
	public String getRecid() {
		return recid;
	}

	/**
	 * 用户类型
	 */
	public UserType getUserType() {
		return userType;
	}

	/**
	 * 姓名
	 */
	public String getName() {
		return name;
	}

	/**
	 * 邮箱
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * 用户名
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * 手机号码
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * 角色列表
	 */
	public List<RoleExtForm> getRoleList() {
		return roleList;
	}

}
