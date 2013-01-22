package com.spark.cms.services.card;

import java.util.Date;
import java.util.List;

import com.spark.cms.common.DataModel;
import com.spark.cms.services.ServiceMessage;
import com.spark.cms.services.form.Login;
import com.spark.cms.services.vo.CardVo;

public interface CardService {

	/**
	 * ��������card
	 * @param lastDate 
	 */
	List<CardVo> createCards(double amount, int count, String secretKey, Date lastDate, Login login) throws ServiceMessage;

	/**
	 * �������ã���ӡ�ɹ������ã�
	 */
	void exeActiveCards(List<String> cardIds, Login login) throws ServiceMessage;

	/**
	 * ����ɾ��δ���ÿ�Ƭ
	 * 
	 * @throws Exception
	 */
	void deleteAll(List<String> cardIds) throws ServiceMessage;

	/**
	 * ɾ��δ���ÿ�Ƭ
	 */
	void delete(String cardId) throws ServiceMessage;

	/**
	 * ���Ͽ�Ƭ
	 */
	void exeStopCard(String cardId, Login login) throws ServiceMessage;

	/**
	 * �����ָ����Ͽ�Ƭ
	 */
	void exeReActivingCards(List<String> cardIds, Login login) throws ServiceMessage;

	/**
	 * ��ӡ�ɹ��Ŀ�Ƭ״̬���Ѵ�ӡ��
	 */
	void exePrintCards(List<String> cardIds, Login login) throws ServiceMessage;

	/**
	 * �ַ���Ƭ
	 * 
	 * @param personName
	 */
	void exeDistributeCards(List<String> cardIds, String personId, String personName, Login login)
			throws ServiceMessage;

	/**
	 * ʹ�ÿ�Ƭ
	 */
	ServiceMessage exeUseCard(String cardNo, String password, Login login) throws ServiceMessage;

	/**
	 * ��ѯ
	 */
	DataModel<CardVo> getList(GetCardListKey key);

	/**
	 * id��ѯ
	 */
	CardVo find(String recid);

	/**
	 * �޸�
	 */
	void modify(CardVo vo, Login login) throws ServiceMessage;

	/**
	 * �õ�δ��ӡ״̬����ֵ��
	 * @param cardIds
	 * @return
	 */
	List<CardVo> getVoListPrintingByIds(List<String> cardIds);
	
	void exeImportExcel(CardVo vo) throws Exception;
}
