package com.spark.cms.services.goods;

import java.util.List;

import com.spark.base.common.system.dic.DicItem;
import com.spark.cms.services.ServiceMessage;
import com.spark.cms.services.form.GoodsForm;
import com.spark.cms.services.form.UserExtForm;
import com.spark.cms.services.vo.EGoodsVo;
import com.spark.cms.services.vo.GoodsContentVo;
import com.spark.cms.services.vo.GoodsVo;

public interface GoodsService {

	/**
	 * ������Ʒ(��ERP�е���Ʒ���Ƶ���վ��Ʒ�У�ֻ���ƻ�����Ϣ����)
	 * 
	 * @param goodsForm
	 * @return
	 */
	public boolean createGoods(String categoryId, GoodsVo goodsVo);

	/**
	 * ������Ʒ(��ָ����ERP�е���Ʒ���Ƶ���վ��Ʒ�У�ֻ���ƻ�����Ϣ����)
	 * @param erpGoodsIds
	 * @return
	 */
	public void createGoodsFromERP(String categoryId, String[] erpGoodsIds);
	/**
	 * �����Ʒ�Ļ�����Ϣ
	 * 
	 * @param goodsId
	 * @return
	 */
	public GoodsVo getGoodsVo(String goodsId);

	/**
	 * �޸���Ʒ�Ļ�����Ϣ����ʱֻ֧���޸���Ʒ���ԣ�
	 * 
	 * @param goodsId
	 * @param task
	 * @return
	 */
	public boolean modifyGoodsVo(ModifyGoodsVoTask task);
	
	/**
	 * ������Ʒ����״̬
	 * @param goodsId
	 * @param isPromotion
	 * @return
	 */
	public boolean modifyGoodsPromotionStatus(String goodsId, boolean isPromotion);

	/**
	 * �޸���Ʒ����
	 * 
	 * @param goodsForm
	 * @return
	 * @throws ServiceMessage
	 */
	public void modifyGoods(GoodsForm goodsForm, UserExtForm loginUser) throws ServiceMessage;

	/**
	 * �޸���Ʒ�����������ۼӣ�
	 * @param goodsId
	 * @param changeCount �ı������
	 */
	public boolean modifyGoodsSaleCount(String goodsId, double changeCount);
	
	/**
	 * �����Ʒ����
	 * 
	 * @param goodsId
	 * @return
	 */
	public GoodsForm getGoodsForm(String goodsId);

	/**
	 * ɾ��ָ����Ʒ
	 * 
	 * @param goodsRecid
	 * @return
	 */
	public boolean deleteGoods(String goodsRecid);

	/**
	 * ��ѯ��Ʒ�б�
	 * 
	 * @param key
	 * @return
	 */
	public List<GoodsVo> getGoodsList(GetGoodsListKey key);

	/**
	 * �����Ʒ������
	 * 
	 * @param key
	 * @return
	 */
	public int getGoodsTotalCount(GetGoodsListKey key);

	/**
	 * ��ѯ��Ʒ�����б�
	 */
	List<GoodsContentVo> getGoodsContentList(String goodsId);

	/**
	 * ���ERPϵͳ�е���Ʒ
	 * 
	 * @return
	 */
	public List<EGoodsVo> getEGoodsList(GetEGoodsListKey key);

	/**
	 * ���ERPϵͳ�е���Ʒ����
	 * 
	 * @return
	 */
	public int getEGoodsTotalCount(GetEGoodsListKey key);

	/**
	 * �����Ʒ���ֹ����б�
	 * 
	 * @return
	 */
	public List<DicItem> getVantageList();

	/**
	 * ������Ʒ��Ų�ѯ��ͬ����ͬһ��Ʒ
	 * 
	 * @param goodsCode
	 * @return
	 */
	List<GoodsForm> getSameGoodsList(String goodsCode);

	/**
	 * ����һ�������ѯ������Ʒ
	 * 
	 * @param goodsCode
	 * @return
	 */
	List<GoodsForm> getPopularGoodsList(String categoryId);
	

	/**
	 * ���ݷ����ѯ������Ʒ
	 * 
	 * @param goodsCode
	 * @return
	 */
	List<GoodsForm> getHotGoodsList(String categoryId);
	
	/**
	 * ���ݷ����ѯ������Ʒ
	 * @param categoryId
	 * @return
	 */
	List<GoodsVo> getHotGoodsVoList(String categoryId);
	
	/**
	 * ��ѯ������Ʒ����Ʒ
	 * @param key
	 * @return
	 */
	List<GoodsVo> getVantageGoodsList(GetVantageGoodsListKey key);

}
