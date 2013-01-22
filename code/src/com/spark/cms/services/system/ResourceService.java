package com.spark.cms.services.system;

import java.util.List;

import com.spark.cms.services.form.ResourceExtForm;

public interface ResourceService {
	
	/**
	 * ��ѯ��Դ�б�
	 */
	public List<ResourceExtForm> getResources(String ...args);
	
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
	public void deleteResource(String...  resourceIds);

	public List<ResourceExtForm> getResourcesByParentId(String parentId);

	/**
	 * �������Ƿ����
	 * @param id
	 * @param code
	 * @return
	 */
	public boolean checkResouceCode(String id,String code);
	
}
