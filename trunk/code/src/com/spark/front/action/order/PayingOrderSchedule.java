/**
 * 
 */
package com.spark.front.action.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.spark.cms.services.order.OrderService;

/**
 * @author Jideas
 *
 */
@Component
public class PayingOrderSchedule {

	@Autowired
	private OrderService orderService;
	
	@Scheduled(fixedDelay = 1800000)
	public void deleteOrders() {
		 this.orderService.deleteOrders();
	}
}
