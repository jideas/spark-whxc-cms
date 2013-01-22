package com.spark.cms.services.channel;

import java.util.List;

import com.spark.cms.services.channel.info.FloorInfo;
import com.spark.cms.services.vo.ChannelAdvertisingVo;
import com.spark.cms.services.vo.ChannelContentVo;
import com.spark.cms.services.vo.ChannelGoodsVo;
import com.spark.cms.services.vo.ChannelVo;
import com.spark.cms.services.vo.FloorVo;
import com.spark.cms.services.vo.GoodsVo;
import com.spark.cms.services.vo.PopularKeyWordsVo;

public interface ChannelService {

	/**
	 * ������Ŀ
	 * @param v
	 */
	void createChannel(ChannelVo v);
	/**
	 * �޸�
	 */
	void modifyChannel(ChannelVo v);
	/**
	 * ����������Ŀ
	 */
	void createChannelContent(ChannelContentVo v);
	/**
	 * ������Ʒ��Ŀ
	 */
	void createChannelGoods(ChannelGoodsVo v);
	/**
	 * ���������Ŀ
	 */
	void createChannelAdvertising(ChannelAdvertisingVo v);
	/**
	 * ����¥��
	 * @throws ChannelThrowable 
	 */
	void createFloor(FloorInfo v) throws ChannelThrowable;
	
	boolean isChannelExist(String... channelIds);
	/**
	 * �༭������Ŀ
	 */
	void modifyChannelContent(ChannelContentVo v);
	/**
	 * �༭��Ʒ��Ŀ
	 */
	void modifyChannelGoods(ChannelGoodsVo v);
	/**
	 * �༭�����Ŀ
	 */
	void modifyChannelAdvertising(ChannelAdvertisingVo v);
	/**
	 * ��ȡ�����б�
	 */
	List<ChannelContentVo> getChannelContentVos(GetChannelContentListKey key);
	
	public int getChannelContentCount(GetChannelContentListKey key);
	/**
	 * ��ȡ��Ʒ�б�
	 */
	List<ChannelGoodsVo> getChannelGoodsVos(GetChannelGoodsListKey key);

	List<GoodsVo> getGoodsListByChannel(GetChannelGoodsListKey key);
	String[] getChannelGoodsIds(String channelId);
	
	int getChannelGoodsCount(GetChannelGoodsListKey key);
	
	/**
	 * ��ȡ����б�
	 */
	List<ChannelAdvertisingVo> getChannelAdvertisingVos(GetChannelAdvertisingKey key);
	
	int getChannelAdvertisingCount(GetChannelAdvertisingKey key);
	/**
	 * �༭¥��
	 * @throws ChannelThrowable 
	 */
	void modifyFloor(FloorInfo v) throws ChannelThrowable;
	/**
	 * ɾ��¥��
	 */
	void deleteFloor(String id);
	
	/**
	 * ��ѯ��Ŀ
	 * @param v
	 */
	ChannelVo findChannel(String id);
	/**
	 * ��ѯ��Ŀ
	 * @param v
	 */
	List<ChannelVo> getList(String id);
	/**
	 * ������˵��б�
	 * @return
	 */
	List<ChannelVo> getMainMenuList();
	
	/**
	 * ��ѯ��Ŀ�б�
	 * @param key
	 * @return
	 */
	List<ChannelVo> getChannelList(GetChannelListKey key);
	/**
	 * ��ѯ������Ŀ
	 */
	ChannelContentVo findChannelContent(String id);
	/**
	 * ��ѯ��Ʒ��Ŀ
	 */
	ChannelGoodsVo findChannelGoods(String id);
	/**
	 * ��ѯ�����Ŀ
	 */
	ChannelAdvertisingVo findChannelAdvertising(String id);
	/**
	 * ��ѯ¥��
	 */
	FloorVo findFloor(String id);
	FloorInfo findFloorInfo(String id);
	/**
	 * ��ѯ¥���б�
	 */
	List<FloorVo> getFloorList(String id);
	/**
	 * ��ѯ¥����Ϣ�б�
	 */
	List<FloorInfo> getFloorInfoList();
	/**
	 * ɾ��
	 * @param id
	 */
	void delete(String id);
	
	/**
	 * ��������
	 */
	void modifyPopularKeyWords(PopularKeyWordsVo popularKeyWordsVo);
	/**
	 * ��ѯ
	 * @return
	 */
	PopularKeyWordsVo getPopularKeyWordsVo();
	/**
	 * ɾ����Ŀ��Ʒ
	 * @throws ChannelThrowable 
	 */
	void deleteChannelGoods(String channelId,String goodsId) throws ChannelThrowable;
	/**
	 * ɾ�������Ŀ����
	 */
	void deleteChannelAdvertising(String id);
	/**
	 * ɾ��������Ŀ����
	 * @param id
	 */
	void deleteChannelContent(String id);
	
	/**
	 * ��Ŀ ��Ʒ����/����
	 * @param id
	 * @param isEnable
	 * @return
	 */
	boolean updateChannelGoodsStatus(String id, boolean isEnable);
	
	/**
	 * ��Ŀ ��������/����
	 * @param id
	 * @param isEnable
	 * @return
	 */
	boolean updateChannelContentStatus(String id, boolean isEnable);
	
	/**
	 * ��Ŀ �������/����
	 * @param id
	 * @param isEnable
	 * @return
	 */
	boolean updateChannelAdvertisingStatus(String id, boolean isEnable);
	
}
