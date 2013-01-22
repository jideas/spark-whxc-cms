package com.spark.cms.common.generate;

import java.util.List;

import com.spark.cms.services.vo.ChannelGoodsVo;
import com.spark.cms.services.vo.ChannelVo;

public class MainFloorGen {
	
	public static String getMainFloor(List<ChannelVo> channelVoslist ,String path){
		//String ret = getMainFloorBegin();
		String ret = "";
		
		ret += getFloorHeadBegin();
		for(int i=0;i<channelVoslist.size();i++){
			ChannelVo channelVo = channelVoslist.get(i);
			ret += getFloorHeadItem(channelVo.getRecid(),channelVo.getName(),i==0?true:false);
		}
		ret += getFloorHeadEnd();
		
		ret += getFloorContainBegin();
		
		for(int i=0;i<channelVoslist.size();i++){
			ChannelVo channelVo = channelVoslist.get(i);
			ret += getFloorContainProductWapBegin(channelVo.getRecid());
			
			List<ChannelGoodsVo> goodsList = channelVo.getGoodsModel().getRows();
			for(int k=0;k<goodsList.size();k++){
				ret += getFloorContainProduct(goodsList.get(k),path);
			}
			
			ret += getFloorContainProductWapEnd();
		}
		
		ret += getFloorContainEnd();
		
		//ret += getMainFloorEnd();
		return ret;
	}
	
	private static String getMainFloorBegin(){
		return "<div id=\"mainFloorMainDiv\">";
	}
	
	private static String getMainFloorEnd(){
		return "</div>";
	}

	
	private static String getFloorHeadBegin(){
		return "<div id=\"mainFloorTitleDiv\"><ul>";
	}
	private static String getFloorHeadEnd(){
		return "</ul></div>";
	}
	private static String getFloorHeadItem(String id,String title,boolean isAddClass){
		if(isAddClass){
			return "<li id=\"mainFloorLi"+id+"\" class=\"pre\" >"+title+"</li>";
		}
		return "<li id=\"mainFloorLi"+id+"\">"+title+"</li>";
	}
	
	private static String getFloorContainBegin(){
		return "<div id=\"mainFloorContain\">";
	}
	private static String getFloorContainEnd(){
		return "</div>";
	}
	
	private static String getFloorContainProductWapBegin(String id){
		return "<ul id=\"mainFloorLi"+id+"\">";
	}
	private static String getFloorContainProductWapEnd(){
		return "</ul>";
	}
	private static String getFloorContainProduct(ChannelGoodsVo goods,String path){
		String imgsrc = null;
		if(goods.getPicturepath2()!=null){
			 imgsrc = path+ goods.getPicturepath2();
		}else if(goods.getPicturepath3()!=null){
			 imgsrc = path+ goods.getPicturepath3();
		}else if(goods.getPicturepath1()!=null){
			 imgsrc = path+ goods.getPicturepath1();
		}
		String url = path + "/front/toGoodsInfoPage?id=" + goods.getGoodsid();
		
		return	
		"<li><div class=\"product_image\"> "+"<a target=\"_blank\" href=\"" + url + "\">"+"<img  width=\"140px\" height=\"140px\" src=\""+imgsrc+"\"/></a></div>"+
		"<div class=\"product_name\">"+"<a target=\"_blank\" href=\"" + url + "\">"+goods.getGoodsName()+"</a></div>"+
		"<div class=\"product_type\">¹æ¸ñ£º"+goods.getGoodsspec()+"</div>"+
		"<div class=\"product_price\">£¤"+goods.getRealprice()+"/"+goods.getGoodsunit()+"</div></li>";
	}
	
	
}
