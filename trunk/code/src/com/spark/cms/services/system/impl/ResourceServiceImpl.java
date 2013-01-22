package com.spark.cms.services.system.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spark.base.common.utils.BeanUtils;
import com.spark.base.common.utils.ListUtils;
import com.spark.base.common.utils.RandomGUID;
import com.spark.base.common.utils.StringUtil;
import com.spark.base.hibernate.GenericDAO;
import com.spark.cms.dao.po.Resource;
import com.spark.cms.services.form.ResourceExtForm;
import com.spark.cms.services.system.ResourceService;
@Service
public class ResourceServiceImpl  implements ResourceService{
	
	@Autowired
	private GenericDAO genericDAO;

	@Override
	public boolean checkResouceCode(String id,String code){
		boolean isExist = false;
		String sql = " select count(*) from Resouce r where r.code= '"+code+"' ";
		if(!StringUtil.isEmpty(id)){
			sql += " r.id ！= '"+id+"' ";
		}
		int i = genericDAO.getGenericCountByHql(sql);
		if(i>0) isExist = true;
		return isExist;
	}

	@Override
	public ResourceExtForm addResource(ResourceExtForm resourceExtForm) {
		Resource cmsResource = BeanUtils.copyProperties(Resource.class, resourceExtForm);
		cmsResource.setId(RandomGUID.newGuid());
		genericDAO.save(cmsResource);
		
//		if(!StringUtil.isEmpty(cmsResource.getParentId())){
//			Resource parentResouce = genericDAO.get(Resource.class, cmsResource.getParentId());
//		}
		
		
		resourceExtForm.setId(cmsResource.getId());
		return resourceExtForm;
	}
	
	/**
	 *获取一级子节点
	 * @param parentCode
	 * @return
	 */
	@Override
	public List<ResourceExtForm> getResourcesByParentId(String parentId){
		StringBuffer sb = new StringBuffer();
		sb.append(" from Resource r  where  1=1 ");
		if(parentId!=null&&!parentId.trim().equals("")){
			sb.append(" and r.parentId = '").append(parentId).append("' ");
		}else{
			sb.append(" and ( r.parentId ='' or r.parentId is null) ");
		} 
		sb.append("order by r.code ");
		List<Resource> list = genericDAO.getGenericByHql(sb.toString());
		return ListUtils.transform(list, ResourceExtForm.class);
	}
	
	@Override
	public void deleteResource(String... resourceIds){ 
		String hsql =null;
		for(String id:resourceIds){
			Resource cmsResource = genericDAO.get(Resource.class,id);
			hsql = "delete from cms_Resource where id = '"+cmsResource.getId() +"'";
			genericDAO.execteNativeBulk(hsql);
			hsql = "delete from cms_Resource where parentId = '"+cmsResource.getId() +"'";
			genericDAO.execteNativeBulk(hsql);
			hsql = "delete from Cms_RoleResource  where resourceId = '"+cmsResource.getId() +"'";
			genericDAO.execteNativeBulk(hsql);
		}
	}

	@Override
	public ResourceExtForm getResourceById(String resourceId) {
		Resource cmsResource = genericDAO.get(Resource.class,resourceId);
		if(cmsResource!=null){
			return BeanUtils.copyProperties(ResourceExtForm.class, cmsResource);
		}
		return null;
	}

	@Override
	public List<ResourceExtForm> getResources(String... args) {
		String sql  = "from Resource r order by r.code";
		List<Resource> list = genericDAO.getGenericByHql(sql);
		return ListUtils.transform(list, ResourceExtForm.class);
	}

	@Override
	public ResourceExtForm updateResource(ResourceExtForm resourceExtForm) {
		Resource cmsResource = BeanUtils.copyProperties(Resource.class, resourceExtForm);
		genericDAO.saveOrUpdate(cmsResource);
		return resourceExtForm;
		
	}

}
