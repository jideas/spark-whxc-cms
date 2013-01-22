/**
 * 
 */
package com.spark.cms.services.orderPromotion;

import com.spark.base.common.key.LimitKey;

/**
 * @author Jideas
 * 
 */
public class GetOrderPmtListKey extends LimitKey {

	private Boolean activingOnly;

	public Boolean getActivingOnly() {
		return activingOnly;
	}

	public void setActivingOnly(Boolean activingOnly) {
		this.activingOnly = activingOnly;
	}
}
