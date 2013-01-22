/**
 * 
 */
package com.spark.cms.services.freeDelivery;

import com.spark.base.common.key.LimitKey;

/**
 * @author Jideas
 * 
 */
public class GetDeliveryListKey extends LimitKey {

	private Boolean isOnlyActiving;

	public Boolean getIsOnlyActiving() {
		return isOnlyActiving;
	}

	public void setIsOnlyActiving(Boolean isOnlyActiving) {
		this.isOnlyActiving = isOnlyActiving;
	}
}
