package com.spark.front.manager;

import com.spark.cms.services.goods.GetGoodsListKey;
import com.spark.cms.services.vo.ChannelContentVo;
import com.spark.cms.services.vo.ChannelVo;


public interface PageManager {
	
	/**
	 * 生成菜单栏
	 * @return
	 */
	public String getMenuHtml(String path) ;
	
	/**
	 * 生成菜单栏（首页）
	 * @return
	 */
	public String getMenuHtmlIndex(String path) ;
	
	/**
	 * 生成隐藏产品栏
	 * @return
	 */
	public String getGoodsCategoryHiddenTreeHtml() ;
	
	/**
	 * 生成产品栏
	 */
	public String getGoodsCategoryTreeHtml() ;
	
	/**
	 * 咨询促销
	 */
	public String getNewsList(String id,String page,String rows,String path) ;
	
	/**
	 * 资讯/促销【导航】
	 */
	public String getNewsNavigation(String page, String rows, String path);
	
	/**
	 * 新闻 -> 【新闻页】【新闻栏目】点击
	 */
	public String getNewsListByChannelId(String id,int page,int rows);
	
	/**
	 * 产品列表
	 */
	public String showProuctsList(GetGoodsListKey key,String path);
	
	/**
	 * 楼层
	 */
	public String getFloorHtml(String page,String rows,String path);
	
	/**
	 * 主楼
	 */
	public String getMainFloorHtml(String page,String rows,String path);
	
	/**
	 * 获取广告位
	 */
	public String  getPictureAd(String id,String path);
	
	/**
	 * 获取Nav
	 */
	public String getNav(String categoryId, String path);

	/**
	 * 二级栏目页面搜索条件
	 */
	public String getGoodsSerachItem(String categoryId);

	int getProuctsListCount(GetGoodsListKey key);
	
	public ChannelContentVo getNews(String id);

	String getPopularGoodsHtml(String categoryId,String path);

	String getHotGoodsList(String goodsCode, String path);

}
