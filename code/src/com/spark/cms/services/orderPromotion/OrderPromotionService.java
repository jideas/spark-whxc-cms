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
	 * �������޸Ĵ���
	 */
	ServiceMessage createOrModifyPmt(OrderPromotionVo vo, Login login);

	/**
	 * ͣ�ô���
	 */
	ServiceMessage  exeStopPromotion(String recid, Login login);

	/**
	 * ���ô���
	 */
	ServiceMessage  exeActivePromotion(String recid, Login login);

	/**
	 * ����id��ѯ����
	 */
	OrderPromotionVo findOrderPromotion(String recid);
	/**
	 * ����id��ѯ����
	 */
	OrderPromotionForm findOrderPmtForm(String recid);

	/**
	 * �õ�ȫ����������
	 */
	DataModel<OrderPromotionForm> getList(GetOrderPmtListKey key);

	/**
	 * ��ѯ�����Ĵ���
	 */
	OrderPromotionResult findByOrderAmount(double amount);

}
