package com.spark.cms.services.messageborder.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spark.base.common.key.LimitKey;
import com.spark.base.hibernate.GenericDAO;
import com.spark.base.type.GUID;
import com.spark.cms.dao.po.MessageborderPo;
import com.spark.cms.services.messageborder.MessageborderService;
import com.spark.cms.services.vo.MessageborderVo;

@Service
public class MessageborderServiceImpl implements MessageborderService {
	
	@Autowired
	private GenericDAO genericDAO;

	@Override
	public String createMessageborder(MessageborderVo mbv) {
		MessageborderPo mbp = new MessageborderPo();
		try {
			mbp.setIsrecovery(mbv.getIsrecovery());
			mbp.setMsgcontent(mbv.getMsgcontent());
			mbp.setMsgcreator(mbv.getMsgcreator());
			if(mbv.getMsgcreatorid() != null){
				mbp.setMsgcreatorid(GUID.valueOf(mbv.getMsgcreatorid()).toBytes());
			}
			if(mbv.getRecoveryid() != null){
				mbp.setRecoveryid(GUID.valueOf(mbv.getRecoveryid()).toBytes());
			}
			mbp.setMsgdate(new Date(mbv.getMsgdate()));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		GUID guid = GUID.randomID();
		mbp.setRecid(guid.toBytes());
		genericDAO.save(mbp);
		return guid.toString();
	}

	@Override
	public MessageborderVo getMessageborder(String id) {
		MessageborderPo mbp = genericDAO.get(MessageborderPo.class, GUID.valueOf(id).toBytes());
		MessageborderVo mbv = copyMessageborderVo(mbp);		
		return mbv;
	}
	
	private MessageborderVo copyMessageborderVo(MessageborderPo mbp){
		MessageborderVo mbv = new MessageborderVo();
		if(mbp != null){
			mbv.setRecid(GUID.valueOf(mbp.getRecid()).toString());
			mbv.setIsrecovery(mbp.getIsrecovery());
			mbv.setMsgcontent(mbp.getMsgcontent());
			mbv.setMsgcreator(mbp.getMsgcreator());
			if(mbp.getMsgcreatorid() != null){
				mbv.setMsgcreatorid(GUID.valueOf(mbp.getMsgcreatorid()).toString());
			}
			if(mbp.getRecoveryid() != null){
				mbv.setMsgcreatorid(GUID.valueOf(mbp.getRecoveryid()).toString());
			}
			if(mbp.getRecoveryid() != null){
				mbv.setRecoveryid(GUID.valueOf(mbp.getRecoveryid()).toString());
			}			
			mbv.setMsgdate(mbp.getMsgdate().toString());
		}
		return mbv;
	}

	@Override
	public List<MessageborderVo> getMessageborderList() {
		List<MessageborderVo> mbvList = new ArrayList<MessageborderVo>();
		
		StringBuilder sql = new StringBuilder("FROM MessageborderPo p WHERE p.isrecovery = '0' or p.isrecovery = '1' ORDER BY p.msgdate DESC ");
		List<MessageborderPo> messageborderList = genericDAO.getGenericByPosition(sql.toString(), 0, 20);
		
		if(messageborderList != null && messageborderList.size() > 0){
			for(int i = 0;i < messageborderList.size();i++){
				MessageborderPo mbp = messageborderList.get(i);
				mbvList.add(copyMessageborderVo(mbp));
			}
		}
		return mbvList;
	}
	
	@Override
	public List<MessageborderVo> getMessageborderList(LimitKey lk) {
		List<MessageborderVo> mbvList = new ArrayList<MessageborderVo>();
		
		StringBuilder sql = new StringBuilder("FROM MessageborderPo p WHERE p.isrecovery = '0' or p.isrecovery = '1' ORDER BY p.msgdate DESC ");
		List<MessageborderPo> messageborderList = genericDAO.getGenericByPosition(sql.toString(), lk.getOffset(), lk.getPageSize());
		
		if(messageborderList != null && messageborderList.size() > 0){
			for(int i = 0;i < messageborderList.size();i++){
				MessageborderPo mbp = messageborderList.get(i);
				mbvList.add(copyMessageborderVo(mbp));
			}
		}
		return mbvList;
	}
	
	@Override
	public int countMsg(LimitKey lk){
		StringBuffer hsql = new StringBuffer();
		hsql.append(" select count(recid) from MessageborderPo mbp ");
		hsql.append(" where mbp.isrecovery = '0' or mbp.isrecovery = '1' ");
		return genericDAO.getGenericCountByHql(hsql.toString());
	}
	
	@Override
	public boolean deleteMsg(String recid){
		StringBuffer hql = new StringBuffer();
		hql.append(" delete from MessageborderPo mbp ");
		hql.append(" where mbp.recid= ? or mbp.recoveryid = ?");
		this.genericDAO.execteBulk(hql.toString(), GUID.valueOf(recid).toBytes(),GUID.valueOf(recid).toBytes());
		return true;
	}
	
	@Override
	public boolean exeRecoveryMsg(String recid,String msgcontent){
		//保存[管理员]留言
		MessageborderPo mbp2 = new MessageborderPo();
		mbp2.setIsrecovery("2");
		mbp2.setMsgcontent(msgcontent);
		mbp2.setMsgcreator("管理员");
		mbp2.setMsgdate(new Date());
		mbp2.setRecoveryid(GUID.valueOf(recid).toBytes());
		mbp2.setRecid(GUID.randomID().toBytes());
		genericDAO.save(mbp2);
		//更新用户留言
		MessageborderPo mbp1 = genericDAO.get(MessageborderPo.class, GUID.valueOf(recid).toBytes());
		mbp1.setIsrecovery("1");
		genericDAO.update(mbp1);
		return true;
	}
	
	@Override
	public boolean updateRecoveryMsg(String recid,String msgcontent){
		//更新管理员留言
		MessageborderPo mbp2 = genericDAO.get(MessageborderPo.class, GUID.valueOf(recid).toBytes());
		mbp2.setMsgcontent(msgcontent);
		genericDAO.update(mbp2);
		return true;
	}
	
	@Override
	public MessageborderVo getRecoveryById(String recid){
		String hql = "from MessageborderPo as p where p.recoveryid = ?";
		List<MessageborderPo> poList = genericDAO.getGenericByHql(hql, GUID.valueOf(recid).toBytes());
		if(poList == null || poList.isEmpty()){
			return null;
		}
		return copyMessageborderVo(poList.get(0));
	}

}
