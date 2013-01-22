package com.spark.cms.common.generate;

import java.util.ArrayList;
import java.util.List;

import com.spark.cms.services.vo.ChannelContentVo;
import com.spark.cms.services.vo.ChannelVo;

public class NewsCenterGen {
	
	public static String getNewsHtml(List<ChannelContentVo> items,String path) {
		String newsHtml = "";
		if(items == null) return "";
		for(int i = 0;i < items.size() && i < 10;i++){
			ChannelContentVo item = items.get(i);
			newsHtml += getNewsItem(item.getRecid(), item.getTitle(),path);
		}
		return newsHtml;
	}

	private static String getNewsItem(String id, String title,String path) {
		String url = path+"/front/channel/getNews/"+id;
		if(title.length()>15){
			return "<li><a href=\""+url+"\" target=\"_blank\" title=\""+title+"\">" + (title.substring(0,14)+"...") + "</a></li>";
		} else{
			return "<li><a href=\""+url+"\" target=\"_blank\">" + title + "</a></li>";
		}		
	}
	
	/**
	 * ��Ѷ/������������
	 * @param items
	 * @param path
	 * @return
	 */
	public static String getNewsNavHTML(List<ChannelContentVo> items,String path,ChannelVo channelVo) {
		String str = "";
		//�������⿪ʼ
		str += getNewsNavTitleBeginOrEnd(true, channelVo);
		//��������
		items = (items == null) ? new ArrayList<ChannelContentVo>() : items;
		boolean isFirst = true;
		for(int i = 0;i < items.size() && i < 10;i++){
			ChannelContentVo cv = items.get(i);
			str += getNewsNavItem(isFirst, cv);
			isFirst = false;
		}
		//�����������
		str += getNewsNavTitleBeginOrEnd(false, channelVo);
		return str;
	}
	
	/**
	 * ��������ʼ/������
	 */
	public static String getNewsNavBeginOrEnd(boolean isBegin){
		String str = "";
		if(isBegin){
			str += "<div id=\"my7\" class=\"m\">";
			str += "<div class=\"mc\">";
		}else{
			str += "</div>";
			str += "</div>";
		}
		return str;
	}
	
	/**
	 * �������⡾��ʼ/������
	 */
	private static String getNewsNavTitleBeginOrEnd(boolean isBegin,ChannelVo channelVo){
		String str = "";
		if(isBegin){		
			str += "<dl>";
			str += "<dt onClick=\"loadNewsList('"+channelVo.getRecid()+"',this)\">";
			if(channelVo != null){
				str +=	channelVo.getName() == null ? "" : channelVo.getName();			
			}
			str += "</dt>";
			str += "<dd>";
		}else{
			str += "</dd>";
			str += "</dl>";
		}
		return str;
	}
	
	/**
	 * ��������
	 * @param isBegin
	 * @param channelVo
	 * @return
	 */
	private static String getNewsNavItem(boolean isFirst,ChannelContentVo channelContentVo){
		String str = "";
		//�ж��Ƿ��һ��
		if(isFirst){
			str += "<div class=\"item pre\">";			
		}else{
			str += "<div class=\"item\">";
		}
		//����������
		if(channelContentVo.getTitle().length() > 14){
			str += "&nbsp;&gt;&nbsp;<a href=\"javascript:loadNews('"+channelContentVo.getRecid()+"')\" title=\""+channelContentVo.getTitle()+"\">"+channelContentVo.getTitle().substring(0, 13)+"...</a>";
		}else{
			str += "&nbsp;&gt;&nbsp;<a href=\"javascript:loadNews('"+channelContentVo.getRecid()+"')\">"+channelContentVo.getTitle()+"</a>";
		}		
		str += "</div>";
		return str;
	}
	
	/**
	 * �����б�
	 */
	public static String getNewsListHTML(List<ChannelContentVo> items){
		String str = "";
		if(items == null) return "";
		for(int i = 0;i<items.size() && i < 15;i++){
			ChannelContentVo channelContentVo = items.get(i);
			str += getNewsListItem(channelContentVo);
		}
		return str;
	}
	
	/**
	 * �����б� -> ����
	 */
	private static String getNewsListItem(ChannelContentVo channelContentVo){
		return "<li><a href=\"javascript:loadNews('"+channelContentVo.getRecid()+"')\">"+channelContentVo.getTitle()+"</a><span style=\"font-size:13px;\">( "+channelContentVo.getCreatedate()+" )</span></li>";
	}
	
}
