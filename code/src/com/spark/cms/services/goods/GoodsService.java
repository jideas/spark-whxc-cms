package com.spark.cms.services.goods;

import java.util.List;

import com.spark.base.common.system.dic.DicItem;
import com.spark.cms.services.ServiceMessage;
import com.spark.cms.services.form.GoodsForm;
import com.spark.cms.services.form.UserExtForm;
import com.spark.cms.services.vo.EGoodsVo;
import com.spark.cms.services.vo.GoodsContentVo;
import com.spark.cms.services.vo.GoodsVo;

public interface GoodsService {

	/**
	 * 创建商品(将ERP中的商品复制到网站商品中，只复制基础信息部分)
	 * 
	 * @param goodsForm
	 * @return
	 */
	public boolean createGoods(String categoryId, GoodsVo goodsVo);

	/**
	 * 创建商品(将指定的ERP中的商品复制到网站商品中，只复制基础信息部分)
	 * @param erpGoodsIds
	 * @return
	 */
	public void createGoodsFromERP(String categoryId, String[] erpGoodsIds);
	/**
	 * 获得商品的基础信息
	 * 
	 * @param goodsId
	 * @return
	 */
	public GoodsVo getGoodsVo(String goodsId);

	/**
	 * 修改商品的基础信息（暂时只支持修改商品属性）
	 * 
	 * @param goodsId
	 * @param task
	 * @return
	 */
	public boolean modifyGoodsVo(ModifyGoodsVoTask task);
	
	/**
	 * 更改商品促销状态
	 * @param goodsId
	 * @param isPromotion
	 * @return
	 */
	public boolean modifyGoodsPromotionStatus(String goodsId, boolean isPromotion);

	/**
	 * 修改商品详情
	 * 
	 * @param goodsForm
	 * @return
	 * @throws ServiceMessage
	 */
	public void modifyGoods(GoodsForm goodsForm, UserExtForm loginUser) throws ServiceMessage;

	/**
	 * 修改商品销售数量（累加）
	 * @param goodsId
	 * @param changeCount 改变的数量
	 */
	public boolean modifyGoodsSaleCount(String goodsId, double changeCount);
	
	/**
	 * 获得商品详情
	 * 
	 * @param goodsId
	 * @return
	 */
	public GoodsForm getGoodsForm(String goodsId);

	/**
	 * 删除指定商品
	 * 
	 * @param goodsRecid
	 * @return
	 */
	public boolean deleteGoods(String goodsRecid);

	/**
	 * 查询商品列表
	 * 
	 * @param key
	 * @return
	 */
	public List<GoodsVo> getGoodsList(GetGoodsListKey key);

	/**
	 * 获得商品总数量
	 * 
	 * @param key
	 * @return
	 */
	public int getGoodsTotalCount(GetGoodsListKey key);

	/**
	 * 查询商品内容列表
	 */
	List<GoodsContentVo> getGoodsContentList(String goodsId);

	/**
	 * 获得ERP系统中的商品
	 * 
	 * @return
	 */
	public List<EGoodsVo> getEGoodsList(GetEGoodsListKey key);

	/**
	 * 获得ERP系统中的商品总数
	 * 
	 * @return
	 */
	public int getEGoodsTotalCount(GetEGoodsListKey key);

	/**
	 * 获得商品积分规则列表
	 * 
	 * @return
	 */
	public List<DicItem> getVantageList();

	/**
	 * 根据商品编号查询不同规格的同一产品
	 * 
	 * @param goodsCode
	 * @return
	 */
	List<GoodsForm> getSameGoodsList(String goodsCode);

	/**
	 * 根据一级分类查询人气产品
	 * 
	 * @param goodsCode
	 * @return
	 */
	List<GoodsForm> getPopularGoodsList(String categoryId);
	

	/**
	 * 根据分类查询热销产品
	 * 
	 * @param goodsCode
	 * @return
	 */
	List<GoodsForm> getHotGoodsList(String categoryId);
	
	/**
	 * 根据分类查询热销商品
	 * @param categoryId
	 * @return
	 */
	List<GoodsVo> getHotGoodsVoList(String categoryId);
	
	/**
	 * 查询积分商品城商品
	 * @param key
	 * @return
	 */
	List<GoodsVo> getVantageGoodsList(GetVantageGoodsListKey key);

}
