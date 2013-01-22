package com.spark.cms.common.generate;

import java.util.List;

import com.spark.cms.services.form.GoodsCategoryForm;

public class CategoryNavigatorGen {
	
	
	public static String fillCategoryNavigator(List<GoodsCategoryForm> items, String path) {
		String str = getIndex(path);
		for(int i=0;i<items.size();i++){
			GoodsCategoryForm form = items.get(i);
			if(i==items.size()-1){
				str += getSpan(form.getRecid(),form.getCategoryname());
			}else{
				str += getAIndex(form.getRecid(),form.getCategoryname(),path);
			}
			if(i!=items.size()-1){
				str += getspanr();
			}
		}
		return str;
	}
	
	private static String getAIndex(String id,String name,String path){
		String togo = path+"/front/toCategoryPage/"+id;
		return "<span><a class=\"canClickSpan\" href='"+togo+ "'>"+name+"</a></span>";
	}
	
	private static String getIndex(String path){
		return "<span><a class=\"canClickSpan\" href=\""+path+"\">Ê×Ò³</a></span><span>&gt;</span>";
	}
	
	private static String getSpan(String id,String name){
		return "<span>"+name+"</span>";
	}
	
	private static String getspanr(){
		return "<span>&gt;</span>";
	}

}
