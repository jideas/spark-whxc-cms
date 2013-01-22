/**
 * 
 */
package com.spark.cms.services.sms.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spark.base.hibernate.GenericDAO;
import com.spark.cms.base.utils.BeanCopy;
import com.spark.cms.dao.po.SmsConfigPo;
import com.spark.cms.services.sms.SmsConfigService;
import com.spark.cms.services.vo.SmsConfigVo;

/**
 * @author Jideas
 * 
 */
@Service
public class SmsConfigServiceImpl implements SmsConfigService {

	@Autowired
	private GenericDAO baseDAO;

	public SmsConfigVo findVo() {
		StringBuilder ss = new StringBuilder();
		ss.append(" from SmsConfigPo as t ");
		try{
		List<SmsConfigPo> polist = this.baseDAO.getGenericByHql(ss.toString());
		if (null == polist || polist.isEmpty()) {
			return null;
		}
		return BeanCopy.copy(SmsConfigVo.class, polist.get(0));
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
}
