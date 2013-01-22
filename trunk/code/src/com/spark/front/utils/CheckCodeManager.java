/**
 * 
 */
package com.spark.front.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 系统激活码验证码管理
 * 
 * @author Jideas
 */
public class CheckCodeManager {

	private static final Map<String, CheckCode> getPassEmailCodes = new HashMap<String, CheckCode>();
	private static final Map<String, CheckCode> getSafeEmailCodes = new HashMap<String, CheckCode>();
	private static final Map<String, CheckCode> getPassMobileCodes = new HashMap<String, CheckCode>();
	private static final Map<String, CheckCode> getSafeMobileCodes = new HashMap<String, CheckCode>();

	/**
	 * 根据邮箱激活号得到要重设密码的会员id
	 * 
	 * @param code
	 * @return
	 */
	public static final CheckCode getEmailSafeMemberId(String code) {
		CheckCode checkCode = getSafeEmailCodes.get(code);
		if(null==checkCode){
			return null;
		}
		if (new Date().getTime() >= checkCode.getTheLastTime()) {
			return null;
		}
		return checkCode;
	}

	/**
	 * 放置邮箱激活码
	 * 
	 * @param code
	 * @param memberId
	 */
	public static final void putEmailSafeCheckCode(String code, String memberId,Object relaObj) {
		getSafeEmailCodes.put(code, new CheckCode(memberId, code, new Date().getTime() + 12*36000000,relaObj));
	}

	/**
	 * 移除邮箱激活码
	 * 
	 * @param code
	 */
	public static final void removeEmailSafeCheckCode(String code) {
		getSafeEmailCodes.remove(code);
	}
	
	/**
	 * 根据邮箱激活号得到要重设密码的会员id
	 * 
	 * @param code
	 * @return
	 */
	public static final CheckCode getEmailPassMemberId(String code) {
		CheckCode checkCode = getPassEmailCodes.get(code);
		if(null==checkCode){
			return null;
		}
		if (new Date().getTime() >= checkCode.getTheLastTime()) {
			return null;
		}
		return checkCode;
	}

	/**
	 * 放置邮箱激活码
	 * 
	 * @param code
	 * @param memberId
	 */
	public static final void putEmailPassCheckCode(String code, String memberId,Object relaObj) {
		getPassEmailCodes.put(code, new CheckCode(memberId, code, new Date().getTime() + 12*36000000,relaObj));
	}

	/**
	 * 移除邮箱激活码
	 * 
	 * @param code
	 */
	public static final void removeEmailPassCheckCode(String code) {
		getPassEmailCodes.remove(code);
	}

	/**
	 * 根据短信激活号得到要重设密码的会员id
	 * 
	 * @param code
	 * @return
	 */
	public static final CheckCode getMobilePassMemberId(String memberId) {
		CheckCode checkCode = getPassMobileCodes.get(memberId);
		if(null==checkCode){
			return null;
		}
		if (new Date().getTime() >= checkCode.getTheLastTime()) {
			return null;
		}
		return checkCode;
	}

	/**
	 * 放置短信激活码
	 * 
	 * @param code
	 * @param memberId
	 */
	public static final void putMobilePassCheckCode(String code, String memberId,Object relaObj) {
		getPassMobileCodes.put(memberId, new CheckCode(memberId, code, new Date().getTime() + 10800000,relaObj));
	}

	/**
	 * 移除短信激活码
	 * 
	 * @param code
	 */
	public static final void removeMobilePassCheckCode(String memberId) {
		getPassMobileCodes.remove(memberId);
	}
	

	/**
	 * 根据短信激活号得到要重设密码的会员id
	 * 
	 * @param code
	 * @return
	 */
	public static final CheckCode getMobileSafeMemberId(String memberId) {
		CheckCode checkCode = getSafeMobileCodes.get(memberId);
		if (new Date().getTime() >= checkCode.getTheLastTime()) {
			return null;
		}
		return checkCode;
	}

	/**
	 * 放置短信激活码
	 * 
	 * @param code
	 * @param memberId
	 */
	public static final void putMobileSafeCheckCode(String code, String memberId,Object relaObj) {
		getSafeMobileCodes.put(memberId, new CheckCode(memberId, code, new Date().getTime() + 10800000,relaObj));
	}

	/**
	 * 移除短信激活码
	 * 
	 * @param code
	 */
	public static final void removeMobileSafeCheckCode(String memberId) {
		getSafeMobileCodes.remove(memberId);
	}
}
