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
	 * ������Ʒ����
	 * @param cateogryVo
	 */
	public void createGoodsCategory(UserExtForm loginUser, String categoryNo, String categoryName, String parentId);
	
	/**
	 * ������Ʒ��������
	 * @param categoryId
	 * @param name
	 */
	public boolean modifyGoodsCategoryName(UserExtForm loginUser, String categoryId, String categoryNo, String name);
	
	/**
	 * ������Ʒ������Ϣ
	 * @param cateogryVo
	 * @return
	 */
	public boolean modifyGoodsCategoryProperties(Login login, CategoryPropertiesForm[] properties);
	
	/**
	 * ɾ��ָ����Ʒ����
	 * @param categoryId
	 */
	public boolean deleteGoodsCategory(String categoryId);
	
	public int isCategoryExist(String parentId, String categoryNo, String categoryName, String categoryId);
	
	/**
	 * ����IDȡ�ö�Ӧ�ķ�����Ϣ
	 * @param categoryId
	 * @return
	 */
	public GoodsCategoryForm getGoodsCategoryById(String categoryId);
	/**
	 * ����IDȡ�ö�Ӧ�ķ�����Ϣ(����������)
	 * @param categoryId
	 * @return
	 */
	public GoodsCategoryVo getGoodsCategorySimpleInfoById(String categoryId);
	
	/**
	 * ��ѯָ�����εķ����б�
	 * @param levelNo
	 * @return
	 */
	public List<GoodsCategoryVo> getGoodsCateogryListByLevel(int levelNo);
	
	/**
	 * ��ѯָ����Ʒ������ӷ����б�
	 * @param categoryId
	 * @return
	 */
	public List<GoodsCategoryVo> getChildrenCategoryList(String categoryId);
	
	/**
	 * �����Ʒ������
	 * @return
	 */
	@Deprecated
	public GoodsCategoryTree getGoodsCategoryTree();
	
	/**
	 * �����Ʒ���������ӽ��
	 * @param parentId
	 * @return
	 */
	public GoodsCategoryTree getGoodsCategoryTreeNode(String parentId);
	/**
	 * �����Ʒ������������ֻ��id��name��
	 * @return
	 */
	public LightGoodsCategoryTree getLightGoodsCategoryTree();
	/**
	 * ȡ��������Ʒ�����б�������Դ��
	 * @return
	 */
	public List<GoodsCategoryVo> getAllGoodsCategoryList();
	/**
	 * ���ERP����Ʒ������
	 * @return
	 */
	@Deprecated
	public EGoodsCategoryTree getEGoodsCategoryTree();
	/**
	 * ���ERP����Ʒ���������ӽ��
	 * @param parentId
	 * @return
	 */
	public EGoodsCategoryTree getEGoodsCategoryTreeNode(String parentId);
}
