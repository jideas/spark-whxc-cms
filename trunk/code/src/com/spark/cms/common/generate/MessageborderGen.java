package com.spark.cms.common.generate;

import java.util.List;

import com.spark.cms.services.messageborder.MessageborderService;
import com.spark.cms.services.vo.MessageborderVo;

public class MessageborderGen {
	/**
	 * 获取Messageborder的HTML
	 */
	public static String getMessageborderHTML(List<MessageborderVo> messageborderList,MessageborderService messageborderService){
		if(messageborderList==null||messageborderList.size()==0)return "";
		StringBuilder sb = new StringBuilder();
		
		//客户留言
		for(int i = 0;i < messageborderList.size();i++){
			MessageborderVo mbv = messageborderList.get(i);
			String msgHTML = getMessageBorderHTMLPassMessageborderVo(mbv,i == messageborderList.size() - 1,messageborderService);
			sb.append(msgHTML);
		}
		return sb.toString();
	}
	
	/**
	 * 获取单个留言
	 */
	public static String getMessageBorderHTMLPassMessageborderVo(MessageborderVo mbv,boolean isLastMessage,MessageborderService messageborderService){
		StringBuilder sb = new StringBuilder();
		//留言开始
		sb.append(getMessageborderBeginOrEnd(true,isLastMessage));
		//客户留言开始
		sb.append(getClientMsg(mbv, true));
		if("1".equals(mbv.getIsrecovery())){
			//管理员回复
			MessageborderVo tempVo = messageborderService.getRecoveryById(mbv.getRecid());
			sb.append(getAdminMsg(tempVo));
		}
		//客户留言结束
		sb.append(getClientMsg(mbv, false));
		//留言结束
		sb.append(getMessageborderBeginOrEnd(false,false));
		return sb.toString();
	}
	
	/**
	 * 留言【开始，结束】
	 */
	private static String getMessageborderBeginOrEnd(boolean isBegin,boolean isLastMessage){
		StringBuilder sb = new StringBuilder();
		if(isBegin){
			if(isLastMessage){
				sb.append("<div class=\"msgitemcontainer\" id=\"msgitemcontainer\">");
			}else{
				sb.append("<div class=\"msgitemcontainer\">");
			}
		}else{
			sb.append("</div>");
		}
		return sb.toString();
	}
	
	/**
	 * 用户留言
	 */
	private static String getClientMsg(MessageborderVo mbv,boolean isBegin){
		StringBuilder sb = new StringBuilder();
		if(isBegin){
			sb.append("<div class='msgitem'>");
			sb.append("<p><b>"+mbv.getMsgcreator()+"</b><span>"+mbv.getMsgdate()+"</span></p>");
			sb.append("<div class=\"msgtext\">");
			sb.append("<p>"+mbv.getMsgcontent()+"</p>");
		}else{
			sb.append("</div>");
			sb.append("</div>");
		}
		return sb.toString();
	}
	
	/**
	 * 管理员回复
	 */
	 private static String getAdminMsg(MessageborderVo mbv){
		 StringBuilder sb = new StringBuilder();
		 if(mbv == null) return sb.toString();
		 sb.append("<div class='msgitem'>");
		 sb.append("<div class=\"msgtext\">");
		 sb.append("<p><b>"+mbv.getMsgcreator()+"</b><span>"+mbv.getMsgdate()+"</span></p>");
		 sb.append("<p>"+mbv.getMsgcontent()+"</p>");
		 sb.append("</div>");
		 sb.append("</div>");
		 return sb.toString();
	 }
	 
}
