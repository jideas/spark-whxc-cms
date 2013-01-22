/**
 * 
 */
package com.spark.cms.services.form.promotion;

/**
 * @author Jideas
 * 
 */
public class GoodsPromotionForm {

	private String recid, goodsId, goodsName, beginTime, endTime, count, salesCount, disRate, status,
			reserveCount;

	public String getRecid() {
		return recid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public String getCount() {
		return count;
	}

	public String getSalesCount() {
		return salesCount;
	}

	public String getDisRate() {
		return disRate;
	}

	public void setRecid(String recid) {
		this.recid = recid;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public void setSalesCount(String salesCount) {
		this.salesCount = salesCount;
	}

	public void setDisRate(String disRate) {
		this.disRate = disRate;
	}

	public String getReserveCount() {
		return reserveCount;
	}

	public void setReserveCount(String reserveCount) {
		this.reserveCount = reserveCount;
	}
}
