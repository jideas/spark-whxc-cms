package com.spark.cms.common.generate;

import java.util.List;

import com.spark.cms.common.Constant.ChannelEnum.ChannelType;
import com.spark.cms.common.Constant.ChannelEnum.Theme;
import com.spark.cms.services.channel.info.FloorInfo;
import com.spark.cms.services.vo.ChannelGoodsVo;
import com.spark.cms.services.vo.ChannelVo;
import com.spark.cms.services.vo.FloorAdvertisingVo;
import com.spark.cms.services.vo.FloorVo;


public  class FloorGen {
	private static String path;
	
	public  static String genFloors(List<FloorInfo> floorList,String contextPath){
		path = contextPath;
		
		String str = "";
		/**
		 * 遍历楼层
		 */
		for(FloorInfo floorVo:floorList){
			/**
			 * 商品楼
			 */
			if(floorVo.getFloortype().equals(ChannelType.Goods.getCode())){
				
				str += getFoorBegin(floorVo.getRecid());
				
				str += getProductFloorBegin();
				
				str += getFloorHeader(floorVo);
				
				str += getFloorContain(floorVo);
				
				str += getProductFloorEnd();
				
				str += getFloorAd(floorVo);
				
				str += getFoorEnd();
			}else{
				/**
				 * 处理广告楼
				 */
				
			}
			
			
		}
		return str;
	}
	
	private static   String getFoorBegin(String floorId){
		return "<div class=\"commonFloor\" id=\"floor_"+floorId+"\">";
	}
	
	private static   String getFoorEnd(){
		return "</div>";
	}
	
	
	private static   String getFloorAd(FloorInfo floorVo){
		String str = "<div class=\"floor_ad\">";
		
		for(FloorAdvertisingVo ad:floorVo.getAdvertisings()){
			
			String imgsrc = path +"/"+ ad.getImageurl();
			String url = ad.getUrl();
			
			str += "<div> <a href=\""+url+"\" target=\"_blank\"> <img src=\""+imgsrc+"\" /></a> </div>";
		}
		
		str += "</div>";
		return str;
	}

	
	private static   String getProductFloorBegin(){
		return "<div class=\"floor_product\"><div class=\"floor_line_dotted\"></div>";
	}
	
	private static   String getProductFloorEnd(){
		return "</div>";
	}
	
	private static String getFloorContain(FloorInfo floorVo){
		String ret = getFloorProductcontainBegin();
		
		for(ChannelVo channelVo:floorVo.getChannelVoList()){
			ret += getGoodsHead(channelVo.getRecid());
			for(ChannelGoodsVo goodVo:channelVo.getGoodsModel().getRows()){
				ret += getGoodsAbstact(goodVo,path);
			}
			ret += getGoodsEnd();
		}
		ret += getFloorProductcontainEnd();
		return ret;
	}
	private  static String getFloorProductcontainBegin(){
		return "<div class = \"floor_productcontain\">";
	}
	private  static String getFloorProductcontainEnd(){
		return "</div>";
	}
	
	private static   String getGoodsHead(String ChannelId){
		return "<div class=\"floor_category_product\" id=\"product_floor"+ChannelId+ "\"><ol>";
		
	}
	
	private static   String getGoodsEnd(){
		return "</ol></div>";
		
	}
	private static   String getGoodsAbstact(ChannelGoodsVo goodVo,String path){
		String imgsrc = null;
		if(goodVo.getPicturepath2()!=null){
			 imgsrc = path+ goodVo.getPicturepath2();
		}else if(goodVo.getPicturepath3()!=null){
			 imgsrc = path+ goodVo.getPicturepath3();
		}else if(goodVo.getPicturepath1()!=null){
			 imgsrc = path+ goodVo.getPicturepath1();
		}
		
		
		String url = path + "/front/toGoodsInfoPage?id=" + goodVo.getGoodsid();
		String ret = "<li><div class=\"product_image\"><a style=\" width:140;height:140;border:0\" href=\"" + url + "\" target=\"_blank\"><img width=\"140px\" height=\"140px\" src=\""+imgsrc+"\" /></a></div>";
				ret += "<div class=\"product_name\">"+"<a target=\"_blank\" href=\"" + url + "\">"+goodVo.getGoodsName()+"</a></div>";
				ret += "<div class=\"product_type\">"+"规格："+goodVo.getGoodsspec()+"</div>";
				ret += "<div class=\"product_price\">"+"￥"+goodVo.getRealprice()+"/份"+"</div></li>";
				
		return ret;
	}
	
	private static String getFloorHeader(FloorVo floorVo){
		
		Theme theme = Theme.getTheme(floorVo.getTheme());
		
		String fileName = path+imgPath+theme.getFileName();
		
		String ret=getFloorItemHead();
		
		ret += getFloorCellHead(floorVo.getRecid(), floorVo.getTitle(),fileName);
		
		ret += getFloorSpan(theme.getBorderColor());
		
		ret += getFloorCellsHtml(floorVo.getChannelVoList(),theme.getBorderColor());
		
		ret += getFloorItemEnd();
		return ret;
		
	}
	
	

	
	private static String getFloorCellsHtml(List<ChannelVo> items,String bordercolor){
		String str= "";
		int sum_width = 730;
		int li_count = items.size();
		if(li_count==0) li_count = 1;
		int li_width =  sum_width/li_count;
		int li_last= sum_width % li_count;
		for(int i=0;i<items.size();i++){
			ChannelVo item = items.get(i);
			if(i==items.size()-1)
				li_width = li_width+li_last;
			str +=getFloorCell(item.getRecid(), item.getName(),item.getChanneltype(),li_width,bordercolor);
		}
		return str;
	}
	

	private static String getFloorItemHead(){
		return "<div class=\"floor_category\"><ul>";
	}
	
	private static String getFloorItemEnd(){
		return "</ul></div>";
	}
	
	private static String getFloorCell(String code ,String title,String type, int width,String borderColor){
		return  "<li id=\"floor"+code+"\" onClick=\"toCategoryPage('"+code+"')\" style=\"cursor:hand;border-top:2px solid "+borderColor+"; width:"+width+"px \"  >"+title+"</li>";
	}
	

	private static String getFloorCellHead(String code ,String title,String floorImg){
		return  "<li class=\"floor_second_category\" style=\"border-top:0px;background-image:url('"+floorImg+"')\">"+title+"</li>";
	
	}
	
	private static String getFloorSpan(String borderColor){
		return "<li class=\"floor_span\" style=\"border-top:2px solid "+borderColor+"\"></li>";
		 
	}
	
	
	
	private static final String imgPath = "/images/page/";

}
