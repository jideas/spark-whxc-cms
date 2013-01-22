package com.spark.cms.action.common;

import java.util.List;

import com.spark.cms.services.vo.GoodsVo;

public class SearchResultInfo {
	private int totalCount = 0;
	private List<GoodsVo> goodsList = null;
	private List<CategorySummary> categorySummarys = null;
	private String searchKey = null;
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public List<GoodsVo> getGoodsList() {
		return goodsList;
	}
	public void setGoodsList(List<GoodsVo> goodsList) {
		this.goodsList = goodsList;
	}
	public String getSearchKey() {
		return searchKey;
	}
	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}
	public List<CategorySummary> getCategorySummarys() {
		return categorySummarys;
	}
	public void setCategorySummarys(List<CategorySummary> categorySummarys) {
		this.categorySummarys = categorySummarys;
	}
	
}
