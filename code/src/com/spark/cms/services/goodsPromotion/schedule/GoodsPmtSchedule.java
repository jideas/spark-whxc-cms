/**
 * 
 */
package com.spark.cms.services.goodsPromotion.schedule;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.spark.base.hibernate.GenericDAO;

/**
 * @author Jideas
 * 
 */
@Component
public class GoodsPmtSchedule {

	@Autowired
	private GenericDAO baseDAO;

	@Scheduled(fixedDelay = 300000)
	public void changeTimeOutPmtStatus() {
		StringBuilder ss = new StringBuilder();
		ss.append("update GoodsPromotionPo as t ");
		ss.append(" set ");
		ss.append(" isactiving=? ");
		ss.append(" where t.isactiving=? ");
		ss.append(" and t.endtime<?");
		int count = this.baseDAO.execteBulk(ss.toString(), false, true, new Date().getTime());
		if(count>0){
			System.out.println("更新了" + count + "条已过期的促销方案状态");
		}
	}
}
