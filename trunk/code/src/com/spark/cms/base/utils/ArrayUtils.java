/**
 * 
 */
package com.spark.cms.base.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jideas ���鹤��
 */
public class ArrayUtils {

	public static <T> List<T> arrayToList(T[] array) {
		if (null == array) {
			return null;
		}
		List<T> list = new ArrayList<T>();
		for (T t : array) {
			if (null == t) {
				continue;
			}
			list.add(t);
		}
		return list;
	} 
}
