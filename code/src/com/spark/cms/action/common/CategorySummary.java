package com.spark.cms.action.common;

import java.util.ArrayList;
import java.util.List;

import com.spark.cms.services.vo.GoodsCategoryVo;

public class CategorySummary {
	private GoodsCategoryVo category;
	private GoodsCategoryVo parent;
	private List<CategorySummary> childrenList;
	private int goodsCount;
	
	public GoodsCategoryVo getCategory() {
		return category;
	}
	public void setCategory(GoodsCategoryVo category) {
		this.category = category;
	}
	public GoodsCategoryVo getParent() {
		return parent;
	}
	public void setParent(GoodsCategoryVo parent) {
		this.parent = parent;
	}
	
	public List<CategorySummary> getChildrenList() {
		return childrenList;
	}
	public void setChildrenList(List<CategorySummary> childrenList) {
		this.childrenList = childrenList;
	}
	public void addChild(CategorySummary child) {
		if (null == childrenList) childrenList = new ArrayList<CategorySummary>();
		childrenList.add(child);
	}
	
	
	public int getGoodsCount() {
		return goodsCount;
	}
	public void setGoodsCount(int goodsCount) {
		this.goodsCount = goodsCount;
	}
	public void addGoodsCount(int goodsCount) {
		this.goodsCount += goodsCount;
	}
}
