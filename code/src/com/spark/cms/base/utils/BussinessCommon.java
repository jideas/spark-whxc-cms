package com.spark.cms.base.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.spark.cms.action.common.CategorySummary;
import com.spark.cms.services.goods.GoodsCategoryService;
import com.spark.cms.services.vo.GoodsCategoryVo;
import com.spark.cms.services.vo.GoodsVo;

public class BussinessCommon {
	
	public static List<CategorySummary> getCategorySummaryByGoodsList(List<GoodsVo> goodsList, GoodsCategoryService categoryService) {
		// level 3 summary
		Map<String, CategorySummary> level3SummaryMap = new HashMap<String, CategorySummary>();
		CategorySummary categorySummary = null;
		for (GoodsVo goods : goodsList) {
			categorySummary = level3SummaryMap.get(goods.getCategoryid1());
			if (null == categorySummary) {
				categorySummary = new CategorySummary();
				GoodsCategoryVo category1 = categoryService.getGoodsCategorySimpleInfoById(goods.getCategoryid1());
				GoodsCategoryVo category2 = categoryService.getGoodsCategorySimpleInfoById(goods.getCategoryid2());
				if (category1 == null || category2 == null) continue;
				categorySummary.setCategory(category1);
				categorySummary.setParent(category2);
				level3SummaryMap.put(goods.getCategoryid1(), categorySummary);
			}
			categorySummary.addGoodsCount(1);
		}
		// level 2 summary
		Map<String, CategorySummary> level2SummaryMap =  new HashMap<String, CategorySummary>();
		Iterator<String> lavel3It = level3SummaryMap.keySet().iterator();
		CategorySummary level2Summary = null;
		while(lavel3It.hasNext()) {
			CategorySummary level3Summary = level3SummaryMap.get(lavel3It.next());
			level2Summary = level2SummaryMap.get(level3Summary.getParent().getRecid());
			if (null == level2Summary) {
				level2Summary = new CategorySummary();
				level2Summary.setCategory(level3Summary.getParent());
				level2SummaryMap.put(level3Summary.getParent().getRecid(), level2Summary);
			}
			level2Summary.addChild(level3Summary);
			level2Summary.addGoodsCount(level3Summary.getGoodsCount());
		}
		
		// convert level 2 summary to list
		List<CategorySummary> summaryList = new ArrayList<CategorySummary>();
		Iterator<String> level2It = level2SummaryMap.keySet().iterator();
		while(level2It.hasNext()) {
			summaryList.add(level2SummaryMap.get(level2It.next()));
		}
		return summaryList;
	}
}
