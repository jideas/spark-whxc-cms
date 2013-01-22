package com.spark.cms.services.card;

import java.util.Date;
import java.util.List;

import com.spark.cms.common.DataModel;
import com.spark.cms.services.ServiceMessage;
import com.spark.cms.services.form.Login;
import com.spark.cms.services.vo.CardVo;

public interface CardService {

	/**
	 * 批量生成card
	 * @param lastDate 
	 */
	List<CardVo> createCards(double amount, int count, String secretKey, Date lastDate, Login login) throws ServiceMessage;

	/**
	 * 批量启用（打印成功后启用）
	 */
	void exeActiveCards(List<String> cardIds, Login login) throws ServiceMessage;

	/**
	 * 批量删除未启用卡片
	 * 
	 * @throws Exception
	 */
	void deleteAll(List<String> cardIds) throws ServiceMessage;

	/**
	 * 删除未启用卡片
	 */
	void delete(String cardId) throws ServiceMessage;

	/**
	 * 作废卡片
	 */
	void exeStopCard(String cardId, Login login) throws ServiceMessage;

	/**
	 * 批量恢复作废卡片
	 */
	void exeReActivingCards(List<String> cardIds, Login login) throws ServiceMessage;

	/**
	 * 打印成功改卡片状态（已打印）
	 */
	void exePrintCards(List<String> cardIds, Login login) throws ServiceMessage;

	/**
	 * 分发卡片
	 * 
	 * @param personName
	 */
	void exeDistributeCards(List<String> cardIds, String personId, String personName, Login login)
			throws ServiceMessage;

	/**
	 * 使用卡片
	 */
	ServiceMessage exeUseCard(String cardNo, String password, Login login) throws ServiceMessage;

	/**
	 * 查询
	 */
	DataModel<CardVo> getList(GetCardListKey key);

	/**
	 * id查询
	 */
	CardVo find(String recid);

	/**
	 * 修改
	 */
	void modify(CardVo vo, Login login) throws ServiceMessage;

	/**
	 * 得到未打印状态的面值卡
	 * @param cardIds
	 * @return
	 */
	List<CardVo> getVoListPrintingByIds(List<String> cardIds);
	
	void exeImportExcel(CardVo vo) throws Exception;
}
