package com.spark.cms.common.generate;

import com.spark.cms.services.vo.LightGoodsCategoryTree;

public class CategoryTreeGen {
	
	public static String getTreeHtml(LightGoodsCategoryTree tree){
		 String str = "";
		 LightGoodsCategoryTree.CategoryNode []cateoryNodeList = tree.getChildren();
		 
		 if (null == cateoryNodeList) return "";
		 for(int i=0;i<cateoryNodeList.length;i++){
			 LightGoodsCategoryTree.CategoryNode node = cateoryNodeList[i];
			 /* 修改原因：暂时不显示“营养健康”		  
			 if("227744F75612FAE43624FACBC8C3761A".equals(node.getId())) continue;
			 */
			 if(i==0){
				 str += getFirstCategoryHead(true);
			 }else{
				 str += getFirstCategoryHead(false);
			 }
			 str += getFirstCategory(node.getId(), node.getText());
			 
			 //遍历二级栏目
			 LightGoodsCategoryTree.CategoryNode []cateory2NodeList = node.getChildren();
			 if(cateory2NodeList!=null&&cateory2NodeList.length>0){
				 str += getSecondWrapBegin();
				 for(int j=0; j<cateory2NodeList.length;j++){
					 LightGoodsCategoryTree.CategoryNode node2 = cateory2NodeList[j];
					 str += getSecondCategoryHead(j==0?true:false);
					 str += getSecondCateGory(node2.getId(), node2.getText());
					  //遍历三级栏目
					 LightGoodsCategoryTree.CategoryNode []cateory3NodeList = node2.getChildren();
					 if(cateory3NodeList!=null&&cateory3NodeList.length>0){
						 str += getThirdCategoryHead();
						 for(int k=0;k<cateory3NodeList.length;k++){
							 str += getThirdCateGory(cateory3NodeList[k].getId(), cateory3NodeList[k].getText());
						 }
						 str += getThirdCategoryTail();
					 }
					 str += getSecondCategoryTail();
				 }
				 str += getSecondWrapEnd();
			 }
			 str += getFirstCategoryTail();
			 
		 }
		 return str;
	}
	private static String getFirstCategoryHead(boolean first){
		if(first){
			return "<DIV class=\"item fore\">";
		}else{
			return "<DIV class=\"item\">";
		}
	}
	
	private static String getFirstCategoryTail(){
		return "</DIV>";
	}
	
	private static String getSecondWrapBegin(){
		return "<DIV class=i-mc><DIV class=subitem>";
	}
	private static String getSecondWrapEnd(){
		return "</DIV></DIV>";
	}
	
	private static String getSecondCategoryHead(boolean first){
		if(first){
			return "<DL class='subItemFore'>";
		}else{
			return "<DL>";
		}
	}
	
	private static String getSecondCategoryTail(){
		return "</DL>";
	}
	
	private static String getThirdCategoryHead(){
		return "<DD>";
	}
	
	private static String getThirdCategoryTail(){
		return "</DD>";
	}
	
	
	private static String getFirstCategory(String code,String title){
		return "<SPAN><H3><A href=\"#\" onClick=\"javascript:toCategoryPage('"+code+"')\">"+title+"</A></H3> <S></S></SPAN>";
	}
	private static String getSecondCateGory(String code2,String title2){
		return "<DT><A href=\"#\" onClick=\"javascript:toCategoryPage('"+code2+"')\">"+title2+"</A></DT>";
	}
	private static String getThirdCateGory(String code3,String title3){
		return "<em><A href=\"#\" onClick=\"javascript:toCategoryPage('"+code3+"')\">"+title3+"</A></em>";
	}

}
