/**
 * 
 */
package com.spark.cms.services.payment;

import java.util.List;

import com.spark.cms.services.ServiceMessage;
import com.spark.cms.services.vo.PayingBillsVo;
import com.spark.cms.services.vo.PayingLogVo;

/**
 * @author Jideas
 * 
 */
public interface PayingBillsService {

	boolean createPayingBill(PayingBillsVo vo);

	boolean deleteOldBill();

	PayingBillsVo findPayingBill(String recid);
	PayingBillsVo findPayingBillByOrderNo(String recid);

	List<PayingBillsVo> getAllList();

	/**
	 * @param vo
	 * @param status
	 * @return
	 * @throws ServiceMessage
	 * @throws Throwable
	 */
	boolean exeUpdateStatus(PayingBillsVo vo, String status,boolean must) throws ServiceMessage;
	

	void insertLog(PayingLogVo vo);
}
