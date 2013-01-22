/**
 * 
 */
package com.spark.front.action.goods;

/**
 * @author Jideas
 * 
 */
public enum GoodsKey {
	
	GoodsType,
	
	CategoryNavigator,

	GoodsName,

	GoodsCode,

	GoodsSpec,

	GoodsPrice,

	GoodsOldPrice,

	GoodsId,

	GoodsImageUrl,

	SpecSelector,

	PromotionInfo,

	VantegesRule,

	GoodsContentTitles,

	GoodsContentHtmls,
	
	SameCategoryPopularGoods,
	
	IsBooking

	;

	@Override
	public String toString() {
		return this.name();
	}
}
