/**
 * 
 */
package com.spark.cms.services.folder;

import java.util.List;

import com.spark.base.common.system.resource.BaseResourceProvider;
import com.spark.cms.services.vo.FolderVo;

/**
 * @author Jideas
 * 
 */
public class FolderResourceProvider implements BaseResourceProvider<FolderVo> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.spark.base.common.system.resource.BaseResourceProvider#getElements(java.lang.Object)
	 */
	@Override
	public List<FolderVo> getElements(Object service) {
		FolderService fservice = (FolderService) service;
		return fservice.getAllFolders();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.spark.base.common.system.resource.BaseResourceProvider#getOneKeyValidate(java.lang.Object,
	 *      java.lang.Object)
	 */
	@Override
	public boolean getOneKeyValidate(FolderVo t, Object key) {
		if (t == null) {
			return false;
		}
		if (t.getParentId().equals(key)) {
			return true;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.spark.base.common.system.resource.BaseResourceProvider#getServiceName()
	 */
	@Override
	public String getServiceName() {
		return FolderService.class.getName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.spark.base.common.system.resource.BaseResourceProvider#getThreeKeyValidate(java.lang.Object,
	 *      java.lang.Object, java.lang.Object, java.lang.Object)
	 */
	@Override
	public boolean getThreeKeyValidate(FolderVo t, Object key, Object key2, Object key3) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.spark.base.common.system.resource.BaseResourceProvider#getTwoKeyValidate(java.lang.Object,
	 *      java.lang.Object, java.lang.Object)
	 */
	@Override
	public boolean getTwoKeyValidate(FolderVo t, Object key, Object key2) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.spark.base.common.system.resource.BaseResourceProvider#getEntityClassName()
	 */
	@Override
	public Class<FolderVo> getEntityClass() {
		return FolderVo.class;
	}

}
