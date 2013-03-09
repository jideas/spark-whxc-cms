/**
 * 
 */
package com.spark.front.action.payment.logs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spark.base.hibernate.GenericDAO;
import com.spark.base.type.GUID;
import com.spark.cms.base.utils.BeanCopy;
import com.spark.cms.dao.po.PayingLogPo;
import com.spark.cms.services.vo.PayingLogVo;

/**
 * ≤Â»Î»’÷æ
 * 
 * @author jideas
 */
@Service
public class PayingBillsLogsServiceImpl implements PayingBillsLogsService {

	@Autowired
	private GenericDAO baseDAO;

	@Override
	public void insert(PayingLogVo vo) {
		vo.setRecid(GUID.randomID().toString());
		this.baseDAO.save(BeanCopy.copy(PayingLogPo.class, vo));
	}
}
