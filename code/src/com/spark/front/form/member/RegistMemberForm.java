/**
 * 
 */
package com.spark.front.form.member;

import com.spark.base.common.utils.CheckIsNull;
import com.spark.front.utils.RegEx;
import com.spark.front.utils.CmsString;

/**
 * @author Jideas
 *
 */
public class RegistMemberForm {

	private String email;
	private String mobile;
	private String userName;
	private String password;
	private String invitationcode;
	public String getEmail() {
		return email;
	}
	public String getMobile() {
		return mobile;
	}
	public String getUserName() {
		return userName;
	}
	public String getPassword() {
		return password;
	}
	public String getInvitationcode() {
		return invitationcode;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setInvitationcode(String invitationcode) {
		this.invitationcode = invitationcode;
	}
	
	/**
	 * 判断注册信息是否符合规范
	 * @param form
	 * @return
	 */
	public boolean validate() {
		if (CheckIsNull.isEmpty(this.getEmail())) {
			return false;
		}
		if (CheckIsNull.isEmpty(this.getMobile())) {
			return false;
		}
		if (CheckIsNull.isEmpty(this.getUserName())) {
			return false;
		}
		if (CheckIsNull.isEmpty(this.getPassword())) {
			return false;
		}
		if (!CmsString.matches(RegEx.Email, this.getEmail())) {
			return false;
		}
		if (!CmsString.matches(RegEx.Mobile, this.getMobile())) {
			return false;
		}
		if (!CmsString.matches(RegEx.UserName, this.getUserName())) {
			return false;
		}
		if (!CmsString.matches(RegEx.PassWord, this.getPassword())) {
			return false;
		}
		if (this.getUserName().length() < 8 || this.getUserName().length() > 40) {
			return false;
		}
		if (this.getPassword().length() < 6 || this.getPassword().length() > 16) {
			return false;
		}
		return true;
	}
}
