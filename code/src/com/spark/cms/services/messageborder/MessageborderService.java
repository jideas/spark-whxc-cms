package com.spark.cms.services.messageborder;

import java.util.List;

import com.spark.base.common.key.LimitKey;
import com.spark.cms.services.vo.MessageborderVo;

public interface MessageborderService {

	/**
	 * 提交留言
	 */
	public abstract String createMessageborder(MessageborderVo mbv);
	
	/**
	 * 根据recid获取留言
	 */
	public abstract MessageborderVo getMessageborder(String id);
	
	/**
	 * 获取用户留言列表
	 */
	public abstract List<MessageborderVo> getMessageborderList();
	
	/**
	 * 后台 -> 获取留言列表
	 */
	public abstract List<MessageborderVo> getMessageborderList(LimitKey lk);
	
	/**
	 * 统计Msg数量
	 */
	public abstract int countMsg(LimitKey lk);
	
	/**
	 * 后台 -> 删除留言
	 */
	public abstract boolean deleteMsg(String recid);
	
	/**
	 * 后台 -> 回复留言
	 */
	public abstract boolean exeRecoveryMsg(String recid,String msgcontent);
	
	/**
	 * 后台 -> 回复留言
	 */
	public abstract boolean updateRecoveryMsg(String recid,String msgcontent);
	/**
	 * 后台 -> 根据ID获取回复
	 */
	public abstract MessageborderVo getRecoveryById(String recid);
}
