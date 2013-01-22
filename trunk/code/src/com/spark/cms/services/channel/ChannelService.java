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
	 * 新增栏目
	 * @param v
	 */
	void createChannel(ChannelVo v);
	/**
	 * 修改
	 */
	void modifyChannel(ChannelVo v);
	/**
	 * 新增内容栏目
	 */
	void createChannelContent(ChannelContentVo v);
	/**
	 * 新增商品栏目
	 */
	void createChannelGoods(ChannelGoodsVo v);
	/**
	 * 新增广告栏目
	 */
	void createChannelAdvertising(ChannelAdvertisingVo v);
	/**
	 * 新增楼层
	 * @throws ChannelThrowable 
	 */
	void createFloor(FloorInfo v) throws ChannelThrowable;
	
	boolean isChannelExist(String... channelIds);
	/**
	 * 编辑内容栏目
	 */
	void modifyChannelContent(ChannelContentVo v);
	/**
	 * 编辑商品栏目
	 */
	void modifyChannelGoods(ChannelGoodsVo v);
	/**
	 * 编辑广告栏目
	 */
	void modifyChannelAdvertising(ChannelAdvertisingVo v);
	/**
	 * 获取内容列表
	 */
	List<ChannelContentVo> getChannelContentVos(GetChannelContentListKey key);
	
	public int getChannelContentCount(GetChannelContentListKey key);
	/**
	 * 获取商品列表
	 */
	List<ChannelGoodsVo> getChannelGoodsVos(GetChannelGoodsListKey key);

	List<GoodsVo> getGoodsListByChannel(GetChannelGoodsListKey key);
	String[] getChannelGoodsIds(String channelId);
	
	int getChannelGoodsCount(GetChannelGoodsListKey key);
	
	/**
	 * 获取广告列表
	 */
	List<ChannelAdvertisingVo> getChannelAdvertisingVos(GetChannelAdvertisingKey key);
	
	int getChannelAdvertisingCount(GetChannelAdvertisingKey key);
	/**
	 * 编辑楼层
	 * @throws ChannelThrowable 
	 */
	void modifyFloor(FloorInfo v) throws ChannelThrowable;
	/**
	 * 删除楼层
	 */
	void deleteFloor(String id);
	
	/**
	 * 查询栏目
	 * @param v
	 */
	ChannelVo findChannel(String id);
	/**
	 * 查询栏目
	 * @param v
	 */
	List<ChannelVo> getList(String id);
	/**
	 * 获得主菜单列表
	 * @return
	 */
	List<ChannelVo> getMainMenuList();
	
	/**
	 * 查询栏目列表
	 * @param key
	 * @return
	 */
	List<ChannelVo> getChannelList(GetChannelListKey key);
	/**
	 * 查询内容栏目
	 */
	ChannelContentVo findChannelContent(String id);
	/**
	 * 查询商品栏目
	 */
	ChannelGoodsVo findChannelGoods(String id);
	/**
	 * 查询广告栏目
	 */
	ChannelAdvertisingVo findChannelAdvertising(String id);
	/**
	 * 查询楼层
	 */
	FloorVo findFloor(String id);
	FloorInfo findFloorInfo(String id);
	/**
	 * 查询楼层列表
	 */
	List<FloorVo> getFloorList(String id);
	/**
	 * 查询楼层信息列表
	 */
	List<FloorInfo> getFloorInfoList();
	/**
	 * 删除
	 * @param id
	 */
	void delete(String id);
	
	/**
	 * 热门搜索
	 */
	void modifyPopularKeyWords(PopularKeyWordsVo popularKeyWordsVo);
	/**
	 * 查询
	 * @return
	 */
	PopularKeyWordsVo getPopularKeyWordsVo();
	/**
	 * 删除栏目商品
	 * @throws ChannelThrowable 
	 */
	void deleteChannelGoods(String channelId,String goodsId) throws ChannelThrowable;
	/**
	 * 删除广告栏目内容
	 */
	void deleteChannelAdvertising(String id);
	/**
	 * 删除内容栏目内容
	 * @param id
	 */
	void deleteChannelContent(String id);
	
	/**
	 * 栏目 商品启用/禁用
	 * @param id
	 * @param isEnable
	 * @return
	 */
	boolean updateChannelGoodsStatus(String id, boolean isEnable);
	
	/**
	 * 栏目 内容启用/禁用
	 * @param id
	 * @param isEnable
	 * @return
	 */
	boolean updateChannelContentStatus(String id, boolean isEnable);
	
	/**
	 * 栏目 广告启用/禁用
	 * @param id
	 * @param isEnable
	 * @return
	 */
	boolean updateChannelAdvertisingStatus(String id, boolean isEnable);
	
}
