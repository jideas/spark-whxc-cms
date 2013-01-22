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
	 * �����µĴ���
	 */
	ServiceMessage createOrModify(GoodsPromotionVo po, Login login);

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
	GoodsPromotionVo findGoodsPromotion(String recid);
	/**
	 * ����id��ѯ����
	 */
	GoodsPromotionForm findGoodsPmtForm(String recid);

	/**
	 * ��Ʒ�����б�
	 */
	DataModel<GoodsPromotionForm> getFormList(GetGoodsPromotionListKey key);

	/**
	 * �����Ʒ���������Ƿ����ظ�
	 * 
	 * @return true Ϊ���ظ�
	 */
	ServiceMessage checkPromotionDouble(String goodsId, String recid);

	/**
	 * ��ѯ��Ʒ�Ĵ���
	 */
	GoodsPromotionVo findByGoodsId(String goodsId);

	/**
	 * ����������Ʒ����
	 */
	ServiceMessage changeSaledCount(String goodsId, double count);

}
