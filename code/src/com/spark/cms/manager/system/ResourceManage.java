package com.spark.cms.manager.system;

import java.util.List;

import com.spark.cms.services.form.ResourceExtForm;

public interface ResourceManage {
	/**
	 * ��ѯ��Դ�б�
	 */
	public List<ResourceExtForm> getResources(String ...args);
	
	/**
	 * ��ȡ�ӽڵ� 
	 * @param parentId
	 * @return
	 */
	public  List<ResourceExtForm> getResourcesByParentId(String parentId);
	
	/**
	 * ͨ����Դ
	 */
	public ResourceExtForm getResourceById(String resourceId);
	
	/**
	 * ������Դ
	 * @return TODO
	 */
	public ResourceExtForm addResource(ResourceExtForm resourceExtForm);
	
	/**
	 * �޸���Դ
	 * @return TODO
	 */
	public ResourceExtForm updateResource(ResourceExtForm resourceExtForm);
	
	/**
	 * ɾ����Դ
	 */
	public void deleteResource(String  resourceIds);
	
	/**
	 * �����Դ�Ƿ����
	 * @param id
	 * @param code
	 * @return
	 */
	public boolean checkResouceCode(String id,String code);
	
}
