/**
 * 
 */
package com.spark.cms.services.freeDelivery;

import com.spark.cms.common.DataModel;
import com.spark.cms.services.ServiceMessage;
import com.spark.cms.services.form.Login;
import com.spark.cms.services.form.delivery.DeliveryForm;
import com.spark.cms.services.vo.DeliveryPriceVo;

/**
 * @author Jideas
 * 
 */
public interface DeliveryPriceService {

	/**
	 * �õ�����
	 * 
	 * @return
	 */
	Double getPrice();

	/**
	 * ���浥��
	 * 
	 * @param price
	 * @param login
	 * @return
	 */
	ServiceMessage createPurPrice(double price, Login login);

	/**
	 * ��ѯ�����б�
	 * 
	 * @param key
	 * 
	 * @return
	 */
	DataModel<DeliveryForm> getDeliveryPriceList(GetDeliveryListKey key);

	/**
	 * ��ѯ����������Ϣ
	 * 
	 * @param recid
	 * @return
	 */
	DeliveryForm findDeliveryPrice(String recid);

	/**
	 * ��ѯ����������Ϣ
	 * 
	 * @param recid
	 * @return
	 */
	DeliveryPriceVo findDelivery(String recid);

	/**
	 * ���淽��
	 * 
	 * @param price
	 * @param login
	 * @return
	 */
	ServiceMessage createOrModify(DeliveryPriceVo vo, Login login);

	/**
	 * ͣ�÷���
	 * 
	 * @param price
	 * @param login
	 * @return
	 */
	ServiceMessage exeStopDeliveryPrice(String recid, Login login);

	/**
	 * ���÷���
	 * 
	 * @param price
	 * @param login
	 * @return
	 */
	ServiceMessage exeActiveDeliveryPrice(String recid, Login login);

}
