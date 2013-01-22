package com.spark.base.common.system.resource;

import java.util.List;

/**
 * @author Jideas ϵͳ��Դ����
 */
public interface SystemResourceService {
	/**
	 * �õ�ȫ������
	 */
	<T> List<T> getList(Class<T> clazz);

	/**
	 * ����id�õ�����
	 */
	<T> T find(Class<T> clazz, String recid);

	/**
	 * ���������õ�����
	 */
	<T> T findByKey(Class<T> clazz, Object... key);

	/**
	 * ���������õ��������
	 */
	<T> List<T> getListByKey(Class<T> clazz, Object... key);

	/**
	 * ������Դ
	 */
	<T> boolean update(T t);

	/**
	 * ������Դ
	 */
	<T> boolean insert(T t);

	/**
	 * ɾ����Դ
	 */
	<T> boolean delete(T t);

	/**
	 * ɾ����Դ
	 */
	<T> boolean delete(Class<T> clazz, String recid);

}
