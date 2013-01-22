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
	 * �������޸Ĵ���
	 */
	ServiceMessage createOrModifyPmt(CardPromotionVo vo, Login login);

	/**
	 * ͣ�ô���
	 */
	ServiceMessage exeStopPromotion(String recid, Login login);

	/**
	 * ���ô���
	 */
	ServiceMessage exeActivePromotion(String recid, Login login);

	/**
	 * ����id��ѯ����
	 */
	CardPromotionVo findCardPromotion(String recid);

	/**
	 * ����id��ѯ����
	 */
	CardPromotionForm findCardPmtForm(String recid);

	/**
	 * �õ�ȫ����ֵ����
	 */
	DataModel<CardPromotionForm> getList(GetCardPmtListKey key);

	/**
	 * ��ѯ�����Ĵ���
	 */
	CardPromotionVo findByCardAmount(double amount);

}
