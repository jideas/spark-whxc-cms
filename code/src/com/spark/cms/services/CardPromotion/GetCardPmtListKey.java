/**
 * 
 */
package com.spark.cms.services.CardPromotion;

import com.spark.base.common.key.LimitKey;

/**
 * @author Jideas
 * 
 */
public class GetCardPmtListKey extends LimitKey {

	private Boolean onlyActiving;

	public Boolean getOnlyActiving() {
		return onlyActiving;
	}

	public void setOnlyActiving(Boolean onlyActiving) {
		this.onlyActiving = onlyActiving;
	}
 

}
