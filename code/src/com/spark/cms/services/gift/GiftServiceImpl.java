/**
 * 
 */
package com.spark.cms.services.gift;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spark.base.hibernate.GenericDAO;
import com.spark.cms.base.utils.BeanCopy;
import com.spark.cms.dao.po.GiftPo;
import com.spark.cms.services.ServiceMessage;
import com.spark.cms.services.vo.GiftVo;

/**
 * @author Jideas
 * 
 */
@Service
public class GiftServiceImpl implements GiftService {

	@Autowired
	private GenericDAO baseDAO;

	/*
	 * (non-Javadoc)新增赠品
	 * 
	 * @see com.spark.cms.services.gift.GiftService#createGift(com.spark.cms.services.vo.GiftVo)
	 */
	@Override
	public ServiceMessage createGift(GiftVo vo) {
		this.baseDAO.save(BeanCopy.copy(GiftPo.class, vo));
		return new ServiceMessage(true, "保存成功！");
	}

}
