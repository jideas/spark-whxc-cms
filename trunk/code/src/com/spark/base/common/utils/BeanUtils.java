package com.spark.base.common.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

import org.apache.commons.beanutils.PropertyUtils;

public class BeanUtils {
	
	public static <T,S> T copyProperties(Class<T> clazz,S s){
		
		try{
			T t = clazz.newInstance();
			PropertyUtils.copyProperties(t, s);
			return t;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	/**
	 * ʹ�÷�����BeanUtils.copyProperties(object,new Object());
	 * @param src  Դ����
	 * @param dest	Ŀ�����
	 * @return
	 * @throws Exception
	 */
	public static <T> void copyProperties1(Object src, T dest) throws Exception {
		BeanInfo beanInfo = Introspector.getBeanInfo(src.getClass());
		PropertyDescriptor[] properties = beanInfo.getPropertyDescriptors();
		Method get = null;
		Method set = null;
		for (int i = 0; i < properties.length; i++) {
			try {
				get = properties[i].getReadMethod();
				set = dest.getClass().getMethod(
						properties[i].getWriteMethod().getName(),
						new Class[] { properties[i].getPropertyType() });
			} catch (Throwable a) {
				continue;
			}
			if (set != null && get != null) {
				set.invoke(dest, new Object[] { get.invoke(src,
						new Object[] {}) });
				set = null;
				get = null;
			}
		}
	}

}
