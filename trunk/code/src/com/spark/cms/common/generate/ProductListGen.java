package com.spark.cms.common.generate;

import java.util.List;

import com.spark.base.common.utils.DoubleUtil;
import com.spark.cms.services.goodsPromotion.GoodsPromotionService;
import com.spark.cms.services.vo.GoodsPromotionVo;
import com.spark.cms.services.vo.GoodsVo;


public class ProductListGen {
	
	
	public static String generateProductList(List<GoodsVo> goods,String path, GoodsPromotionService promotionService){
		if(goods.size()==0)return "";
		String str = genProductItemBegin();
		
		for(int i=0;i<goods.size();i++){
			if(i%4==0&&i>0&&i!=(goods.size()-1)){
				str += genProductItemEnd();
				if(i<(goods.size()-1)){
					str += genProductItemBegin();
				}
			}
			GoodsVo goodsVo = goods.get(i);
			if (goodsVo.isPromotion()) {
				GoodsPromotionVo promotion = promotionService.findByGoodsId(goodsVo.getRecid());
				String promotionInfo = "";
				double price = 0.0;
				if (promotion.getDisrate() > 0 && promotion.getDisrate() < 1) {
					price = DoubleUtil.mul(goodsVo.getRealprice(), promotion.getDisrate());
					promotionInfo = "," + promotion.getDisrate() + "折";
				}
				if (goodsVo.isFreedelivery()) {
					promotionInfo += ",免费送货上门";
				}
				if (goodsVo.getVantagestype().equals("2")) {
					promotionInfo += ",双倍积分";
				}
				goodsVo.setGoodsname(goodsVo.getGoodsname() + promotionInfo);
				goodsVo.setRealprice(price);
			}
			str += generateProductCell(goodsVo,path);
		}
		str += genProductItemEnd();
		return str;
	}

	
	private static String generateProductCell(GoodsVo goodsVo,String path){
		
		StringBuffer sb = new StringBuffer();
		String imgsrc =null;
		if(goodsVo.getPicturepath2()!=null){
			 imgsrc = path+ goodsVo.getPicturepath2();
		}else if(goodsVo.getPicturepath3()!=null){
			 imgsrc = path+ goodsVo.getPicturepath3();
		}else if(goodsVo.getPicturepath1()!=null){
			 imgsrc = path+ goodsVo.getPicturepath1();
		}
		String url = path + "/front/toGoodsInfoPage?id=" + goodsVo.getRecid();
		
		sb.append("<div class=\"goodsInfoRowItem\"><div>")
		  .append("<a class= \"goodsCellImage\" href=\"" + url + "\" target=\"_blank\"><img width=\"140px\" height=\"140px\" src=\""+imgsrc+"\" /></a>")
		  .append("</div>")
		  .append("<div class='p-name'>").append("<a href=\"" + url + "\"  target=\"_blank\" class=\"p-name\">").append(goodsVo.getGoodsname()).append("</a></div>")
		  .append("<div class='p-name'>规格：").append(goodsVo.getGoodsspec()).append("</div>")
		  .append("<div class=\"p-price\">").append("<strong>￥").append(DoubleUtil.getRoundStr(goodsVo.getRealprice(), 2)).append("/").append(goodsVo.getGoodsunit()).append("</strong></div>")
		  .append("<div class=\"bellowGoodsSmallInfo\">")
		  .append(" <img  onclick=\"product.setbuyNum('txt_"+goodsVo.getRecid()+"','-1') \" src=\""+path+"/images/page/button_sub.png\" class=\"bellowGoodsSmallInfo_img_minus\" /> ")
		 
		  .append(" <input id=\"txt_"+goodsVo.getRecid()+"\" type=\"text\" value=\"1\" align=\"center\" preValue=\"0\" onchange=\"product.countChangeListener('txt_"+goodsVo.getRecid()+"')\">")
		  .append(" <img onclick=\"product.setbuyNum('txt_"+goodsVo.getRecid()+"','1') \" src=\""+path+"/images/page/button_sum.png\" class=\"bellowGoodsSmallInfo_img_add\" /> ")
		  .append(" <img onclick=\"product.dobuy('"+goodsVo.getRecid()+"') \"src=\""+path+"/images/page/buy25-01.png\" class=\"bellowGoodsSmallInfo_img_buy\" onmouseover='product_buy_over(this)' onmouseout='product_buy_out(this)'/> ")
		  .append("</div></div>");
		return sb.toString();
	}
	
	private static String genProductItemBegin(){
		return "<div class=\"goodsInfoRow\">";
	}
	
	private static String genProductItemEnd(){
		return "</div>";
	}
	
}
