/**
 * 
 */
package com.spark.front.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * ϵͳ��������֤�����
 * 
 * @author Jideas
 */
public class CheckCodeManager {

	private static final Map<String, CheckCode> getPassEmailCodes = new HashMap<String, CheckCode>();
	private static final Map<String, CheckCode> getSafeEmailCodes = new HashMap<String, CheckCode>();
	private static final Map<String, CheckCode> getPassMobileCodes = new HashMap<String, CheckCode>();
	private static final Map<String, CheckCode> getSafeMobileCodes = new HashMap<String, CheckCode>();

	/**
	 * �������伤��ŵõ�Ҫ��������Ļ�Աid
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
	 * �������伤����
	 * 
	 * @param code
	 * @param memberId
	 */
	public static final void putEmailSafeCheckCode(String code, String memberId,Object relaObj) {
		getSafeEmailCodes.put(code, new CheckCode(memberId, code, new Date().getTime() + 12*36000000,relaObj));
	}

	/**
	 * �Ƴ����伤����
	 * 
	 * @param code
	 */
	public static final void removeEmailSafeCheckCode(String code) {
		getSafeEmailCodes.remove(code);
	}
	
	/**
	 * �������伤��ŵõ�Ҫ��������Ļ�Աid
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
	 * �������伤����
	 * 
	 * @param code
	 * @param memberId
	 */
	public static final void putEmailPassCheckCode(String code, String memberId,Object relaObj) {
		getPassEmailCodes.put(code, new CheckCode(memberId, code, new Date().getTime() + 12*36000000,relaObj));
	}

	/**
	 * �Ƴ����伤����
	 * 
	 * @param code
	 */
	public static final void removeEmailPassCheckCode(String code) {
		getPassEmailCodes.remove(code);
	}

	/**
	 * ���ݶ��ż���ŵõ�Ҫ��������Ļ�Աid
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
	 * ���ö��ż�����
	 * 
	 * @param code
	 * @param memberId
	 */
	public static final void putMobilePassCheckCode(String code, String memberId,Object relaObj) {
		getPassMobileCodes.put(memberId, new CheckCode(memberId, code, new Date().getTime() + 10800000,relaObj));
	}

	/**
	 * �Ƴ����ż�����
	 * 
	 * @param code
	 */
	public static final void removeMobilePassCheckCode(String memberId) {
		getPassMobileCodes.remove(memberId);
	}
	

	/**
	 * ���ݶ��ż���ŵõ�Ҫ��������Ļ�Աid
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
	 * ���ö��ż�����
	 * 
	 * @param code
	 * @param memberId
	 */
	public static final void putMobileSafeCheckCode(String code, String memberId,Object relaObj) {
		getSafeMobileCodes.put(memberId, new CheckCode(memberId, code, new Date().getTime() + 10800000,relaObj));
	}

	/**
	 * �Ƴ����ż�����
	 * 
	 * @param code
	 */
	public static final void removeMobileSafeCheckCode(String memberId) {
		getSafeMobileCodes.remove(memberId);
	}
}
