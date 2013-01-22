package com.spark.cms.services.channel;

import com.spark.base.common.key.LimitKey;
import com.spark.cms.base.constant.ChannelContentsStatus;

public class GetChannelGoodsListKey extends LimitKey {
	private String channelId;
	private SortColumn sortColumn = SortColumn.PUBLISHDATE;
	private String goodsCategoryId;
	
	private ChannelContentsStatus status;
	
	private double priceBegin = -1.0;
	private double priceEnd = -1.0;
	
	public enum SortColumn {
		PUBLISHDATE("publishDate"),
		PRICE("realprice"),
		SALECOUNT("saledCount");
		
		private String columnName;
		private SortColumn(String columnName) {
			this.columnName = columnName;
		}
		
		public String getColumnName() {
			return this.columnName;
		}
	}
	
	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	public SortColumn getSortColumn() {
		return sortColumn;
	}

	public void setSortColumn(SortColumn sortColumn) {
		this.sortColumn = sortColumn;
	}

	public ChannelContentsStatus getStatus() {
		return status;
	}

	public void setStatus(ChannelContentsStatus status) {
		this.status = status;
	}

	public String getGoodsCategoryId() {
		return goodsCategoryId;
	}

	public void setGoodsCategoryId(String goodsCategoryId) {
		this.goodsCategoryId = goodsCategoryId;
	}

	public double getPriceBegin() {
		return priceBegin;
	}

	public void setPriceBegin(double priceBegin) {
		this.priceBegin = priceBegin;
	}

	public double getPriceEnd() {
		return priceEnd;
	}

	public void setPriceEnd(double priceEnd) {
		this.priceEnd = priceEnd;
	}
	
}
