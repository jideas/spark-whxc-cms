/**
 * 
 */
package com.spark.cms.services.CardPromotion;

import com.spark.cms.common.DataModel;
import com.spark.cms.services.ServiceMessage;
import com.spark.cms.services.form.Login;
import com.spark.cms.services.form.promotion.CardPromotionForm;
import com.spark.cms.services.vo.CardPromotionVo;

/**
 * @author Jideas
 * 
 */
public interface CardPmtService {

	/**
	 * 创建或修改促销
	 */
	ServiceMessage createOrModifyPmt(CardPromotionVo vo, Login login);

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
	CardPromotionVo findCardPromotion(String recid);

	/**
	 * 根据id查询促销
	 */
	CardPromotionForm findCardPmtForm(String recid);

	/**
	 * 得到全部充值促销
	 */
	DataModel<CardPromotionForm> getList(GetCardPmtListKey key);

	/**
	 * 查询订单的促销
	 */
	CardPromotionVo findByCardAmount(double amount);

}
