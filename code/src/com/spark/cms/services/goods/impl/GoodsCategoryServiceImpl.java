package com.spark.cms.services.goods.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.common.util.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spark.base.common.utils.BeanUtils;
import com.spark.base.hibernate.GenericDAO;
import com.spark.base.type.GUID;
import com.spark.cms.dao.po.CategoryPropertiesPo;
import com.spark.cms.dao.po.CategoryPvaluesPo;
import com.spark.cms.dao.po.EGoodsCategoryPo;
import com.spark.cms.dao.po.GoodsCategoryPo;
import com.spark.cms.services.form.CategoryPropertiesForm;
import com.spark.cms.services.form.CategoryPvaluesForm;
import com.spark.cms.services.form.GoodsCategoryForm;
import com.spark.cms.services.form.Login;
import com.spark.cms.services.form.UserExtForm;
import com.spark.cms.services.goods.GoodsCategoryService;
import com.spark.cms.services.vo.EGoodsCategoryTree;
import com.spark.cms.services.vo.GoodsCategoryTree;
import com.spark.cms.services.vo.GoodsCategoryVo;

@Service
public class GoodsCategoryServiceImpl {

	/*
	@Autowired
	private GenericDAO baseDAO;
	
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean modifyGoodsCategoryName(UserExtForm loginUser, String categoryId, String name) {
		int result = baseDAO.execteBulk("update GoodsCategoryPo set categoryname = '" 
				+ name + "', lastmodifyperson='" + loginUser.getName() + "', lastmodifydate=? " +
						"where recid=?", new Date(), GUID.tryValueOf(categoryId).toBytes());
		return result == 1 ? true : false;
	}

	@Override
	public boolean modifyGoodsCategoryProperties(Login login, CategoryPropertiesForm[] properties) {
		// TODO 处理异常情况
		if (properties == null || properties.length < 1) return false;
		byte[] categoryId = GUID.tryValueOf(properties[0].getCategoryid()).toBytes();
		// 删除分类对应的所有属性值
		baseDAO.execteBulk("delete CategoryPvaluesPo where categoryid=?", categoryId);
		baseDAO.execteBulk("delete CategoryPropertiesPo where categoryid=?", categoryId);
		try {
			int index = 0;
			for (CategoryPropertiesForm propertiesForm : properties) {
				if (index > 4) break;
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
//		baseDAO.execteBulk("update GoodsCategoryPo set lastmodifyperson='" + login.getName() + "', lastmodifydate=? " +
//						"where recid=?", new Date(), properties[0].getCategoryid());
//		baseDAO.execteBulk("update GoodsCategoryPo set lastmodifydate=? " +
//				" where recid=?", new Date(), categoryId);
		return true;
	}

	@Override
	public boolean deleteGoodsCategory(String categoryId) {
		if (categoryId == null) return false;
		try {
			byte[] categoryIdb = GUID.valueOf(categoryId).toBytes();
			int resultC = baseDAO.execteBulk("delete from GoodsCategoryPo where recid=?", categoryIdb);
			baseDAO.execteBulk("delete from CategoryPropertiesPo where categoryid=?", categoryIdb);
			baseDAO.execteBulk("delete from CategoryPvaluesPo where categoryid=?", categoryIdb);
			return resultC == 1 ? true : false;
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
			categoryForm.setCreatorid(null == categoryPo.getCreatorid() ? null : GUID.valueOf(categoryPo.getCreatorid()).toString());
			categoryForm.setParentid(null == categoryPo.getParentid() ? null : GUID.valueOf(categoryPo.getParentid()).toString());
			categoryForm.setRecid(GUID.valueOf(categoryPo.getRecid()).toString());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		List<CategoryPropertiesPo> propertiesList = baseDAO.getGenericByHql("from CategoryPropertiesPo as t where t.categoryid=? order by t.ordinal asc", GUID.tryValueOf(categoryId).toBytes());
		CategoryPropertiesForm[] properties = new CategoryPropertiesForm[propertiesList.size()];
		try {
			for (int propertyIndex = 0; propertyIndex < propertiesList.size(); propertyIndex++) {
				CategoryPropertiesPo propertyPo = propertiesList.get(propertyIndex);
				CategoryPropertiesForm propertyForm = new CategoryPropertiesForm();
				BeanUtils.copyProperties1(propertyPo, propertyForm);
				propertyForm.setCategoryid(categoryId);
				propertyForm.setRecid(GUID.valueOf(propertyPo.getRecid()).toString());
				List<CategoryPvaluesPo> valueList = baseDAO.getGenericByHql("from CategoryPvaluesPo as t where t.propertyid=? order by t.ordinal asc", propertyPo.getRecid());
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
		} catch(Exception e) {
			e.printStackTrace();
		}
		categoryForm.setPropertiesForm(properties);
		return categoryForm;
	}

	@Override
	public List<GoodsCategoryVo> getGoodsCateogryListByLevel(int levelNo) {
		List<GoodsCategoryVo> categoryVoList = new ArrayList<GoodsCategoryVo>();
		List<GoodsCategoryPo> childrenList = baseDAO.getGenericByHql("from GoodsCategoryPo as t where t.levelno = '" + levelNo + "' order by t.categoryno");
		try {
			for (GoodsCategoryPo categoryPo : childrenList) {
				GoodsCategoryVo categoryVo = new GoodsCategoryVo();
				BeanUtils.copyProperties1(categoryPo, categoryVo);
				categoryVo.setParentid(categoryPo.getParentid() == null ? null : GUID.valueOf(categoryPo.getParentid()).toString());
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
		List<GoodsCategoryPo> childrenList = baseDAO.getGenericByHql("from GoodsCategoryPo as t where t.parentid = ? order by t.categoryno", GUID.tryValueOf(categoryId).toBytes());
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

	@Override
	public GoodsCategoryTree getGoodsCategoryTree() {
		GoodsCategoryTree categoryTree = new GoodsCategoryTree();
		List<GoodsCategoryPo> rootCategoryList = new ArrayList<GoodsCategoryPo>();
		try {
			rootCategoryList = baseDAO.getGenericByHql("from GoodsCategoryPo as t where t.levelno = 1 order by t.categoryno");
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
			for (GoodsCategoryPo categoryPo : rootCategoryList) {
				GoodsCategoryTree.CategoryNode rootNode = categoryTree.new CategoryNode();
				GoodsCategoryTree.Attribute attributes = categoryTree.new Attribute();
				rootNode.setCategoryNo(categoryPo.getCategoryno());
				rootNode.setText(categoryPo.getCategoryname());
				attributes.setLevelNo(categoryPo.getPath().split(";").length);
				long childrenCount = baseDAO.getGenericCountByHql("select count(*) from CategoryPropertiesPo as t where t.categoryid=?", categoryPo.getRecid());
				attributes.setPropertied(childrenCount > 0);
				rootNode.setAttributes(attributes);
				// rootNode.setParent(null);
				rootNode.setId(GUID.valueOf(categoryPo.getRecid()).toString());
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
		List<GoodsCategoryPo> childrenList = baseDAO.getGenericByHql("from GoodsCategoryPo as t where t.parentid = ?  order by t.categoryno", GUID.tryValueOf(node.getId()).toBytes());
		if (childrenList.size() < 1) {
			node.setChildren(null);
			return;
		}
		GoodsCategoryTree.CategoryNode[] childrenNodes = new GoodsCategoryTree.CategoryNode[childrenList.size()];
		int index = 0;
		for (GoodsCategoryPo child : childrenList) {
			GoodsCategoryTree.CategoryNode childNode = categoryTree.new CategoryNode();
			GoodsCategoryTree.Attribute attributes = categoryTree.new Attribute();
			childNode.setCategoryNo(child.getCategoryno());
			childNode.setText(child.getCategoryname());
			attributes.setLevelNo(child.getPath().split(";").length);
			long childrenCount = baseDAO.getGenericCountByHql("select count(*) from CategoryPropertiesPo as t where t.categoryid=?", child.getRecid());
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
		rootCategoryList = baseDAO.getGenericByHql("from EGoodsCategoryPo where parentid=? order by createdate desc", EGoodsCategoryTree.ERP_ROOT_ID);
		// rootCategoryList = baseDAO.getGenericByHql("from EGoodsCategoryPo");
		if (rootCategoryList.size() < 1) {
			eCategoryTree.setChildren(null);
			return eCategoryTree;
		}
		EGoodsCategoryTree.CategoryNode[] rootNodes = new EGoodsCategoryTree.CategoryNode[rootCategoryList.size()];
		int index = 0;
		for (EGoodsCategoryPo categoryPo : rootCategoryList) {
			EGoodsCategoryTree.CategoryNode rootNode = eCategoryTree.new CategoryNode();
			rootNode.setCategoryNo(categoryPo.getCategoryno());
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
		List<EGoodsCategoryPo> childrenList = baseDAO.getGenericByHql("from EGoodsCategoryPo as t where t.parentid = ? order by t.createdate desc", GUID.tryValueOf(node.getId()).toBytes());
		if (childrenList.size() < 1) {
			node.setChildren(null);
			return;
		}
		EGoodsCategoryTree.CategoryNode[] childrenNodes = new EGoodsCategoryTree.CategoryNode[childrenList.size()];
		int index = 0;
		for (EGoodsCategoryPo child : childrenList) {
			EGoodsCategoryTree.CategoryNode childNode = categoryTree.new CategoryNode();
			childNode.setCategoryNo(child.getCategoryno());
			childNode.setText(child.getCategoryname());
			// childNode.setParent(node);
			childNode.setId(GUID.valueOf(child.getRecid()).toString());
			setGoodsCategoryChildren(categoryTree, childNode);
			childrenNodes[index] = childNode;
			index++;
		}
		node.setChildren(childrenNodes);
	}

	@Override
	public List<GoodsCategoryVo> getAllGoodsCategoryList() {
		// TODO Auto-generated method stub
		return null;
	}
*/
}
