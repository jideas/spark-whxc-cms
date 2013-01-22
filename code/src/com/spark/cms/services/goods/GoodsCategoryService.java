package com.spark.cms.services.goods;

import java.util.List;

import com.spark.cms.services.form.CategoryPropertiesForm;
import com.spark.cms.services.form.GoodsCategoryForm;
import com.spark.cms.services.form.Login;
import com.spark.cms.services.form.UserExtForm;
import com.spark.cms.services.vo.EGoodsCategoryTree;
import com.spark.cms.services.vo.GoodsCategoryTree;
import com.spark.cms.services.vo.GoodsCategoryVo;
import com.spark.cms.services.vo.LightGoodsCategoryTree;

public interface GoodsCategoryService {
	
	/**
	 * 创建商品分类
	 * @param cateogryVo
	 */
	public void createGoodsCategory(UserExtForm loginUser, String categoryNo, String categoryName, String parentId);
	
	/**
	 * 更新商品分类名称
	 * @param categoryId
	 * @param name
	 */
	public boolean modifyGoodsCategoryName(UserExtForm loginUser, String categoryId, String categoryNo, String name);
	
	/**
	 * 更新商品分类信息
	 * @param cateogryVo
	 * @return
	 */
	public boolean modifyGoodsCategoryProperties(Login login, CategoryPropertiesForm[] properties);
	
	/**
	 * 删除指定商品分类
	 * @param categoryId
	 */
	public boolean deleteGoodsCategory(String categoryId);
	
	public int isCategoryExist(String parentId, String categoryNo, String categoryName, String categoryId);
	
	/**
	 * 根据ID取得对应的分类信息
	 * @param categoryId
	 * @return
	 */
	public GoodsCategoryForm getGoodsCategoryById(String categoryId);
	/**
	 * 根据ID取得对应的分类信息(不包括属性)
	 * @param categoryId
	 * @return
	 */
	public GoodsCategoryVo getGoodsCategorySimpleInfoById(String categoryId);
	
	/**
	 * 查询指定级次的分类列表
	 * @param levelNo
	 * @return
	 */
	public List<GoodsCategoryVo> getGoodsCateogryListByLevel(int levelNo);
	
	/**
	 * 查询指定商品分类的子分类列表
	 * @param categoryId
	 * @return
	 */
	public List<GoodsCategoryVo> getChildrenCategoryList(String categoryId);
	
	/**
	 * 获得商品分类树
	 * @return
	 */
	@Deprecated
	public GoodsCategoryTree getGoodsCategoryTree();
	
	/**
	 * 获得商品分类树的子结点
	 * @param parentId
	 * @return
	 */
	public GoodsCategoryTree getGoodsCategoryTreeNode(String parentId);
	/**
	 * 获得商品分类轻量树（只有id和name）
	 * @return
	 */
	public LightGoodsCategoryTree getLightGoodsCategoryTree();
	/**
	 * 取得所有商品分类列表（用于资源）
	 * @return
	 */
	public List<GoodsCategoryVo> getAllGoodsCategoryList();
	/**
	 * 获得ERP中商品分类树
	 * @return
	 */
	@Deprecated
	public EGoodsCategoryTree getEGoodsCategoryTree();
	/**
	 * 获得ERP中商品分类树的子结点
	 * @param parentId
	 * @return
	 */
	public EGoodsCategoryTree getEGoodsCategoryTreeNode(String parentId);
}
