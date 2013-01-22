package com.spark.cms.services.messageborder;

import java.util.List;

import com.spark.base.common.key.LimitKey;
import com.spark.cms.services.vo.MessageborderVo;

public interface MessageborderService {

	/**
	 * �ύ����
	 */
	public abstract String createMessageborder(MessageborderVo mbv);
	
	/**
	 * ����recid��ȡ����
	 */
	public abstract MessageborderVo getMessageborder(String id);
	
	/**
	 * ��ȡ�û������б�
	 */
	public abstract List<MessageborderVo> getMessageborderList();
	
	/**
	 * ��̨ -> ��ȡ�����б�
	 */
	public abstract List<MessageborderVo> getMessageborderList(LimitKey lk);
	
	/**
	 * ͳ��Msg����
	 */
	public abstract int countMsg(LimitKey lk);
	
	/**
	 * ��̨ -> ɾ������
	 */
	public abstract boolean deleteMsg(String recid);
	
	/**
	 * ��̨ -> �ظ�����
	 */
	public abstract boolean exeRecoveryMsg(String recid,String msgcontent);
	
	/**
	 * ��̨ -> �ظ�����
	 */
	public abstract boolean updateRecoveryMsg(String recid,String msgcontent);
	/**
	 * ��̨ -> ����ID��ȡ�ظ�
	 */
	public abstract MessageborderVo getRecoveryById(String recid);
}
