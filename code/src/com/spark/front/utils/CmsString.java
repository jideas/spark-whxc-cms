/**
 * 
 */
package com.spark.front.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.spark.base.common.utils.CheckIsNull;
import com.spark.base.common.utils.DoubleUtil;

/**
 * @author Jideas
 * 
 */
public class CmsString extends CheckIsNull {

	/**
	 * �ж��ַ�����������ʽ�Ƿ�ƥ��
	 * 
	 * @param pattern
	 * @param str
	 * @return
	 */
	public static boolean matches(String pattern, String str) {
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(str);
		return m.matches();
	}

	/**
	 * ���ַ������йؼ�����
	 * 
	 * @param str
	 * @return
	 */
	public static String hiddenStr(String str) {
		if (isEmpty(str)) {
			return str;
		}
		StringBuilder ss = new StringBuilder();
		if (matches(RegEx.Mobile, str)) {
			ss.append(str.substring(0, 3));
			ss.append("*****");
			ss.append(str.substring(8, 11));
		} else if (matches(RegEx.Email, str)) {
			String suf = str.substring(str.indexOf("@"));
			str = str.substring(0, str.indexOf("@"));
			ss.append(str.substring(0, 1));
			ss.append("*****");
			ss.append(str.substring(str.length() - 1, str.length()));
			ss.append(suf);
		} else {
			ss.append(str.substring(0, 1));
			ss.append("*****");
			ss.append(str.substring(str.length() - 1, str.length()));
		}
		return ss.toString();
	}

	/**
	 * �õ���Ҫλ������������ַ���
	 * 
	 * @param length
	 * @return
	 */
	public static String getRandomNumStr(int length){
		double i = Math.random();
		String s = DoubleUtil.getRoundStrWithOutQfw(i, length);
		s = s.substring(s.indexOf(".") + 1);
		return s;
	}
}
