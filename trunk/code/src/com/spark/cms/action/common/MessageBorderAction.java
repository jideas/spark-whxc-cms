package com.spark.cms.action.common;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spark.base.common.key.LimitKey;
import com.spark.base.common.key.SortType;
import com.spark.cms.action.BaseAction;
import com.spark.cms.common.Constant;
import com.spark.cms.common.DataModel;
import com.spark.cms.common.MessageModel;
import com.spark.cms.common.ResponseEntityUtil;
import com.spark.cms.common.generate.MessageborderGen;
import com.spark.cms.services.messageborder.MessageborderService;
import com.spark.cms.services.vo.MemberVo;
import com.spark.cms.services.vo.MessageborderVo;

@Controller
public class MessageBorderAction extends BaseAction{

	@Autowired
	MessageborderService messageborderService;
	
	/**
	 * 获取初始化留言数据
	 */
	 @RequestMapping("/messageBorder/getInitMessage")
	@ResponseBody
	public MessageModel submitMessage(){
		 MessageModel mm = new MessageModel();
		try{
			mm.setMessage(MessageborderGen.getMessageborderHTML(messageborderService.getMessageborderList(),messageborderService));
			mm.setResult(true);
			return mm;
		} catch (Exception e) {
			log.error("提交留言发生异常====" + e.getStackTrace());
			e.printStackTrace();
		}
		mm.setResult(false);
		return mm; 
	}
	
	/**
	 * 提交留言
	 */
	@RequestMapping("/messageBorder/submitMessage")
	@ResponseBody
	public MessageModel submitMessage(HttpServletRequest request,String msgcontent) {
		MessageModel mm = new MessageModel();
		try{	
			MemberVo mv = (MemberVo)request.getSession().getAttribute(Constant.LoginMemberUser);
			MessageborderVo mbv = new MessageborderVo();
			if(mv != null){
				mbv.setMsgcreatorid(mv.getRecid());
				mbv.setMsgcreator(mv.getUsername());
			}else{
				mbv.setMsgcreator("7号生活馆用户");
			}
			mbv.setIsrecovery("0");
			mbv.setMsgcontent(msgcontent);
			String id = messageborderService.createMessageborder(mbv);
			mbv = messageborderService.getMessageborder(id);
		
			mm.setMessage(MessageborderGen.getMessageBorderHTMLPassMessageborderVo(mbv, false,messageborderService));
			mm.setResult(true);
			return mm;
		} catch (Exception e) {
			log.error("提交留言发生异常====" + e.getStackTrace());
			e.printStackTrace();
		}
		mm.setResult(false);
		return mm;
	}
	
	/**
	 * 后台 -> 获取留言列表
	 */
	@RequestMapping("/messageborder/getMessageborderList")
	@ResponseBody
	public DataModel getMessageborderList(@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "rows", required = false) String rows,
			@RequestParam(value = "filter", required = false) String filter){
		DataModel dm = new DataModel();
		try{
			LimitKey lk = new LimitKey();
			lk.setOffset((Integer.parseInt(page) - 1) * Integer.parseInt(rows));
			lk.setPageSize(Integer.parseInt(rows));
			lk.setSortType(SortType.Desc);
			lk.setSearchText(filter);
			int count = messageborderService.countMsg(lk);
			dm.setRows(messageborderService.getMessageborderList(lk));
			dm.setTotal(count);
			return dm;
		} catch (Exception e) {
			log.error("后台 -> 获取留言列表发生异常====" + e.getStackTrace());
			e.printStackTrace();
		}
		return dm;
	}
	
	/**
	 * 后台 －> 删除留言
	 */
	@RequestMapping("/messageborder/deleteMsg")
	@ResponseBody
	public MessageModel deleteMsg(String recid){
		try{	
			messageborderService.deleteMsg(recid);
			return Success;
		} catch (Exception e) {
			log.error("后台 －> 删除留言发生异常====" + e.getStackTrace());
			e.printStackTrace();
		}
		return Failure;
	}
	
	/**
	 * 后台 -> 回复留言
	 */
	@RequestMapping("/messageborder/recovery")
	@ResponseBody
	public MessageModel recovery(String recid,String msgcontent){
		try{	
			messageborderService.exeRecoveryMsg(recid,msgcontent);
			return Success;
		} catch (Exception e) {
			log.error("后台 -> 回复留言发生异常====" + e.getStackTrace());
			e.printStackTrace();
		}
		return Failure;
	}
	
	/**
	 * 后台 -> 修改留言
	 */
	@RequestMapping("/messageborder/updateRecovery")
	@ResponseBody
	public MessageModel updateRecovery(String recid,String msgcontent){
		try{	
			messageborderService.updateRecoveryMsg(recid,msgcontent);
			return Success;
		} catch (Exception e) {
			log.error("后台 -> 修改留言发生异常====" + e.getStackTrace());
			e.printStackTrace();
		}
		return Failure;
	}
	
	/**
	 * 后台 -> 根据ID获取回复
	 */
	@RequestMapping("/messageborder/getRecoveryById")
	@ResponseBody
	public ResponseEntity<MessageborderVo> getRecoveryById(String recid){
		try{	
			return ResponseEntityUtil.getResponseEntity(messageborderService.getRecoveryById(recid));
		} catch (Exception e) {
			log.error("提交留言发生异常====" + e.getStackTrace());
			e.printStackTrace();
		}
		return null;
	} 
	
}
