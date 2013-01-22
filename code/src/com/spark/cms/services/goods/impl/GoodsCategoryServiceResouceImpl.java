package com.spark.cms.services.goods.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.common.util.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spark.base.common.system.resource.SystemResourceManager;
import com.spark.base.common.utils.BeanUtils;
import com.spark.base.common.utils.StringUtil;
import com.spark.base.hibernate.GenericDAO;
import com.spark.base.type.GUID;
import com.spark.cms.base.utils.BeanCopy;
import com.spark.cms.base.utils.SortUtils;
import com.spark.cms.common.Constant;
import com.spark.cms.dao.po.CategoryPropertiesPo;
import com.spark.cms.dao.po.CategoryPvaluesPo;
import com.spark.cms.dao.po.EGoodsCategoryPo;
import com.spark.cms.dao.po.GoodsCategoryPo;
import com.spark.cms.services.ServiceMessage;
import com.spark.cms.services.form.CategoryPropertiesForm;
import com.spark.cms.services.form.CategoryPvaluesForm;
import com.spark.cms.services.form.GoodsCategoryForm;
import com.spark.cms.services.form.Login;
import com.spark.cms.services.form.UserExtForm;
import com.spark.cms.services.goods.GoodsCategoryService;
import com.spark.cms.services.vo.EGoodsCategoryTree;
import com.spark.cms.services.vo.GoodsCategoryTree;
import com.spark.cms.services.vo.GoodsCategoryVo;
import com.spark.cms.services.vo.LightGoodsCategoryTree;

@Service
public class GoodsCategoryServiceResouceImpl implements GoodsCategoryService {

	@Autowired
	private GenericDAO baseDAO;
	@Autowired
	private SystemResourceManager resourceManager;

	public static enum QueryType {
		BYLEVEL, BYPARENTID
	}

	@Override
	public void createGoodsCategory(UserExtForm loginUser, String categoryNo, String categoryName, String parentId) {
		GoodsCategoryVo categoryVo = new GoodsCategoryVo();
		categoryVo.setRecid(GUID.randomID().toString());
		String path = null;
		if (StringHelper.isEmpty(parentId) || GoodsCategoryTree.Root_ID.equals(parentId)) {
			parentId = null;
			path = categoryVo.getRecid() + ";";
		} else {
			GoodsCategoryPo parentCategory = baseDAO.get(GoodsCategoryPo.class, GUID.tryValueOf(parentId).toBytes());
			path = parentCategory.getPath() + categoryVo.getRecid() + ";";
			GoodsCategoryVo parentVo = resourceManager.find(GoodsCategoryVo.class, parentId);
			if (null == parentVo) throw new ServiceMessage("上级分类不存在");
			categoryNo = parentVo.getCategoryno() + categoryNo;
		}
		
		categoryVo.setCategoryno(categoryNo);
		categoryVo.setCategoryname(categoryName);
		categoryVo.setParentid(parentId);
		categoryVo.setPath(path);
		int pathLevel = path.split(";").length;
		categoryVo.setLevelno(pathLevel < 0 ? 0 : pathLevel);
		categoryVo.setCreatedate(new Date());
		categoryVo.setCreator(loginUser.getName());
		categoryVo.setCreatorid(loginUser.getId());

		GoodsCategoryPo categoryPo = new GoodsCategoryPo();
		try {
			BeanUtils.copyProperties1(categoryVo, categoryPo);
			categoryPo.setRecid(GUID.tryValueOf(categoryVo.getRecid()).toBytes());
			categoryPo.setCreatorid(GUID.tryValueOf(categoryVo.getCreatorid()).toBytes());
			categoryPo.setParentid(null == parentId ? null : GUID.tryValueOf(parentId).toBytes());
			baseDAO.save(categoryPo);
			resourceManager.insert(BeanCopy.copy(GoodsCategoryVo.class, categoryPo));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public int isCategoryExist(String parentId, String categoryNo, String categoryName, String categoryId) {
		List<GoodsCategoryVo> sblingList = null;
		if (StringUtil.isEmpty(parentId)) return Constant.NOT_EXIST;
		if (GoodsCategoryTree.Root_ID.equals(parentId)) {
			sblingList = resourceManager.getListByKey(GoodsCategoryVo.class, 1, QueryType.BYLEVEL);
		} else {
			sblingList = resourceManager.getListByKey(GoodsCategoryVo.class, parentId, QueryType.BYPARENTID);
		}
		if (sblingList == null) return Constant.NOT_EXIST;
		for (GoodsCategoryVo category : sblingList) {
			String shorNo = category.getCategoryno().substring(category.getCategoryno().length() - 2, category.getCategoryno().length());
			if (shorNo.equals(categoryNo)) {
				if (!category.getRecid().equals(categoryId)) {
					return Constant.CATEGORYNO_EXIST;
				}
			}
			if (category.getCategoryname().equals(categoryName)) {
				if (!category.getRecid().equals(categoryId)) {
					return Constant.CATEGORYNAME_EXIST;
				}
			}
		}
		return Constant.NOT_EXIST;
	}

	@Override
	public boolean modifyGoodsCategoryName(UserExtForm loginUser, String categoryId, String categoryNo, String name) {
		GoodsCategoryVo categoryInfo = resourceManager.find(GoodsCategoryVo.class, categoryId);
		GoodsCategoryVo parentInfo = resourceManager.find(GoodsCategoryVo.class, categoryInfo.getParentid());
		if (parentInfo != null) {
			categoryNo = parentInfo.getCategoryno() + categoryNo;
		}
		int result = baseDAO.execteBulk("update GoodsCategoryPo set categoryno='" + categoryNo + "', categoryname = '" + name + "', lastmodifyperson='"
				+ loginUser.getName() + "', lastmodifydate=? " + "where recid=?", new Date(), GUID.tryValueOf(
				categoryId).toBytes());
		if (result == 1) {
			GoodsCategoryPo po = baseDAO.get(GoodsCategoryPo.class, GUID.tryValueOf(categoryId).toBytes());
			GoodsCategoryVo vo = BeanCopy.copy(GoodsCategoryVo.class, po);
			vo.setCategoryno(categoryNo);
			vo.setCategoryname(name);
			resourceManager.update(vo);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean modifyGoodsCategoryProperties(Login login, CategoryPropertiesForm[] properties) {
		// TODO 处理异常情况
		if (properties == null || properties.length < 1)
			return false;
		byte[] categoryId = GUID.tryValueOf(properties[0].getCategoryid()).toBytes();
		// 删除分类对应的所有属性值
		baseDAO.execteBulk("delete CategoryPvaluesPo where categoryid=?", categoryId);
		baseDAO.execteBulk("delete CategoryPropertiesPo where categoryid=?", categoryId);
		try {
			int index = 0;
			for (CategoryPropertiesForm propertiesForm : properties) {
				if (index > 4)
					break;
				CategoryPropertiesPo propertiesPo = new CategoryPropertiesPo();
				BeanUtils.copyProperties1(propertiesForm, propertiesPo);
				propertiesPo.setCategoryid(categoryId);
				propertiesPo.setRecid(GUID.randomID().toBytes());
				baseDAO.save(propertiesPo);
				for (CategoryPvaluesForm value : propertiesForm.getValues()) {
					CategoryPvaluesPo valuePo = new CategoryPvaluesPo();
					BeanUtils.copyProperties1(value, valuePo);
					valuePo.setCategoryid(categoryId);
					valuePo.setPropertyid(propertiesPo.getRecid());
					valuePo.setRecid(GUID.randomID().toBytes());
					baseDAO.save(valuePo);
				}
				index++;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		// baseDAO.execteBulk("update GoodsCategoryPo set lastmodifyperson='" +
		// login.getName() + "', lastmodifydate=? " +
		// "where recid=?", new Date(), properties[0].getCategoryid());
		// baseDAO.execteBulk("update GoodsCategoryPo set lastmodifydate=? " +
		// " where recid=?", new Date(), categoryId);
		return true;
	}

	@Override
	public boolean deleteGoodsCategory(String categoryId) {
		if (categoryId == null)
			return false;
		try {
			byte[] categoryIdb = GUID.valueOf(categoryId).toBytes();
			int resultC = baseDAO.execteBulk("delete from GoodsCategoryPo where recid=?", categoryIdb);
			baseDAO.execteBulk("delete from CategoryPropertiesPo where categoryid=?", categoryIdb);
			baseDAO.execteBulk("delete from CategoryPvaluesPo where categoryid=?", categoryIdb);
			if (resultC == 1) {
				resourceManager.delete(GoodsCategoryVo.class, categoryId);
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public GoodsCategoryForm getGoodsCategoryById(String categoryId) {
		GoodsCategoryForm categoryForm = new GoodsCategoryForm();
		try {
			GoodsCategoryPo categoryPo = baseDAO.get(GoodsCategoryPo.class, GUID.tryValueOf(categoryId).toBytes());
			BeanUtils.copyProperties1(categoryPo, categoryForm);
			categoryForm.setCreatorid(null == categoryPo.getCreatorid() ? null : GUID
					.valueOf(categoryPo.getCreatorid()).toString());
			categoryForm.setParentid(null == categoryPo.getParentid() ? null : GUID.valueOf(categoryPo.getParentid())
					.toString());
			categoryForm.setRecid(GUID.valueOf(categoryPo.getRecid()).toString());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		List<CategoryPropertiesPo> propertiesList = baseDAO.getGenericByHql(
				"from CategoryPropertiesPo as t where t.categoryid=? order by t.ordinal asc", GUID.tryValueOf(
						categoryId).toBytes());
		CategoryPropertiesForm[] properties = new CategoryPropertiesForm[propertiesList.size()];
		try {
			for (int propertyIndex = 0; propertyIndex < propertiesList.size(); propertyIndex++) {
				CategoryPropertiesPo propertyPo = propertiesList.get(propertyIndex);
				CategoryPropertiesForm propertyForm = new CategoryPropertiesForm();
				BeanUtils.copyProperties1(propertyPo, propertyForm);
				propertyForm.setCategoryid(categoryId);
				propertyForm.setRecid(GUID.valueOf(propertyPo.getRecid()).toString());
				List<CategoryPvaluesPo> valueList = baseDAO.getGenericByHql(
						"from CategoryPvaluesPo as t where t.propertyid=? order by t.ordinal asc", propertyPo
								.getRecid());
				CategoryPvaluesForm[] valuesForm = new CategoryPvaluesForm[valueList.size()];
				for (int valueIndex = 0; valueIndex < valueList.size(); valueIndex++) {
					CategoryPvaluesPo valuePo = valueList.get(valueIndex);
					CategoryPvaluesForm valueForm = new CategoryPvaluesForm();
					BeanUtils.copyProperties1(valuePo, valueForm);
					valueForm.setCategoryid(categoryId);
					valueForm.setPropertyid(propertyForm.getRecid());
					valueForm.setRecid((GUID.valueOf(valuePo.getRecid()).toString()));
					valuesForm[valueIndex] = valueForm;
				}
				propertyForm.setValues(valuesForm);
				properties[propertyIndex] = propertyForm;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		categoryForm.setPropertiesForm(properties);
		return categoryForm;
	}
	
	public GoodsCategoryVo getGoodsCategorySimpleInfoById(String categoryId) {
		return resourceManager.find(GoodsCategoryVo.class, categoryId);
	}
	
	@Override
	public List<GoodsCategoryVo> getGoodsCateogryListByLevel(int levelNo) {
		List<GoodsCategoryVo> categoryVoList = new ArrayList<GoodsCategoryVo>();
		List<GoodsCategoryPo> childrenList = baseDAO.getGenericByHql("from GoodsCategoryPo as t where t.levelno = '"
				+ levelNo + "' order by t.categoryno");
		try {
			for (GoodsCategoryPo categoryPo : childrenList) {
				GoodsCategoryVo categoryVo = new GoodsCategoryVo();
				BeanUtils.copyProperties1(categoryPo, categoryVo);
				categoryVo.setParentid(categoryPo.getParentid() == null ? null : GUID.valueOf(categoryPo.getParentid())
						.toString());
				categoryVo.setRecid(GUID.valueOf(categoryPo.getRecid()).toString());
				categoryVoList.add(categoryVo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<GoodsCategoryVo>();
		}
		return categoryVoList;
	}

	@Override
	public List<GoodsCategoryVo> getChildrenCategoryList(String categoryId) {
		List<GoodsCategoryVo> categoryVoList = new ArrayList<GoodsCategoryVo>();
		List<GoodsCategoryPo> childrenList = baseDAO.getGenericByHql(
				"from GoodsCategoryPo as t where t.parentid = ? order by t.categoryno", GUID.tryValueOf(categoryId)
						.toBytes());
		try {
			for (GoodsCategoryPo categoryPo : childrenList) {
				GoodsCategoryVo categoryVo = new GoodsCategoryVo();
				BeanUtils.copyProperties1(categoryPo, categoryVo);
				categoryVo.setParentid(GUID.valueOf(categoryPo.getParentid()).toString());
				categoryVo.setRecid(GUID.valueOf(categoryPo.getRecid()).toString());
				categoryVoList.add(categoryVo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<GoodsCategoryVo>();
		}
		return categoryVoList;
	}

	public GoodsCategoryTree getGoodsCategoryTreeNode(String parentId) {
		GoodsCategoryTree categoryTree = new GoodsCategoryTree();
		List<GoodsCategoryVo> childrenCategoryList = new ArrayList<GoodsCategoryVo>();
		if (parentId == null || GoodsCategoryTree.Root_ID.equals(parentId)) {
			categoryTree.setId(GoodsCategoryTree.Root_ID);
			categoryTree.setText(GoodsCategoryTree.Root_Text);
			childrenCategoryList = resourceManager.getListByKey(GoodsCategoryVo.class, 1, QueryType.BYLEVEL);
		} else {
			GoodsCategoryVo categoryVo = resourceManager.find(GoodsCategoryVo.class, parentId);
			categoryTree.setId(categoryVo.getRecid());
			categoryTree.setText(categoryVo.getCategoryname());
			childrenCategoryList = resourceManager.getListByKey(GoodsCategoryVo.class, parentId, QueryType.BYPARENTID);
		}
		if (null == childrenCategoryList || childrenCategoryList.size() < 1) {
			categoryTree.setChildren(null);
			return categoryTree;
		}
		SortUtils.sort(childrenCategoryList, "categoryno");
		categoryTree.setState(GoodsCategoryTree.Status.open.name());
		
		GoodsCategoryTree.CategoryNode[] childrenNode = new GoodsCategoryTree.CategoryNode[childrenCategoryList.size()];
		int index = 0;
		for (GoodsCategoryVo categoryVo : childrenCategoryList) {
			GoodsCategoryTree.CategoryNode childNode = categoryTree.new CategoryNode();
			GoodsCategoryTree.Attribute attributes = categoryTree.new Attribute();
			childNode.setText(categoryVo.getCategoryname());
			attributes.setCategoryNo(categoryVo.getCategoryno());
			attributes.setLevelNo(categoryVo.getPath().split(";").length);
			/*
			long propertiteCount = baseDAO.getGenericCountByHql(
					"select count(*) from CategoryPropertiesPo as t where t.categoryid=?", GUID.tryValueOf(
							categoryVo.getRecid()).toBytes());
			attributes.setPropertied(propertiteCount > 0);
			*/
			childNode.setAttributes(attributes);
			childNode.setId(categoryVo.getRecid());
//			List<GoodsCategoryVo> childChildrenList = resourceManager.getListByKey(GoodsCategoryVo.class, categoryVo
//					.getRecid(), QueryType.BYPARENTID);
			long childrenCount = baseDAO.getGenericCountByHql(
					"select count(*) from GoodsCategoryPo as t where t.parentid=?", GUID.tryValueOf(
							categoryVo.getRecid()).toBytes());
			if (childrenCount > 0) {
				childNode.setState(GoodsCategoryTree.Status.closed.name());
			} else {
				childNode.setState(GoodsCategoryTree.Status.open.name());
			}
			childrenNode[index] = childNode;
			index++;
		}

		categoryTree.setChildren(childrenNode);
		return categoryTree;
	}

	@Override
	@Deprecated
	public GoodsCategoryTree getGoodsCategoryTree() {
		GoodsCategoryTree categoryTree = new GoodsCategoryTree();
		List<GoodsCategoryVo> rootCategoryList = new ArrayList<GoodsCategoryVo>();
		try {
			rootCategoryList = resourceManager.getListByKey(GoodsCategoryVo.class, 1, QueryType.BYLEVEL);
			// rootCategoryList = baseDAO.getGenericByHql("from GoodsCategoryPo
			// as t where t.levelno = 1 order by t.categoryno");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (rootCategoryList.size() < 1) {
			categoryTree.setChildren(null);
			return categoryTree;
		}
		try {
			GoodsCategoryTree.CategoryNode[] rootNodes = new GoodsCategoryTree.CategoryNode[rootCategoryList.size()];
			int index = 0;
			for (GoodsCategoryVo categoryVo : rootCategoryList) {
				GoodsCategoryTree.CategoryNode rootNode = categoryTree.new CategoryNode();
				GoodsCategoryTree.Attribute attributes = categoryTree.new Attribute();
				rootNode.setText(categoryVo.getCategoryname());
				attributes.setCategoryNo(categoryVo.getCategoryno());
				attributes.setLevelNo(categoryVo.getPath().split(";").length);
				long childrenCount = baseDAO.getGenericCountByHql(
						"select count(*) from CategoryPropertiesPo as t where t.categoryid=?", GUID.tryValueOf(
								categoryVo.getRecid()).toBytes());
				attributes.setPropertied(childrenCount > 0);
				rootNode.setAttributes(attributes);
				// rootNode.setParent(null);
				rootNode.setId(categoryVo.getRecid());
				setGoodsCategoryChildren(categoryTree, rootNode);
				rootNodes[index] = rootNode;
				index++;
			}

			categoryTree.setChildren(rootNodes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return categoryTree;
	}

	private void setGoodsCategoryChildren(GoodsCategoryTree categoryTree, GoodsCategoryTree.CategoryNode node) {
		List<GoodsCategoryVo> childrenList = resourceManager.getListByKey(GoodsCategoryVo.class, node.getId(),
				QueryType.BYPARENTID);
		if (childrenList.size() < 1) {
			node.setChildren(null);
			return;
		}
		GoodsCategoryTree.CategoryNode[] childrenNodes = new GoodsCategoryTree.CategoryNode[childrenList.size()];
		int index = 0;
		for (GoodsCategoryVo child : childrenList) {
			GoodsCategoryTree.CategoryNode childNode = categoryTree.new CategoryNode();
			GoodsCategoryTree.Attribute attributes = categoryTree.new Attribute();
			childNode.setText(child.getCategoryname());
			attributes.setCategoryNo(child.getCategoryno());
			attributes.setLevelNo(child.getPath().split(";").length);
			long childrenCount = baseDAO.getGenericCountByHql(
					"select count(*) from CategoryPropertiesPo as t where t.categoryid=?", GUID.tryValueOf(
							child.getRecid()).toBytes());
			attributes.setPropertied(childrenCount > 0);
			childNode.setAttributes(attributes);
			// childNode.setParent(node);
			childNode.setId(GUID.valueOf(child.getRecid()).toString());
			setGoodsCategoryChildren(categoryTree, childNode);
			childrenNodes[index] = childNode;
			index++;
		}
		node.setChildren(childrenNodes);
	}

	@Override
	public EGoodsCategoryTree getEGoodsCategoryTree() {
		EGoodsCategoryTree eCategoryTree = new EGoodsCategoryTree();
		List<EGoodsCategoryPo> rootCategoryList = new ArrayList<EGoodsCategoryPo>();
		rootCategoryList = baseDAO.getGenericByHql("from EGoodsCategoryPo where parentid=? order by createdate desc",
				EGoodsCategoryTree.ERP_ROOT_ID);
		// rootCategoryList = baseDAO.getGenericByHql("from EGoodsCategoryPo");
		if (rootCategoryList.size() < 1) {
			eCategoryTree.setChildren(null);
			return eCategoryTree;
		}
		EGoodsCategoryTree.CategoryNode[] rootNodes = new EGoodsCategoryTree.CategoryNode[rootCategoryList.size()];
		int index = 0;
		for (EGoodsCategoryPo categoryPo : rootCategoryList) {
			EGoodsCategoryTree.CategoryNode rootNode = eCategoryTree.new CategoryNode();
			rootNode.setText(categoryPo.getCategoryname());
			// rootNode.setParent(null);
			rootNode.setId(GUID.valueOf(categoryPo.getRecid()).toString());
			setEGoodsCategoryChildren(eCategoryTree, rootNode);
			rootNodes[index] = rootNode;
			index++;
		}

		eCategoryTree.setChildren(rootNodes);
		return eCategoryTree;
	}

	private void setEGoodsCategoryChildren(EGoodsCategoryTree categoryTree, EGoodsCategoryTree.CategoryNode node) {
		List<EGoodsCategoryPo> childrenList = baseDAO.getGenericByHql(
				"from EGoodsCategoryPo as t where t.parentid = ? order by t.createdate desc", GUID.tryValueOf(
						node.getId()).toBytes());
		if (childrenList.size() < 1) {
			node.setChildren(null);
			return;
		}
		EGoodsCategoryTree.CategoryNode[] childrenNodes = new EGoodsCategoryTree.CategoryNode[childrenList.size()];
		int index = 0;
		for (EGoodsCategoryPo child : childrenList) {
			EGoodsCategoryTree.CategoryNode childNode = categoryTree.new CategoryNode();
			childNode.setText(child.getCategoryname());
			// childNode.setParent(node);
			childNode.setId(GUID.valueOf(child.getRecid()).toString());
			setEGoodsCategoryChildren(categoryTree, childNode);
			childrenNodes[index] = childNode;
			index++;
		}
		node.setChildren(childrenNodes);
	}
	
	public EGoodsCategoryTree getEGoodsCategoryTreeNode(String parentId) {
		EGoodsCategoryTree categoryTree = new EGoodsCategoryTree();
		List<EGoodsCategoryPo> childrenCategoryList = new ArrayList<EGoodsCategoryPo>();
		if (parentId == null || EGoodsCategoryTree.ERP_ROOT_ID_STR.equals(parentId)) {
			categoryTree.setId(EGoodsCategoryTree.ERP_ROOT_ID_STR);
			categoryTree.setText("全部");
			childrenCategoryList = baseDAO.getGenericByHql(
					"from EGoodsCategoryPo as t where t.parentid = ? order by t.createdate desc", EGoodsCategoryTree.ERP_ROOT_ID);
		} else {
			EGoodsCategoryPo categoryVo = baseDAO.get(EGoodsCategoryPo.class, GUID.tryValueOf(parentId).toBytes());
			categoryTree.setId(parentId);
			categoryTree.setText(categoryVo.getCategoryname());
			childrenCategoryList = baseDAO.getGenericByHql(
					"from EGoodsCategoryPo as t where t.parentid = ? order by t.createdate desc", GUID.tryValueOf(parentId).toBytes());
		}
		if (childrenCategoryList.size() < 1) {
			categoryTree.setChildren(null);
			return categoryTree;
		}
		SortUtils.sort(childrenCategoryList, "categoryno");
		categoryTree.setState(GoodsCategoryTree.Status.open.name());
		EGoodsCategoryTree.CategoryNode[] childrenNode = new EGoodsCategoryTree.CategoryNode[childrenCategoryList.size()];
		int index = 0;
		for (EGoodsCategoryPo categoryVo : childrenCategoryList) {
			EGoodsCategoryTree.CategoryNode childNode = categoryTree.new CategoryNode();
			childNode.setText(categoryVo.getCategoryname());
			childNode.setId(GUID.valueOf(categoryVo.getRecid()).toString());
			childrenNode[index] = childNode;
			List<Long> childrenCountL = baseDAO.getGenericByHql(
					"select count(*) from EGoodsCategoryPo as t where t.parentid = ?", categoryVo.getRecid());
			if (childrenCountL.get(0) > 0) {
				childNode.setState(GoodsCategoryTree.Status.closed.name());
			}
			index++;
		}
		categoryTree.setChildren(childrenNode);
		return categoryTree;
	}
	@Override
	public List<GoodsCategoryVo> getAllGoodsCategoryList() {
		List<GoodsCategoryPo> poList = baseDAO.getGenericByHql("from GoodsCategoryPo order by categoryno asc");
		List<GoodsCategoryVo> voList = BeanCopy.copys(GoodsCategoryVo.class, poList);
		return voList == null ? new ArrayList<GoodsCategoryVo>() : voList;
	}

	@Override
	public LightGoodsCategoryTree getLightGoodsCategoryTree() {
		LightGoodsCategoryTree categoryTree = new LightGoodsCategoryTree();
		categoryTree.setId(LightGoodsCategoryTree.Root_ID);
		categoryTree.setText(LightGoodsCategoryTree.Root_Text);
		List<GoodsCategoryVo> rootCategoryList = resourceManager.getListByKey(GoodsCategoryVo.class, 1,
				QueryType.BYLEVEL);
		List<GoodsCategoryVo> secondCategoryList = resourceManager.getListByKey(GoodsCategoryVo.class, 2,
				QueryType.BYLEVEL);
		List<GoodsCategoryVo> sunCategoryList = resourceManager.getListByKey(GoodsCategoryVo.class, 3,
				QueryType.BYLEVEL);
		if (null == rootCategoryList || rootCategoryList.size() < 1) {
			categoryTree.setChildren(null);
			return categoryTree;
		} 
		SortUtils.sort(rootCategoryList, "categoryno");
		if (null != secondCategoryList) {
			SortUtils.sort(secondCategoryList, "categoryno");
		}
		if (null != sunCategoryList) {
			SortUtils.sort(sunCategoryList, "categoryno");
		}
		LightGoodsCategoryTree.CategoryNode[] rootNodes = new LightGoodsCategoryTree.CategoryNode[rootCategoryList
				.size()];
		int index = 0;
		for (GoodsCategoryVo categoryVo : rootCategoryList) {
			LightGoodsCategoryTree.CategoryNode rootNode = categoryTree.new CategoryNode();
			rootNode.setText(categoryVo.getCategoryname());
			rootNode.setId(categoryVo.getRecid());
			rootNodes[index] = rootNode;
			setLightGoodsCategoryChildren(categoryTree, rootNode, secondCategoryList, sunCategoryList);
			index++;
		} 
		categoryTree.setChildren(rootNodes);
		return categoryTree;
	}

	private void setLightGoodsCategoryChildren(LightGoodsCategoryTree categoryTree,
			LightGoodsCategoryTree.CategoryNode node, List<GoodsCategoryVo> secondCategoryList,
			List<GoodsCategoryVo> sunCategoryList) {
		if (secondCategoryList == null || secondCategoryList.size() < 1) {
			node.setChildren(null);
			return;
		}
		List<LightGoodsCategoryTree.CategoryNode> childrenNodes = new ArrayList<LightGoodsCategoryTree.CategoryNode>();
		for (GoodsCategoryVo child : secondCategoryList) {
			if (!child.getParentid().equals(node.getId())) {
				continue;
			}
			LightGoodsCategoryTree.CategoryNode childNode = categoryTree.new CategoryNode();
			childNode.setText(child.getCategoryname());
			childNode.setId(GUID.valueOf(child.getRecid()).toString());
			setLightGoodsCategoryChildren(categoryTree, childNode, sunCategoryList);
			childrenNodes.add(childNode);
		}
		node.setChildren(childrenNodes.toArray(new LightGoodsCategoryTree.CategoryNode[childrenNodes.size()]));
	}

	private void setLightGoodsCategoryChildren(LightGoodsCategoryTree categoryTree,
			LightGoodsCategoryTree.CategoryNode node, List<GoodsCategoryVo> sunCategoryList) {
		if (sunCategoryList == null || sunCategoryList.size() < 1) {
			node.setChildren(null);
			return;
		}
		List<LightGoodsCategoryTree.CategoryNode> childrenNodes = new ArrayList<LightGoodsCategoryTree.CategoryNode>();
		for (GoodsCategoryVo child : sunCategoryList) {
			if (!child.getParentid().equals(node.getId())) {
				continue;
			}
			LightGoodsCategoryTree.CategoryNode childNode = categoryTree.new CategoryNode();
			childNode.setText(child.getCategoryname());
			childNode.setId(GUID.valueOf(child.getRecid()).toString());
			childrenNodes.add(childNode);
		}
		node.setChildren(childrenNodes.toArray(new LightGoodsCategoryTree.CategoryNode[childrenNodes.size()]));
	}
}
