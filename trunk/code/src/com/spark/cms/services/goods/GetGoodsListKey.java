package com.spark.cms.services.goods;

import org.hibernate.annotations.common.util.StringHelper;

import com.spark.base.common.key.LimitKey;
import com.spark.base.type.GUID;
import com.spark.cms.services.vo.GoodsCategoryTree;

public class GetGoodsListKey extends LimitKey {
	private String goodsCategoryId = null;
	private Boolean isPublished;
	private String[] filterIds;
	private SortColumn sortColumn = SortColumn.PUBLISHDATE;
	private int offset = 0;
	
	private boolean isVantageOnly = false;
	
	private double priceBegin = -1.0;
	private double priceEnd = -1.0;
	
	private GoodsAdvanceSearch advanceSearch;
	public enum SortColumn {
		PUBLISHDATE("publishDate"),
		PRICE("realprice"),
		VANTAGESCOST("vantagesCost"),
		SALECOUNT("saledCount");
		
		private String columnName;
		private SortColumn(String columnName) {
			this.columnName = columnName;
		}
		
		public String getColumnName() {
			return this.columnName;
		}
	}

	public Boolean isPublished() {
		return isPublished;
	}

	public void setPublished(Boolean isPublished) {
		this.isPublished = isPublished;
	}

	public byte[] getGoodsCategoryId() {
		if (StringHelper.isEmpty(goodsCategoryId) || goodsCategoryId.toLowerCase().equals(GoodsCategoryTree.Root_ID.toLowerCase())) {
			return null;
		}
		return GUID.tryValueOf(goodsCategoryId).toBytes();
	}

	public void setGoodsCategoryId(String goodsCategoryId) {
		this.goodsCategoryId = goodsCategoryId;
	}
	
	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		if (offset < 0) return;
		this.offset = offset;
	}

	public SortColumn getSortColumn() {
		return sortColumn;
	}

	public void setSortColumn(SortColumn sortColumn) {
		this.sortColumn = sortColumn;
	}

	public String[] getFilterIds() {
		return filterIds;
	}

	public void setFilterIds(String[] filterIds) {
		this.filterIds = filterIds;
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

	public boolean isVantageOnly() {
		return isVantageOnly;
	}

	public void setVantageOnly(boolean isVantageOnly) {
		this.isVantageOnly = isVantageOnly;
	}

	public Boolean getIsPublished() {
		return isPublished;
	}

	public void setIsPublished(Boolean isPublished) {
		this.isPublished = isPublished;
	}

	public GoodsAdvanceSearch getAdvanceSearch() {
		return advanceSearch;
	}

	public void setAdvanceSearch(GoodsAdvanceSearch advanceSearch) {
		this.advanceSearch = advanceSearch;
	}
	
	
}
