package com.spark.cms.manager.system.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spark.cms.manager.system.ResourceManage;
import com.spark.cms.services.form.ResourceExtForm;
import com.spark.cms.services.system.ResourceService;

@Component
public class ResourceManagerImpl implements ResourceManage{
	
	@Autowired
	private ResourceService resourceService;

	@Override
	public ResourceExtForm addResource(ResourceExtForm resourceExtForm) {
		return resourceService.addResource(resourceExtForm);
		
	}

	@Override
	public void deleteResource(String resourceIds) {
		resourceService.deleteResource(resourceIds);
		
	}

	@Override
	public ResourceExtForm getResourceById(String resourceId) {
		return resourceService.getResourceById(resourceId);
	}

	@Override
	public List<ResourceExtForm> getResources(String... args) {
		return resourceService.getResources(args);
	}

	@Override
	public ResourceExtForm updateResource(ResourceExtForm resourceExtForm) {
		 return resourceService.updateResource(resourceExtForm);
	}

	@Override
	public List<ResourceExtForm> getResourcesByParentId(String parentId) {
		return resourceService.getResourcesByParentId(parentId);
	}

	@Override
	public boolean checkResouceCode(String id, String code) {
		return resourceService.checkResouceCode(id, code);
	}

}
