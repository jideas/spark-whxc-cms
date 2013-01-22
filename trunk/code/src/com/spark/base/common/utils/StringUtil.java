package com.spark.base.common.utils;

import java.security.MessageDigest;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * �ַ�����������
 */
public class StringUtil {

	/**
	 * ���캯��
	 */
	public StringUtil() {
	}
	
	/**
	 * ȥ���ַ����еĻس�������
	 * ע��\n �س�(\u000a)
	 *	\t ˮƽ�Ʊ��(\u0009)
	 *	\s �ո�(\u0008)
	 *	\r ����(\u000d)
	 * @param str
	 * @return
	 */
	public static String replaceBlank(String str) {
	   Pattern p = Pattern.compile("\\r|\n");
	   Matcher m = p.matcher(str);
	  return m.replaceAll("");
	}
	// Empty
	// --------------------------------------------------------------------------

	/**
	 * �޳��ַ����еĿ����ַ��Ϳհ��ַ�,����ַ�������Ϊnull���ؿ��ַ���
	 * 
	 * @param str
	 *            �ַ���
	 * @return �������ַ���
	 */
	public static String clean(String str) {
		return (str == null ? "" : str.trim());
	}

	/**
	 * �޳��ַ����еĿ����ַ��Ϳհ��ַ�,����ַ�������Ϊnull����null
	 * 
	 * @param str
	 *            �ַ���
	 * @return �������ַ���
	 */
	public static String trim(String str) {
		return (str == null ? null : str.trim());
	}

	/**
	 * ����հ��ַ����հ��ַ���Character.isWhitespace����
	 * 
	 * @param str
	 *            �ַ���
	 * @return the �������ַ���
	 */
	public static String deleteWhitespace(String str) {
		StringBuffer buffer = new StringBuffer();
		int sz = str.length();
		for (int i = 0; i < sz; i++) {
			if (!Character.isWhitespace(str.charAt(i))) {
				buffer.append(str.charAt(i));
			}
		}
		return buffer.toString();
	}

	/**
	 * ����ַ��������Ƿ�Ϊnull�����ַ�����Ϊ�մ�
	 * 
	 * @param str
	 *            �ַ���
	 * @return true ����ַ�������Ϊnull�����ַ�����Ϊ�մ�
	 */
	public static boolean isNotEmpty(String str) {
		return (str != null && str.length() > 0);
	}

	/**
	 * ����ַ��������Ƿ�Ϊnull���ַ���Ϊ�մ�
	 * 
	 * @param str
	 *            �ַ���
	 * @return true ����ַ�������Ϊnull���ַ���Ϊ�մ�
	 */
	public static boolean isEmpty(String str) {
		return (str == null || str.trim().length() == 0);
	}

	// Equals and IndexOf
	// --------------------------------------------------------------------------

	/**
	 * �Ƚ����ַ��������Ƿ���ͬ
	 * 
	 * @param str1
	 *            �ַ���
	 * @param str2
	 *            �ַ���
	 * @return true ����ַ�����������������ַ�������Ϊnull
	 */
	public static boolean equals(String str1, String str2) {
		return (str1 == null ? str2 == null : str1.equals(str2));
	}

	/**
	 * �Ƚ����ַ��������Ƿ���ͬ(���Դ�Сд)
	 * 
	 * @param str1
	 *            �ַ���
	 * @param str2
	 *            �ַ���
	 * @return true ����ַ�����������������ַ�������Ϊnull
	 */
	public static boolean equalsIgnoreCase(String str1, String str2) {
		return (str1 == null ? str2 == null : str1.equalsIgnoreCase(str2));
	}

	/**
	 * ���ҳ���ָ�������������ַ����������λ��
	 * 
	 * @param str
	 *            �ַ���
	 * @param searchStrs
	 *            Ҫƥ����ҵ��ַ�������
	 * @return str�������searchStrs�������ַ�����λ��
	 */
	public static int indexOfAny(String str, String[] searchStrs) {
		if ((str == null) || (searchStrs == null)) {
			return -1;
		}
		int sz = searchStrs.length;

		// String's can't have a MAX_VALUEth index.
		int ret = Integer.MAX_VALUE;

		int tmp = 0;
		for (int i = 0; i < sz; i++) {
			tmp = str.indexOf(searchStrs[i]);
			if (tmp == -1) {
				continue;
			}

			if (tmp < ret) {
				ret = tmp;
			}
		}

		return (ret == Integer.MAX_VALUE) ? -1 : ret;
	}

	/**
	 * ���ҳ���ָ�������������ַ����������λ��
	 * 
	 * @param str
	 *            �ַ���
	 * @param searchStrs
	 *            Ҫƥ����ҵ��ַ�������
	 * @return str�������searchStrs�������ַ�����λ��
	 */
	public static int lastIndexOfAny(String str, String[] searchStrs) {
		if ((str == null) || (searchStrs == null)) {
			return -1;
		}
		int sz = searchStrs.length;
		int ret = -1;
		int tmp = 0;
		for (int i = 0; i < sz; i++) {
			tmp = str.lastIndexOf(searchStrs[i]);
			if (tmp > ret) {
				ret = tmp;
			}
		}
		return ret;
	}

	// Substring
	// --------------------------------------------------------------------------

	/**
	 * ��ȡĳ�ַ���ָ��λ�ÿ�ʼ���Ӵ��������ʼλ��Ϊ����(pos)����ȡ��λ��Ϊstr.length() + pos
	 * 
	 * @param str
	 *            �ַ���
	 * @param start
	 *            ��ʼ��ȡ�Ӵ���λ��
	 * @return �Ӵ�
	 */
	public static String substring(String str, int start) {
		if (str == null) {
			return null;
		}

		// handle negatives, which means last n characters
		if (start < 0) {
			start = str.length() + start; // remember start is negative
		}

		if (start < 0) {
			start = 0;
		}
		if (start > str.length()) {
			return "";
		}

		return str.substring(start);
	}

	/**
	 * ��ȡĳ�ַ���ָ��λ�õ��Ӵ��������ʼ�����λ��Ϊ����(pos)����ȡ��λ��Ϊstr.length() + pos�������ֵ��ȻΪ�������0��ʼ��
	 * ����λ�ô����ַ������ȣ�����λ�ö����ַ���ĩβ�������ʼλ�ô��ڽ���λ�ã����ؿ��ַ���
	 * 
	 * @param str
	 *            �ַ���
	 * @param start
	 *            ��ʼλ��
	 * @param end
	 *            ����λ��
	 * @return �Ӵ�
	 */
	public static String substring(String str, int start, int end) {
		if (str == null) {
			return null;
		}

		// handle negatives
		if (end < 0) {
			end = str.length() + end; // remember end is negative
		}
		if (start < 0) {
			start = str.length() + start; // remember start is negative
		}

		// check length next
		if (end > str.length()) {
			// check this works.
			end = str.length();
		}

		// if start is greater than end, return ""
		if (start > end) {
			return "";
		}

		if (start < 0) {
			start = 0;
		}
		if (end < 0) {
			end = 0;
		}

		return str.substring(start, end);
	}

	/**
	 * ��ȡ�ַ����ӿ�ʼλ������ָ�����ȵ��ַ����������ȡ���ȴ����ַ������ȣ����������ַ���
	 * 
	 * @param str
	 *            �ַ���
	 * @param len
	 *            ��ȡ����
	 * @return ��ȡ���ַ���
	 */
	public static String left(String str, int len) {
		if (len < 0) {
			throw new IllegalArgumentException("Requested String length " + len + " is less than zero");
		}
		if ((str == null) || (str.length() <= len)) {
			return str;
		} else {
			return str.substring(0, len);
		}
	}

	/**
	 * ��ȡ�ַ����ӽ���λ��������ǰָ�����ȵ��ַ����������ȡ���ȴ����ַ������ȣ����������ַ���
	 * 
	 * @param str
	 *            �ַ���
	 * @param len
	 *            ��ȡ����
	 * @return ��ȡ���ַ���
	 */
	public static String right(String str, int len) {
		if (len < 0) {
			throw new IllegalArgumentException("Requested String length " + len + " is less than zero");
		}
		if ((str == null) || (str.length() <= len)) {
			return str;
		} else {
			return str.substring(str.length() - len);
		}
	}

	/**
	 * ��ȡ�ַ���ָ��λ������ָ�����ȵ��ַ����������ȡ��Χ���������ַ��������������ַ���
	 * 
	 * @param str
	 *            �ַ���
	 * @param pos
	 *            ��ʼ��ȡ��λ��
	 * @param len
	 *            ��ȡ����
	 * @return ��ȡ���ַ���
	 */
	public static String mid(String str, int pos, int len) {
		if ((pos < 0) || (str != null && pos > str.length())) {
			throw new StringIndexOutOfBoundsException("String index " + pos + " is out of bounds");
		}
		if (len < 0) {
			throw new IllegalArgumentException("Requested String length " + len + " is less than zero");
		}
		if (str == null) {
			return null;
		}
		if (str.length() <= (pos + len)) {
			return str.substring(pos);
		} else {
			return str.substring(pos, pos + len);
		}
	}

	// Splitting
	// --------------------------------------------------------------------------

	/**
	 * Ĭ�Ϸָ��������ַ���
	 * 
	 * @param str
	 *            Ҫ�������ַ���
	 * @return ��������ַ�������
	 */
	public static String[] split(String str) {
		return split(str, null, -1);
	}

	/**
	 * ָ���ָ��������ָ��ַ���
	 * 
	 * @param text
	 *            Ҫ�������ַ���
	 * @param separator
	 *            �ָ���
	 * @return ��������ַ�������
	 */
	public static String[] split(String text, String separator) {
		return split(text, separator, -1);
	}

	/**
	 * ָ���ָ��������ַ��������ܿ��Ʒ����������󳤶�
	 * 
	 * @param str
	 *            �ַ���
	 * @param separator
	 *            �ָ��������Ϊnull,�ָ���ΪStringTokenizerĬ�ϵĿհ׷�
	 * @param max
	 *            �����󳤶ȣ�������0��ʾ��Լ��
	 * @return ������õ�������
	 */
	public static String[] split(String str, String separator, int max) {
		StringTokenizer tok = null;
		if (separator == null) {
			// Null separator means we're using StringTokenizer's default
			// delimiter, which comprises all whitespace characters.
			tok = new StringTokenizer(str);
		} else {
			tok = new StringTokenizer(str, separator);
		}

		int listSize = tok.countTokens();
		if (max > 0 && listSize > max) {
			listSize = max;
		}

		String[] list = new String[listSize];
		int i = 0;
		int lastTokenBegin = 0;
		int lastTokenEnd = 0;
		while (tok.hasMoreTokens()) {
			if (max > 0 && i == listSize - 1) {
				// In the situation where we hit the max yet have
				// tokens left over in our input, the last list
				// element gets all remaining text.
				String endToken = tok.nextToken();
				lastTokenBegin = str.indexOf(endToken, lastTokenEnd);
				list[i] = str.substring(lastTokenBegin);
				break;
			} else {
				list[i] = tok.nextToken();
				lastTokenBegin = str.indexOf(list[i], lastTokenEnd);
				lastTokenEnd = lastTokenBegin + list[i].length();
			}
			i++;
		}
		return list;
	}

	// Joining
	// --------------------------------------------------------------------------
	/**
	 * �����鼯���еĶ�����ϳ��ַ���
	 * 
	 * @param array
	 *            ���鼯�ϣ�����Ҫ��ϳ��ַ����ĸ���Ԫ��
	 * @return ��Ϻ���ַ���
	 */
	public static String concatenate(Object[] array) {
		return join(array, "");
	}

	/**
	 * �����鼯���еĶ�����ϳ��ַ����������е�Ԫ�ؽ���ָ���ķָ���ָ�
	 * 
	 * @param array
	 *            ���鼯�ϣ�����Ҫ��ϳ��ַ����ĸ���Ԫ��
	 * @param separator
	 *            �ָ���
	 * @return ��Ϻ���ַ���
	 */
	public static String join(Object[] array, String separator) {
		if (separator == null) {
			separator = "";
		}
		int arraySize = array.length;
		int bufSize = (arraySize == 0 ? 0 : (array[0].toString().length() + separator.length()) * arraySize);
		StringBuffer buf = new StringBuffer(bufSize);

		for (int i = 0; i < arraySize; i++) {
			if (i > 0) {
				buf.append(separator);
			}
			buf.append(array[i]);
		}
		return buf.toString();
	}

	/**
	 * �������еĶ�����ϳ��ַ������������󽫱�ָ���ָ����ָ�
	 * 
	 * @param iterator
	 *            Iterator����,����Ҫ��ϳ��ַ����ĸ���Ԫ��
	 * @param separator
	 *            �ָ���
	 * @return ��Ϻ���ַ���
	 */ 
	@SuppressWarnings("unchecked")
	public static String join(Iterator iterator, String separator) {
		if (separator == null) {
			separator = "";
		}
		StringBuffer buf = new StringBuffer(256); // Java default is 16,
													// probably too small
		while (iterator.hasNext()) {
			buf.append(iterator.next());
			if (iterator.hasNext()) {
				buf.append(separator);
			}
		}
		return buf.toString();
	}

	// Replacing
	// --------------------------------------------------------------------------

	/**
	 * �����ַ����滻ĸ�ַ����е�ָ���Ӵ����滻����ִ��һ��
	 * 
	 * @param text
	 *            ��Ҫ���滻���Ӵ���ĸ�ַ���
	 * @param repl
	 *            ��Ҫ���滻�����ַ���
	 * @param with
	 *            �����滻���ַ���
	 * @return �滻��������ַ���
	 */
	public static String replaceOnce(String text, String repl, String with) {
		return replace(text, repl, with, 1);
	}

	/**
	 * �����ַ����滻ĸ�ַ���������ָ�����ַ���
	 * 
	 * @param text
	 *            ��Ҫ���滻���Ӵ���ĸ�ַ���
	 * @param repl
	 *            ��Ҫ���滻�����ַ���
	 * @param with
	 *            �����滻���ַ���
	 * @return �滻��������ַ���
	 */
	public static String replace(String text, String repl, String with) {
		return replace(text, repl, with, -1);
	}

	/**
	 * �����ַ����滻ĸ�ַ����е����ַ�������ָ������滻������ with�滻text�е��ַ���repl,�滻�������Ϊmax��
	 * 
	 * @param text
	 *            ��Ҫ���滻���Ӵ���ĸ�ַ���
	 * @param repl
	 *            ��Ҫ���滻�����ַ���
	 * @param with
	 *            �����滻���ַ���
	 * @param max
	 *            �滻����������������ʾû���������
	 * @return �滻��������ַ���
	 */
	public static String replace(String text, String repl, String with, int max) {
		if (text == null) {
			return null;
		}

		StringBuffer buf = new StringBuffer(text.length());
		int start = 0, end = 0;
		while ((end = text.indexOf(repl, start)) != -1) {
			buf.append(text.substring(start, end)).append(with);
			start = end + repl.length();

			if (--max == 0) {
				break;
			}
		}
		buf.append(text.substring(start));
		return buf.toString();
	}

	/**
	 * ���ַ�����ĳ����������ַ���
	 * 
	 * @param text
	 *            ������Ҫ���滻���Ӵ���ĸ�ַ���
	 * @param overlay
	 *            �����滻���ַ���
	 * @param start
	 *            �滻�Ŀ�ʼλ��
	 * @param end
	 *            �滻�Ľ���λ��
	 * @return String �滻��������ַ���
	 */
	public static String overlayString(String text, String overlay, int start, int end) {
		return new StringBuffer(start + overlay.length() + text.length() - end + 1)
				.append(text.substring(0, start)).append(overlay).append(text.substring(end)).toString();
	}

	// Centering
	// --------------------------------------------------------------------------

	/**
	 * ���ַ���ǰ���ظ��ָ�����ַ�����������ĸ���һ�£������ַ�����󳤶�Ϊsize
	 * 
	 * @param str
	 *            ��������ַ���
	 * @param size
	 *            ���ַ�������󳤶�
	 * @return String �����ַ���
	 */
	public static String center(String str, int size) {
		return center(str, size, " ");
	}

	/**
	 * ���ַ���ǰ���ظ��ָ���ַ�����������ĸ���һ�£������ַ�����󳤶�Ϊsize
	 * 
	 * @param str
	 *            ��������ַ���
	 * @param size
	 *            ���ַ�������󳤶�
	 * @param delim
	 *            ��������ַ���
	 * @return String �����ַ���
	 */
	public static String center(String str, int size, String delim) {
		int sz = str.length();
		int p = size - sz;
		if (p < 1) {
			return str;
		}
		str = leftPad(str, sz + p / 2, delim);
		str = rightPad(str, size, delim);
		return str;
	}

	// Chomping
	// --------------------------------------------------------------------------

	/**
	 * ��ȡ�ַ������һ�е�ǰ�沿�֣���ȥ���ַ��������һ��
	 * 
	 * @param str
	 *            ������ȡ���ַ���
	 * @return String ��ȡ����ַ���
	 */
	public static String chomp(String str) {
		return chomp(str, "\n");
	}

	/**
	 * ��ȡ�ַ�������ȡλ�ô�str������sep��λ�ÿ�ʼ��str�ַ���������
	 * 
	 * @param str
	 *            ������ȡ���ַ���
	 * @param sep
	 *            ��ȡ���Ӵ�
	 * @return String ��ȡ����ַ���
	 */
	public static String chomp(String str, String sep) {
		int idx = str.lastIndexOf(sep);
		if (idx != -1) {
			return str.substring(0, idx);
		} else {
			return str;
		}
	}

	/**
	 * ȥ���ַ�����β���Ļ��з�
	 * 
	 * @param str
	 *            �ַ���
	 * @return String strȥ����β�����з�����ַ���
	 */
	public static String chompLast(String str) {
		return chompLast(str, "\n");
	}

	/**
	 * ��ĸ�ַ�����ָ�����Ӵ���βʱ����ĸ����ȥ�����Ӵ�
	 * 
	 * @param str
	 *            String ĸ�ַ���
	 * @param sep
	 *            String ���ַ���
	 * @return String ȥ����������ַ���
	 */
	public static String chompLast(String str, String sep) {
		if (str.length() == 0) {
			return str;
		}
		String sub = str.substring(str.length() - sep.length());
		if (sep.equals(sub)) {
			return str.substring(0, str.length() - sep.length());
		} else {
			return str;
		}
	}

	/**
	 * ��ȡ�ַ���str����ȡλ�ô�str������sep��λ�õ�str.length-1
	 * 
	 * @param str
	 *            ĸ�ַ���
	 * @param sep
	 *            �Ӵ�
	 * @return String ��ȡ���ַ���
	 */
	public static String getChomp(String str, String sep) {
		int idx = str.lastIndexOf(sep);
		if (idx == str.length() - sep.length()) {
			return sep;
		} else if (idx != -1) {
			return str.substring(idx);
		} else {
			return "";
		}
	}

	/**
	 * ��ȡ�ַ���str����ȡλ�ô�str��һ�γ���sep��λ��+sep.length,��str.length-1
	 * 
	 * @param str
	 *            ĸ�ַ���
	 * @param sep
	 *            �Ӵ�
	 * @return ��ȡ����ַ���
	 */
	public static String prechomp(String str, String sep) {
		int idx = str.indexOf(sep);
		if (idx != -1) {
			return str.substring(idx + sep.length());
		} else {
			return str;
		}
	}

	/**
	 * ��ȡ�ַ���str����ȡλ�ô�0��str��һ�γ���sep��λ��+sep.length
	 * 
	 * @param str
	 *            ĸ�ַ���
	 * @param sep
	 *            �Ӵ�
	 * @return String ��ȡ����ַ���
	 */
	public static String getPrechomp(String str, String sep) {
		int idx = str.indexOf(sep);
		if (idx != -1) {
			return str.substring(0, idx + sep.length());
		} else {
			return "";
		}
	}

	// Chopping
	// --------------------------------------------------------------------------

	/**
	 * ȥ���ַ����е����һ���ַ�������ַ�����\r\n�������������ַ�������ȥ��
	 * 
	 * @param str
	 *            String ĸ�ַ���
	 * @return String ȥ����������ַ���
	 */
	public static String chop(String str) {
		if ("".equals(str)) {
			return "";
		}
		if (str.length() == 1) {
			return "";
		}
		int lastIdx = str.length() - 1;
		String ret = str.substring(0, lastIdx);
		char last = str.charAt(lastIdx);
		if (last == '\n') {
			if (ret.charAt(lastIdx - 1) == '\r') {
				return ret.substring(0, lastIdx - 1);
			}
		}
		return ret;
	}

	/**
	 * ȥ���ַ�������\n�ַ�������ַ�����\r\n�������������ַ�������ȥ��
	 * 
	 * @param str
	 *            String to chop a newline from
	 * @return String without newline
	 * @throws NullPointerException
	 *             if str is null
	 */
	public static String chopNewline(String str) {
		int lastIdx = str.length() - 1;
		char last = str.charAt(lastIdx);
		if (last == '\n') {
			if (str.charAt(lastIdx - 1) == '\r') {
				lastIdx--;
			}
		} else {
			lastIdx++;
		}
		return str.substring(0, lastIdx);
	}

	// Conversion
	// --------------------------------------------------------------------------

	// spec 3.10.6
	/**
	 * ���ַ����еĿ����ַ�ת��Ϊת���ַ�������just\nת��Ϊjust\\n
	 * 
	 * @param str
	 *            Ҫ���д�����ַ���
	 * @return String ת������ַ���
	 */
	public static String escape(String str) {
		// improved with code from cybertiger@cyberiantiger.org
		// unicode from him, and defaul for < 32's.
		int sz = str.length();
		StringBuffer buffer = new StringBuffer(2 * sz);
		for (int i = 0; i < sz; i++) {
			char ch = str.charAt(i);

			// handle unicode
			if (ch > 0xfff) {
				buffer.append("\\u" + Integer.toHexString(ch));
			} else if (ch > 0xff) {
				buffer.append("\\u0" + Integer.toHexString(ch));
			} else if (ch > 0x7f) {
				buffer.append("\\u00" + Integer.toHexString(ch));
			} else if (ch < 32) {
				switch (ch) {
				case '\b':
					buffer.append('\\');
					buffer.append('b');
					break;
				case '\n':
					buffer.append('\\');
					buffer.append('n');
					break;
				case '\t':
					buffer.append('\\');
					buffer.append('t');
					break;
				case '\f':
					buffer.append('\\');
					buffer.append('f');
					break;
				case '\r':
					buffer.append('\\');
					buffer.append('r');
					break;
				default:
					if (ch > 0xf) {
						buffer.append("\\u00" + Integer.toHexString(ch));
					} else {
						buffer.append("\\u000" + Integer.toHexString(ch));
					}
					break;
				}
			} else {
				switch (ch) {
				case '\'':
					buffer.append('\\');
					buffer.append('\'');
					break;
				case '"':
					buffer.append('\\');
					buffer.append('"');
					break;
				case '\\':
					buffer.append('\\');
					buffer.append('\\');
					break;
				default:
					buffer.append(ch);
					break;
				}
			}
		}
		return buffer.toString();
	}

	// Padding
	// --------------------------------------------------------------------------

	/**
	 * n����ͬ�ַ�����ϳ�һ�����ַ���
	 * 
	 * @param str
	 *            String Ҫ��ϵ��ַ���
	 * @param repeat
	 *            int �ظ�����
	 * @return String ��Ϻ���ַ���
	 */
	public static String repeat(String str, int repeat) {
		StringBuffer buffer = new StringBuffer(repeat * str.length());
		for (int i = 0; i < repeat; i++) {
			buffer.append(str);
		}
		return buffer.toString();
	}

	/**
	 * ���ַ�������ո������ַ�������Ϊsize
	 * 
	 * @param str
	 *            String Ҫ������������ַ���
	 * @param size
	 *            int �ַ������ĳ���
	 * @return right ����ַ���
	 */
	public static String rightPad(String str, int size) {
		return rightPad(str, size, " ");
	}

	/**
	 * ���ַ��������һ���ַ����������ַ�������Ϊsize
	 * 
	 * @param str
	 *            String Ҫ������������ַ���
	 * @param size
	 *            int �ַ������ĳ���
	 * @param delim
	 *            String ����ַ���
	 * @return �����ַ���
	 */
	public static String rightPad(String str, int size, String delim) {
		size = (size - str.length()) / delim.length();
		if (size > 0) {
			str += repeat(delim, size);
		}
		return str;
	}

	/**
	 * ���ַ���ǰ�ظ���ո������ַ�������Ϊsize
	 * 
	 * @param str
	 *            String Ҫ������������ַ���
	 * @param size
	 *            int �ַ������ĳ���
	 * @return �����ַ���
	 */
	public static String leftPad(String str, int size) {
		return leftPad(str, size, " ");
	}

	/**
	 * ���ַ���ǰ�ظ����һ���ַ����������ַ�������Ϊsize
	 * 
	 * @param str
	 *            String Ҫ������������ַ���
	 * @param size
	 *            int �ַ������ĳ���
	 * @param delim
	 *            String ����ַ���
	 * @return �����ַ���
	 */
	public static String leftPad(String str, int size, String delim) {
		size = (size - str.length()) / delim.length();
		if (size > 0) {
			str = repeat(delim, size) + str;
		}
		return str;
	}

	// Stripping
	// --------------------------------------------------------------------------

	/**
	 * �޳���β�Ŀ��ַ����ÿ��ַ����ж���Character.isWhitespace����
	 * 
	 * @param str
	 *            �����޳��������ַ���
	 * @return �޳���������ַ���
	 */
	public static String strip(String str) {
		return strip(str, null);
	}

	/**
	 * ���ַ���str��ʼ�ͽ���λ�ø��޳�һ���ַ������޳����ַ�����ÿ���ַ�����������delim�� �磺strip("1234","134")="2"
	 * 
	 * @param str
	 *            �����޳��������ַ���
	 * @param delim
	 *            ����Ҫ�޳����ַ�
	 * @return �޳���������ַ���
	 */
	public static String strip(String str, String delim) {
		str = stripStart(str, delim);
		return stripEnd(str, delim);
	}

	/**
	 * �޳��ַ���������ÿ���ַ�����β�Ŀ��ַ����ÿ��ַ����ж���Character.isWhitespace����
	 * 
	 * @param strs
	 *            �����޳��������ַ�������
	 * @return �޳���������ַ�������
	 */
	public static String[] stripAll(String[] strs) {
		return stripAll(strs, null);
	}

	/**
	 * �ַ���������ÿ���ַ�����β�޳�һ���ַ������޳����ַ�����ÿ���ַ�����������delimiter��
	 * 
	 * @param strs
	 *            �����޳��������ַ�������
	 * @param delimiter
	 *            ����Ҫ�޳����ַ�
	 * @return �޳���������ַ�������
	 */
	public static String[] stripAll(String[] strs, String delimiter) {
		if ((strs == null) || (strs.length == 0)) {
			return strs;
		}
		int sz = strs.length;
		String[] newArr = new String[sz];
		for (int i = 0; i < sz; i++) {
			newArr[i] = strip(strs[i], delimiter);
		}
		return newArr;
	}

	/**
	 * ���ַ���str����λ���޳�һ���ַ������޳����ַ�����ÿ���ַ�����������strip�� �磺stripEnd("1234","34")="12"
	 * 
	 * @param str
	 *            �����޳��������ַ���
	 * @param strip
	 *            ����Ҫ�޳����ַ�
	 * @return �޳���������ַ���
	 */
	public static String stripEnd(String str, String strip) {
		if (str == null) {
			return null;
		}
		int end = str.length();

		if (strip == null) {
			while ((end != 0) && Character.isWhitespace(str.charAt(end - 1))) {
				end--;
			}
		} else {
			while ((end != 0) && (strip.indexOf(str.charAt(end - 1)) != -1)) {
				end--;
			}
		}
		return str.substring(0, end);
	}

	/**
	 * ���ַ���str��ʼλ���޳�һ���ַ������޳����ַ�����ÿ���ַ�����������strip�У�
	 * �磺stripStart("1234","13")="234"
	 * 
	 * @param str
	 *            �����޳��������ַ���
	 * @param strip
	 *            ����Ҫ�޳����ַ�
	 * @return �޳���������ַ���
	 */
	public static String stripStart(String str, String strip) {
		if (str == null) {
			return null;
		}

		int start = 0;

		int sz = str.length();

		if (strip == null) {
			while ((start != sz) && Character.isWhitespace(str.charAt(start))) {
				start++;
			}
		} else {
			while ((start != sz) && (strip.indexOf(str.charAt(start)) != -1)) {
				start++;
			}
		}
		return str.substring(start);
	}

	// Case conversion
	// --------------------------------------------------------------------------

	/**
	 * ���ַ���ת��Ϊ��д
	 * 
	 * @param str
	 *            Ҫת�����ַ���
	 * @return the ת������ַ���
	 */
	public static String upperCase(String str) {
		if (str == null) {
			return null;
		}
		return str.toUpperCase();
	}

	/**
	 * ���ַ���ת��ΪСд
	 * 
	 * @param str
	 *            Ҫת�����ַ���
	 * @return ת������ַ���
	 */
	public static String lowerCase(String str) {
		if (str == null) {
			return null;
		}
		return str.toLowerCase();
	}

	/**
	 * ���ַ����ĵ�һ����ĸת��ΪСд
	 * 
	 * @param str
	 *            Ҫת�����ַ���
	 * @return ת������ַ���
	 */
	public static String uncapitalise(String str) {
		if (str == null) {
			return null;
		}
		if (str.length() == 0) {
			return "";
		}
		return new StringBuffer(str.length()).append(Character.toLowerCase(str.charAt(0)))
				.append(str.substring(1)).toString();
	}

	/**
	 * ���ַ����ĵ�һ����ĸת��Ϊ��д
	 * 
	 * @param str
	 *            ��Ҫת�����ַ���
	 * @return ת������ַ���
	 */
	public static String capitalise(String str) {
		if (str == null) {
			return null;
		}
		if (str.length() == 0) {
			return "";
		}
		return new StringBuffer(str.length()).append(Character.toTitleCase(str.charAt(0)))
				.append(str.substring(1)).toString();
	}

	/**
	 * ���ַ����е���ĸ��Сд�������磺 swapCase("a B")="A b"; swapCase("a b")="A B";
	 * 
	 * @param str
	 *            Ҫת�����ַ���
	 * @return ת������ַ���
	 */
	public static String swapCase(String str) {
		if (str == null) {
			return null;
		}
		int sz = str.length();
		StringBuffer buffer = new StringBuffer(sz);

		boolean whitespace = false;
		char ch = 0;
		char tmp = 0;

		for (int i = 0; i < sz; i++) {
			ch = str.charAt(i);
			if (Character.isUpperCase(ch)) {
				tmp = Character.toLowerCase(ch);
			} else if (Character.isTitleCase(ch)) {
				tmp = Character.toLowerCase(ch);
			} else if (Character.isLowerCase(ch)) {
				if (whitespace) {
					tmp = Character.toTitleCase(ch);
				} else {
					tmp = Character.toUpperCase(ch);
				}
			} else {
				tmp = ch;
			}
			buffer.append(tmp);
			whitespace = Character.isWhitespace(ch);
		}
		return buffer.toString();
	}

	/**
	 * ���ַ������ַ��Ϳհ׷�������ַ�ת��Ϊ��д���磺 capitaliseAllWords("af bb c")="Af Bb C";
	 * 
	 * @param str
	 *            ��Ҫת�����ַ���
	 * @return ת������ַ���
	 */
	public static String capitaliseAllWords(String str) {
		if (str == null) {
			return null;
		}
		int sz = str.length();
		StringBuffer buffer = new StringBuffer(sz);
		boolean space = true;
		for (int i = 0; i < sz; i++) {
			char ch = str.charAt(i);
			if (Character.isWhitespace(ch)) {
				buffer.append(ch);
				space = true;
			} else if (space) {
				buffer.append(Character.toTitleCase(ch));
				space = false;
			} else {
				buffer.append(ch);
			}
		}
		return buffer.toString();
	}

	// Nested extraction
	// --------------------------------------------------------------------------

	/**
	 * ��ĸ�ַ�����ȡ��������ͬ�Ӵ�֮����ַ������磺 getNestedString("afbac","a")="fb";
	 * 
	 * @param str
	 *            ĸ�ַ���
	 * @param tag
	 *            �Ӵ������Խ�ȡλ�ö�λ
	 * @return str������tag����Ӵ�
	 */
	public static String getNestedString(String str, String tag) {
		return getNestedString(str, tag, tag);
	}

	/**
	 * ��ĸ�ַ�����ȡ�������Ӵ�֮����Ӵ����磺 getNestedString("afbbc","f","c")="bb";
	 * 
	 * @param str
	 *            ĸ�ַ���
	 * @param open
	 *            ���ַ���1
	 * @param close
	 *            ���ַ���2
	 * @return ���ַ���1�����ַ���2֮����Ӵ�
	 */
	public static String getNestedString(String str, String open, String close) {
		if (str == null) {
			return null;
		}
		int start = str.indexOf(open);
		if (start != -1) {
			int end = str.indexOf(close, start + open.length());
			if (end != -1) {
				return str.substring(start + open.length(), end);
			}
		}
		return null;
	}

	/**
	 * �����Ӵ���ĸ���г��ֵĴ���
	 * 
	 * @param str
	 *            ĸ��
	 * @param sub
	 *            �Ӵ�
	 * @return �Ӵ�sub��ĸ��str�г��ֵĴ���
	 */
	public static int countMatches(String str, String sub) {
		if (str == null) {
			return 0;
		}
		int count = 0;
		int idx = 0;
		while ((idx = str.indexOf(sub, idx)) != -1) {
			count++;
			idx += sub.length();
		}
		return count;
	}

	// Character Tests
	// --------------------------------------------------------------------------

	/**
	 * ����ַ������Ƿ�ȫ����ĸ
	 * 
	 * @param str
	 *            Ҫ���м����ַ���
	 * @return true ȫ����ĸ false ��ȫ����ĸ
	 */
	public static boolean isAlpha(String str) {
		if (str == null) {
			return false;
		}
		int sz = str.length();
		for (int i = 0; i < sz; i++) {
			if (Character.isLetter(str.charAt(i)) == false) {
				return false;
			}
		}
		return true;
	}

	/**
	 * ����ַ������Ƿ�ȫ����ĸ���ǿո�
	 * 
	 * @param str
	 *            Ҫ���м����ַ���
	 * @return true ȫ����ĸ��ո� false ��ȫ����ĸ��ո�
	 */
	public static boolean isAlphaSpace(String str) {
		if (str == null) {
			return false;
		}
		int sz = str.length();
		for (int i = 0; i < sz; i++) {
			if ((Character.isLetter(str.charAt(i)) == false) && (str.charAt(i) != ' ')) {
				return false;
			}
		}
		return true;
	}

	/**
	 * ����ַ������Ƿ�ȫ����ĸ������
	 * 
	 * @param str
	 *            Ҫ���м����ַ���
	 * @return true ȫ����ĸ������ false ��ȫ����ĸ������
	 */
	public static boolean isAlphanumeric(String str) {
		if (str == null) {
			return false;
		}
		int sz = str.length();
		for (int i = 0; i < sz; i++) {
			if (Character.isLetterOrDigit(str.charAt(i)) == false) {
				return false;
			}
		}
		return true;
	}

	/**
	 * ����ַ������Ƿ�ȫ����ĸ�����ֻ�ո�
	 * 
	 * @param str
	 *            Ҫ���м����ַ���
	 * @return true ȫ����ĸ�����ֻ�ո� false ��ȫ����ĸ�����ֻ�ո�
	 */
	public static boolean isAlphanumericSpace(String str) {
		if (str == null) {
			return false;
		}
		int sz = str.length();
		for (int i = 0; i < sz; i++) {
			if ((Character.isLetterOrDigit(str.charAt(i)) == false) && (str.charAt(i) != ' ')) {
				return false;
			}
		}
		return true;
	}

	/**
	 * ����ַ������Ƿ�ȫ�����֣� �ַ�������ΪNull������false,���ַ�������true.
	 * 
	 * @param str
	 *            Ҫ���м����ַ���
	 * @return true ȫ������ false ��ȫ������
	 */
	public static boolean isNumeric(String str) {
		if (StringUtil.isEmpty(str)) {
			return false;
		}
		int sz = str.length();
		for (int i = 0; i < sz; i++) {
			if (Character.isDigit(str.charAt(i)) == false) {
				return false;
			}
		}
		return true;
	}

	/**
	 * ����ַ������Ƿ�ȫ�����ֻ�ո� �ַ�������ΪNull������false,���ַ�������true.
	 * 
	 * @param str
	 *            Ҫ���м����ַ���
	 * @return true ȫ�����ֻ�ո� false ��ȫ�����ֻ�ո�
	 */
	public static boolean isNumericSpace(String str) {
		if (str == null) {
			return false;
		}
		int sz = str.length();
		for (int i = 0; i < sz; i++) {
			if ((Character.isDigit(str.charAt(i)) == false) && (str.charAt(i) != ' ')) {
				return false;
			}
		}
		return true;
	}

	// Defaults
	// --------------------------------------------------------------------------

	/**
	 * ����ַ����Ƿ�Ϊ�ն��������Կ��ַ������
	 * 
	 * @param str
	 *            Ҫ���м����ַ���
	 * @return ����ַ�������Ϊ�գ����ؿ��ַ���������Ϊ�ն��󣬷��ر���
	 */
	public static String defaultString(String str) {
		return defaultString(str, "");
	}

	/**
	 * ����ַ����Ƿ�Ϊ�ն���������ָ��Ĭ���ַ������
	 * 
	 * @param str
	 *            Ҫ���м����ַ���
	 * @param defaultString
	 *            Ĭ���ַ�������������ַ���Ϊ�ն�������
	 * @return ����ַ�������Ϊ�գ�����Ĭ���ַ���������Ϊ�ն��󣬷��ر���
	 */
	public static String defaultString(String str, String defaultString) {
		return (str == null) ? defaultString : str;
	}

	// Reversing
	// --------------------------------------------------------------------------

	/**
	 * ���������ַ���
	 * 
	 * @param str
	 *            Ҫ���е������е��ַ���
	 * @return �������к���ַ���
	 */
	public static String reverse(String str) {
		if (str == null) {
			return null;
		}
		return new StringBuffer(str).reverse().toString();
	}

	/**
	 * ���������зָ������ַ��������ָ���֮����ַ��������������С��磺
	 * reverseDelimitedString("abc,def,ghi",",")="ghi,def,abc";
	 * 
	 * @param str
	 *            Ҫ���е������е��ַ���
	 * @param delimiter
	 *            �ָ���
	 * @return �������к���ַ���
	 */
	public static String reverseDelimitedString(String str, String delimiter) {
		// could implement manually, but simple way is to reuse other,
		// probably slower, methods.
		String[] strs = split(str, delimiter);
		reverseArray(strs);
		return join(strs, delimiter);
	}

	/**
	 * �������������ڵ�Ԫ��
	 * 
	 * @param array
	 *            the array to reverse
	 */
	private static void reverseArray(Object[] array) {
		int i = 0;
		int j = array.length - 1;
		Object tmp;

		while (j > i) {
			tmp = array[j];
			array[j] = array[i];
			array[i] = tmp;
			j--;
			i++;
		}
	}

	/**
	 * ����ַ����е�ÿ���ַ��Ƿ񶼰�����ָ�����ַ�������
	 * 
	 * @param str
	 *            Ҫ�����ַ���
	 * @param char[] �ַ�����
	 * @return true str�е�ÿ���ַ���������valid�ַ������� ; false str�е�ÿ���ַ�����������valid�ַ�������
	 */
	public static boolean containsOnly(String str, char[] valid) {
		if (str == null || valid == null) {
			return false;
		}

		int strSize = str.length();
		int validSize = valid.length;

		for (int i = 0; i < strSize; i++) {
			boolean contains = false;
			for (int j = 0; j < validSize; j++) {
				if (valid[j] == str.charAt(i)) {
					contains = true;
					break;
				}
			}
			if (!contains) {
				return false;
			}
		}

		return true;
	}

	/******** ��StringUtil��Ǩ���������Ժͷ��� ********/
	/**
	 * ��ָ���㷨�����ַ��������㷨����web.xml�ļ�������
	 * 
	 * @param password
	 *            �����ܵ��ַ���
	 * @param algorithm
	 *            �����㷨
	 * 
	 * @return encypted ���ܺ���ַ���
	 */
	public static String encodePassword(String password, String algorithm) {
		byte[] unencodedPassword = password.getBytes();

		MessageDigest md = null;

		try {
			// first create an instance, given the provider
			md = MessageDigest.getInstance(algorithm);
		} catch (Exception e) {
			return password;
		}

		md.reset();

		// call the update method one or more times
		// (useful when you don't know the size of your data, eg. stream)
		md.update(unencodedPassword);

		// now calculate the hash
		byte[] encodedPassword = md.digest();

		StringBuffer buf = new StringBuffer();

		for (int i = 0; i < encodedPassword.length; i++) {
			if ((encodedPassword[i] & 0xff) < 0x10) {
				buf.append("0");
			}

			buf.append(Long.toString(encodedPassword[i] & 0xff, 16));
		}

		return buf.toString();
	}

	// /**
	// * ��Base64���ܣ�������洢��Cookieʱ�á�
	// *
	// * This is weak encoding in that anyone can use the decodeString
	// * routine to reverse the encoding.
	// *
	// * @param str
	// * @return String
	// */
	// public static String encodeString(String str) {
	// sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();
	// return encoder.encodeBuffer(str.getBytes()).trim();
	// }
	//
	// /**
	// * ��Base64����
	// *
	// * @param str
	// * @return String
	// */
	// public static String decodeString(String str) {
	// sun.misc.BASE64Decoder dec = new sun.misc.BASE64Decoder();
	// try {
	// return new String(dec.decodeBuffer(str));
	// } catch (IOException io) {
	// throw new RuntimeException(io.getMessage(), io.getCause());
	// }
	// }
	//
	/******** ��StringHelper��Ǩ�Ƶķ��� ********/
	/**
	 * �ж��ַ����Ƿ�Ϊnull��Ϊ�մ�
	 * 
	 * @param value
	 *            �ַ���
	 * @return boolean
	 */
	public static boolean isNull(String value) {
		return value == null || value.equals("") || value.equals("null");
	}

	/**
	 * �ж��ַ����Ƿ�Ϊnull���Ҳ��ǿմ�
	 * 
	 * @param value
	 *            �ַ���
	 * @return boolean
	 */
	public static boolean notNull(String value) {
		return value != null && !value.equals("");
	}

	/**
	 * ��һ���ַ�����ĳ��λ�ö�ȡһ��Boolean���͵�ֵ
	 * 
	 * @param str
	 *            �ַ���
	 * @param index
	 *            λ��
	 * @return boolean �����λ�õ��ַ�Ϊ1����true���򷵻�false
	 */
	public static boolean readBool(String str, int index) {
		boolean result = false;
		if (str != null && str.length() > index && index >= 0) {
			result = str.charAt(index) == '1';
		}
		return result;
	}

	/**
	 * ��һ��Boolean�����ַ����õ�һ���ַ�����ĳһ���ַ�
	 * 
	 * @param str
	 *            �ַ���
	 * @param index
	 *            λ�ã�����>=0���ֵ���±��0��ʼ���ַ���.length-1�����index>length����ַ�����#
	 * @param value
	 *            �������͵�ֵ
	 * @return String ����һ���µ��ַ���
	 */
	public static String writeBool(String str, int index, boolean value) {
		StringBuffer result = str == null ? new StringBuffer() : new StringBuffer(str);
		if (index >= 0) {
			int nullcount = index - result.length();
			for (int i = 0; i <= nullcount; i++) {
				result.append('#');
			}
			result.setCharAt(index, value ? '1' : '0');
		}
		return result.toString();
	}

	/**
	 * �����ַ��������������е��������ַ�������жϵı�׼Ϊequals���磺indexOfArray("a",{"a","ab","abc"})=0;
	 * 
	 * @param str
	 * @param ary
	 * @return
	 * @author renbaogang
	 */
	public static int indexOfArray(String str, String[] ary) {
		int pos = -1;
		if (str == null || ary == null) {
			return pos;
		}
		for (int i = 0; i < ary.length; i++) {
			if (str.equals(ary[i])) {
				pos = i;
				return pos;
			}
		}
		return pos;
	}

	/**
	 * ���˵��ַ��� �׻�β ���ֵ�ĳ���ַ� eg:37010200100 ȥβ 370102001 0001230001 ȥͷ 1230001
	 * 
	 * @param str
	 *            Ҫ���˵��ַ���
	 * @param s
	 *            Ҫ���˵��ַ�
	 * @param pos
	 *            λ�� 0:�ף�1��β
	 * @return ���˺���ַ���
	 * @author zhuweiming
	 * @date 2008-4-9
	 */
	public static String filterChar(String str, char s, int pos) {
		if (pos == 0) {
			int flag = 0;
			for (int i = 0; i < str.length(); i++) {
				if (str.charAt(i) == s) {
					flag = i;
				} else {
					break;
				}
			}
			if (flag != 0) {
				flag += 1;
			}
			return str.substring(flag, str.length());
		} else {
			int flag = str.length() - 1;
			for (int i = str.length() - 1; i >= 0; i--) {
				if (str.charAt(i) == s) {
					flag = i;
				} else {
					break;
				}
			}
			if (flag == str.length() - 1) {
				flag += 1;
			}
			return str.substring(0, flag);
		}
	}
}
