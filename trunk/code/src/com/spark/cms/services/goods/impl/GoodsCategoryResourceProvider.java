package com.spark.cms.services.goods.impl;

import java.util.List;

import com.spark.base.common.system.resource.BaseResourceProvider;
import com.spark.cms.services.goods.GoodsCategoryService;
import com.spark.cms.services.goods.impl.GoodsCategoryServiceResouceImpl.QueryType;
import com.spark.cms.services.vo.GoodsCategoryVo;

public class GoodsCategoryResourceProvider implements BaseResourceProvider<GoodsCategoryVo> {

	@Override
	public List<GoodsCategoryVo> getElements(Object service) {
		return ((GoodsCategoryService)service).getAllGoodsCategoryList();
	}

	@Override
	public String getServiceName() {
		return GoodsCategoryService.class.getName();
	}

	@Override
	public boolean getOneKeyValidate(GoodsCategoryVo t, Object key) {
		return false;
	}

	@Override
	public boolean getTwoKeyValidate(GoodsCategoryVo t, Object key, Object key2) {
		if (key == null) return false;
		if (QueryType.BYLEVEL == key2) {
			int targetLevel = (Integer)key;
			return (t.getLevelno() == targetLevel);
		} else if (QueryType.BYPARENTID == key2) {
			String parentId = (String) key;
			return parentId.equals(t.getParentid());
		}
		return false;
	}

	@Override
	public boolean getThreeKeyValidate(GoodsCategoryVo t, Object key,
			Object key2, Object key3) {
		return false;
	}

	/* (non-Javadoc)
	 * @see com.spark.base.common.system.resource.BaseResourceProvider#getEntityClassName()
	 */
	@Override
	public Class<GoodsCategoryVo> getEntityClass() {
		return GoodsCategoryVo.class;
	}

}
