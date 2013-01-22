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
	 * ���طֶδ�����Ϣ
	 */
	public OrderPromotionVo getVo() {
		return vo;
	}

	/**
	 * �Ƿ���������ͻ�����
	 */
	public boolean isFreeDelivery() {
		return isFreeDelivery;
	}

}
