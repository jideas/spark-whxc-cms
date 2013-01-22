/**
 * 
 */
package com.spark.cms.services.joinus;

import com.spark.base.common.key.LimitKey;

/**
 * @author Jideas
 *
 */
public class GetJoinSevenBillsKey extends LimitKey {

	private Boolean onlyOpering;

	public Boolean getOnlyOpered() {
		return onlyOpering;
	}

	public void setOnlyOpered(Boolean onlyOpered) {
		this.onlyOpering = onlyOpered;
	}
}
