package com.spark.cms.common.generate;

import java.util.List;

import com.spark.cms.services.form.GoodsForm;

public class SliderGen {
	/**
	 * 获取Slider的HTML
	 * @param goods
	 * @return
	 */
	public static String getSliderHTML(List<GoodsForm> goodsList,String path){
		if(goodsList==null||goodsList.size()==0)return "";
		String sliderHTML = "";
		//商品分类
		//sliderHTML += getCategoryLevel(secondCategory,thirdCategory);
		//展示区域开始
		//sliderHTML += getHotSaleBeginOrEnd(true);
		//左上角图片
		//sliderHTML += getLeftTopImg(path);
		//上一张/下一张
		//sliderHTML += getPrevAndDownImg();
		//滚动区域开始
		//sliderHTML += getScrollBeginOrEnd(true);
		//滚动子项
		for(GoodsForm gv : goodsList){
			sliderHTML += getScrollItem(gv,path);
		}
		//滚动区域结束
		//sliderHTML += getScrollBeginOrEnd(false);
		//展示区域结束
		//sliderHTML += getHotSaleBeginOrEnd(false);
		return sliderHTML;
	}
	
	/**
	 * 商品分类显示
	 */
	private static String getCategoryLevel(String secondCategory,String thirdCategory){
		String str = "";
		str += "<div id=\"category-level\">";
		str += "<span>首页</span>";
		str += "<span>&gt;</span><span>"+secondCategory+"</span>";
		str += "<span>&gt;</span><span>"+thirdCategory+"</span>";
		str += "</div>";
		return str;
	}
	
	/**
	 * 展示区域开始/结束
	 */
	private static String getHotSaleBeginOrEnd(boolean isBegin){
		String str = "";
		if(isBegin){
			str = "<div id=\"hot-sale\">";
		}else{
			str = "</div>";
		}
		return str;
	}
	
	/**
	 *	左上角图片 
	 */
	private static String getLeftTopImg(String path){
		
		String str = "";
		str += "<div class=\"ad-left-top\">";
		str += "<img src=\""+path+"/images/page/hot-sale-HOT.png\"/>";
		str += "</div>";
		return str;
	}
	
	/**
	 * 上一张/下一张(操作)
	 */
	private static String getPrevAndDownImg(){
		String str = "";
		str += "<A class=\"next\" href=\"#\" onfocus=\"this.blur()\">";
		str += "</A>";
		str += "<A class=\"prev\" href=\"#\" onfocus=\"this.blur()\">";
		str += "</A>";
		return str;
	}
	
	/**
	 * 滚动区域开始/结束
	 */
	private static String getScrollBeginOrEnd(boolean isBegin){
		String str = "";
		if(isBegin){
			str += "<div id=\"hot-sale-scroll\">";
			str += "<ul>";
		}else{
			str += "</ul>";			
			str += "</div>";
		}
		return str;
	} 
	
	/**
	 * 滚动子项
	 */
	private static String getScrollItem(GoodsForm gv,String path){
		
		String imgsrc =null;
		if(gv.getPicturepath2()!=null){
			 imgsrc = path+ gv.getPicturepath2();
		}else if(gv.getPicturepath3()!=null){
			 imgsrc = path+ gv.getPicturepath3();
		}else if(gv.getPicturepath1()!=null){
			 imgsrc = path+ gv.getPicturepath1();
		}
		String url = path + "/front/toGoodsInfoPage?id=" + gv.getRecid();
		String str = "";
		str += "<li>";
		str += "<div class=\"scroll-image\">";
		str += "<a style=\" width:130;height:130;border:0\" href=\"" + url + "\" target=\"_blank\"><img src=\""+imgsrc+"\" width=\"130px;\"/></a>";
		str += "</div>";
		str += "<div class=\"scroll-detail\">";
		
		gv.setGoodsname(gv.getGoodsname() == null ? "" : gv.getGoodsname());
		if(gv.getGoodsname().length() > 10){
			str += "<div><a target=\"_blank\" title=\""+gv.getGoodsname()+"\" href=\"" + url + "\" >"+( gv.getGoodsname().substring(0,10))+"</a></div>";
		}else{
			str += "<div><a target=\"_blank\" href=\"" + url + "\" >"+gv.getGoodsname()+"</a></div>";
		}
		str += "<div>规格："+gv.getGoodsspec()+"</div>";
		str += "<div><b>￥"+gv.getRealprice()+"/"+gv.getGoodsunit()+"</b></div>";
		str += "<div><input type=\"button\" value=\"\" onClick=\"product.hurrybuy('"+gv.getRecid()+"',1)\"/></div>";
		str += "</div>";
		str += "</li>";
		return str;
	}

}
