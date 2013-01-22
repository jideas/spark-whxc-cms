package com.spark.cms.common.generate;

import java.util.List;

import com.spark.cms.common.Constant.MainMenu;
import com.spark.cms.services.vo.ChannelVo;

public class MenuGen {
	
	public static String getMenuHtml(List<ChannelVo> tree, String path){
		
		String menuHtml = getMenuItemBegin(path);
		
		for(ChannelVo item:tree){
			menuHtml += getMenuItem(item, path);
		}
		menuHtml +=getMenuItemEnd();
		return menuHtml;
	}
	
	/**
	 * [首页]获取菜单项
	 */
	public static String getMenuHtmlIndex(List<ChannelVo> tree, String path){
		String menuHtml = getMenuItemBeginIndex(path);
		
		for(ChannelVo item:tree){
			menuHtml += getMenuItem(item, path);
		}
		menuHtml +=getMenuItemEnd();
		return menuHtml;
	}
	
	private static String getMenuItem(ChannelVo channel, String path){
		String url = path;
		String html = "";
		if (MainMenu.vantagemall.getChannelId().equals(channel.getRecid())) {
			url = path + MainMenu.vantagemall.getUrl();
			html = "<li  id='" + MainMenu.vantagemall.name() + "'><a href=\"" + url + "\">"+channel.getName()+"</a></li>";
		} else if (MainMenu.oneyuan.getChannelId().equals(channel.getRecid())) {
			url = path + MainMenu.oneyuan.getUrl();
			html = "<li id='" + MainMenu.oneyuan.name() + "'><a href=\"" + url + "\">"+channel.getName()+"</a></li>";
		} else if (MainMenu.specialoffer.getChannelId().equals(channel.getRecid())) {
			url = path + MainMenu.specialoffer.getUrl();
			html = "<li id='" + MainMenu.specialoffer.name() + "'><a href=\"" + url + "\">"+channel.getName()+"</a></li>";
		} else if (MainMenu.joinus.getChannelId().equals(channel.getRecid())) {
			url = path + MainMenu.joinus.getUrl();
			html = "<li id='" + MainMenu.joinus.name() + "'><a href=\"" + url + "\">"+channel.getName()+"</a></li>";
		}
		return  html;
	}
	
	private static String getMenuItemBegin(String path){
		return "<ul><li id='" + MainMenu.home.name() + "'><a href=\"" + path + "\">首页</a></li>";
	}
	
	private static String getMenuItemBeginIndex(String path){
		return "<ul><li class=\"current\" id='" + MainMenu.home.name() + "'><a href=\"" + path + "\">首页</a></li>";
	}
	private static String getMenuItemEnd(){
		return "</ul>";
	}


}
