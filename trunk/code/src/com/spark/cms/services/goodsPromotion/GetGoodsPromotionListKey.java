/**
 * 
 */
package com.spark.cms.services.goodsPromotion;

import com.spark.base.common.key.LimitKey;
import com.spark.cms.base.constant.promotion.PromotionType;

/**
 * @author Jideas
 * 商品促销列表查询key
 */
public class GetGoodsPromotionListKey extends LimitKey {

	private PromotionType ptype;

	private Boolean isOnlyActiving;

	private String searchText;

	public PromotionType getPtype() {
		return ptype;
	}

	public Boolean isOnlyActiving() {
		return isOnlyActiving;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setPtype(PromotionType ptype) {
		this.ptype = ptype;
	}

	public void setOnlyActiving(Boolean isOnlyActiving) {
		this.isOnlyActiving = isOnlyActiving;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
}
