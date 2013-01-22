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
	 * 得到单价
	 * 
	 * @return
	 */
	Double getPrice();

	/**
	 * 保存单价
	 * 
	 * @param price
	 * @param login
	 * @return
	 */
	ServiceMessage createPurPrice(double price, Login login);

	/**
	 * 查询方案列表
	 * 
	 * @param key
	 * 
	 * @return
	 */
	DataModel<DeliveryForm> getDeliveryPriceList(GetDeliveryListKey key);

	/**
	 * 查询单个方案信息
	 * 
	 * @param recid
	 * @return
	 */
	DeliveryForm findDeliveryPrice(String recid);

	/**
	 * 查询单个方案信息
	 * 
	 * @param recid
	 * @return
	 */
	DeliveryPriceVo findDelivery(String recid);

	/**
	 * 保存方案
	 * 
	 * @param price
	 * @param login
	 * @return
	 */
	ServiceMessage createOrModify(DeliveryPriceVo vo, Login login);

	/**
	 * 停用方案
	 * 
	 * @param price
	 * @param login
	 * @return
	 */
	ServiceMessage exeStopDeliveryPrice(String recid, Login login);

	/**
	 * 启用方案
	 * 
	 * @param price
	 * @param login
	 * @return
	 */
	ServiceMessage exeActiveDeliveryPrice(String recid, Login login);

}
