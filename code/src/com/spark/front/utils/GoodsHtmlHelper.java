/**
 * 
 */
package com.spark.front.utils;

/**
 * @author Jideas
 *
 */
public class GoodsHtmlHelper {
	/**
	 * ������ƷչʾС����
	 * @param id
	 * @param picturepath2
	 * @param goodsname
	 * @param goodsspec
	 * @param price
	 * @return
	 */
	public static String getSmallGoodsHtml(String id, String imgUrl, String name, String spec, String price,String unit, String basePath) {
		String url = basePath + "/front/toGoodsInfoPage?id=" + id;
		StringBuilder ss = new StringBuilder();
		ss.append(" <div>");
		ss.append(" <a href=\"" + url + "\" class=\"a-loseBlue\">");
		ss.append(" <img src=\"" + basePath + imgUrl + "\" width=\"140\" height=\"140\"");
		ss.append(" style=\"border: 0px;\" alt=\"" + name + "\"> </a></div>");
		ss.append(" <div class='p-name'>");
		ss.append(" <a href=\"" + url + "\" class=\"p-name\" title=\"" + name + "\">" + name + "</a>");
		ss.append(" </div><div class='p-name'>���" + spec);
		ss.append(" </div>");
		ss.append(" <div class=\"p-price\"><strong>��");
		ss.append(price);
		ss.append("/"+unit+"</strong></div><div class=\"bellowGoodsSmallInfo\"></div>");
		return ss.toString();
	}
	
	/**
	 * ��ȡͬ����������ʼ��
	 * @return
	 */
	public static String getPopularBegin(){
		String str = "";
		str += "<div id=\"categoryPagecontantLeft1\">";
		str += "<div class=\"divTitleRow\">";
		str += "<font><b>ͬ��������Ʒ</b> </font>";
		str += "</div>";
		return str;
	}
	
	/**
	 * �����ʼ/������
	 * @param isBegin
	 * @return
	 */
	public static String getItemBeginOrEnd(boolean isBegin){
		String str = "";
		if(isBegin){
			str += "<div class=\"goodsInfoDiv\">";
		}else{
			str += "</div>";
		}
		return str;		
	}
}
