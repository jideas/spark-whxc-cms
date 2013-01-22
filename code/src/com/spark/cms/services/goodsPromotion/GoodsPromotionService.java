/**
 * 
 */
package com.spark.cms.services.goodsPromotion;

import com.spark.cms.common.DataModel;
import com.spark.cms.services.ServiceMessage;
import com.spark.cms.services.form.Login;
import com.spark.cms.services.form.promotion.GoodsPromotionForm;
import com.spark.cms.services.vo.GoodsPromotionVo;

/**
 * @author Jideas
 * 
 */
public interface GoodsPromotionService {

	/**
	 * 创建新的促销
	 */
	ServiceMessage createOrModify(GoodsPromotionVo po, Login login);

	/**
	 * 停用促销
	 */
	ServiceMessage exeStopPromotion(String recid, Login login);

	/**
	 * 启用促销
	 */
	ServiceMessage exeActivePromotion(String recid, Login login);

	/**
	 * 根据id查询促销
	 */
	GoodsPromotionVo findGoodsPromotion(String recid);
	/**
	 * 根据id查询促销
	 */
	GoodsPromotionForm findGoodsPmtForm(String recid);

	/**
	 * 商品促销列表
	 */
	DataModel<GoodsPromotionForm> getFormList(GetGoodsPromotionListKey key);

	/**
	 * 检查商品促销类型是否已重复
	 * 
	 * @return true 为不重复
	 */
	ServiceMessage checkPromotionDouble(String goodsId, String recid);

	/**
	 * 查询商品的促销
	 */
	GoodsPromotionVo findByGoodsId(String goodsId);

	/**
	 * 更新已售商品数量
	 */
	ServiceMessage changeSaledCount(String goodsId, double count);

}
