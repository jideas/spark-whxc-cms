package com.spark.cms.services.orderPromotion.result;

import com.spark.cms.services.vo.OrderPromotionVo;

public class OrderPromotionResult {

	private OrderPromotionVo vo;

	private boolean isFreeDelivery;

	public OrderPromotionResult(OrderPromotionVo vo, boolean isFreeDelivery) {
		this.vo = vo;
		this.isFreeDelivery = isFreeDelivery;
	}

	/**
	 * 返回分段促销信息
	 */
	public OrderPromotionVo getVo() {
		return vo;
	}

	/**
	 * 是否满足免费送货上门
	 */
	public boolean isFreeDelivery() {
		return isFreeDelivery;
	}

}
