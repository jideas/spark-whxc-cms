/**
 * 
 */
package com.spark.cms.services.card.schedule;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.spark.base.hibernate.GenericDAO;
import com.spark.cms.base.constant.card.CardStatus;

/**
 * @author Jideas
 * 
 */
@Component
public class CardSchedule {

	@Autowired
	private GenericDAO baseDAO;

	@Scheduled(cron = "0 0 0 ? * 1-7")
	public void changeTimeOutPmtStatus() {
		StringBuilder ss = new StringBuilder("update CardPo as t set");
		ss.append(" status=");
		ss.append("'" + CardStatus.Canceled.getCode() + "'");
		ss.append(",");
		ss.append("stopdate=?");
		ss.append(" where ");
		ss.append(" t.lastDate<?");
		ss.append(" and ");
		ss.append(" t.status <> ?");
		int count = this.baseDAO.execteBulk(ss.toString(), new Date(), new Date().getTime(), CardStatus.Canceled
				.getCode());
		if (count > 0) {
			System.out.println("更新了" + count + "条已过期的面值卡状态");
		}
	}
}
