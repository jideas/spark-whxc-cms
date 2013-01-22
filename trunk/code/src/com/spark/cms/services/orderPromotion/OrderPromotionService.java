/**
 * 
 */
package com.spark.cms.services.orderPromotion;

import com.spark.cms.common.DataModel;
import com.spark.cms.services.ServiceMessage;
import com.spark.cms.services.form.Login;
import com.spark.cms.services.form.promotion.OrderPromotionForm;
import com.spark.cms.services.orderPromotion.result.OrderPromotionResult;
import com.spark.cms.services.vo.OrderPromotionVo;

/**
 * @author Jideas
 * 
 */
public interface OrderPromotionService {

	/**
	 * 创建或修改促销
	 */
	ServiceMessage createOrModifyPmt(OrderPromotionVo vo, Login login);

	/**
	 * 停用促销
	 */
	ServiceMessage  exeStopPromotion(String recid, Login login);

	/**
	 * 启用促销
	 */
	ServiceMessage  exeActivePromotion(String recid, Login login);

	/**
	 * 根据id查询促销
	 */
	OrderPromotionVo findOrderPromotion(String recid);
	/**
	 * 根据id查询促销
	 */
	OrderPromotionForm findOrderPmtForm(String recid);

	/**
	 * 得到全部订单促销
	 */
	DataModel<OrderPromotionForm> getList(GetOrderPmtListKey key);

	/**
	 * 查询订单的促销
	 */
	OrderPromotionResult findByOrderAmount(double amount);

}
