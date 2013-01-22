package com.spark.front.manager;

import com.spark.cms.services.goods.GetGoodsListKey;
import com.spark.cms.services.vo.ChannelContentVo;
import com.spark.cms.services.vo.ChannelVo;


public interface PageManager {
	
	/**
	 * ���ɲ˵���
	 * @return
	 */
	public String getMenuHtml(String path) ;
	
	/**
	 * ���ɲ˵�������ҳ��
	 * @return
	 */
	public String getMenuHtmlIndex(String path) ;
	
	/**
	 * �������ز�Ʒ��
	 * @return
	 */
	public String getGoodsCategoryHiddenTreeHtml() ;
	
	/**
	 * ���ɲ�Ʒ��
	 */
	public String getGoodsCategoryTreeHtml() ;
	
	/**
	 * ��ѯ����
	 */
	public String getNewsList(String id,String page,String rows,String path) ;
	
	/**
	 * ��Ѷ/������������
	 */
	public String getNewsNavigation(String page, String rows, String path);
	
	/**
	 * ���� -> ������ҳ����������Ŀ�����
	 */
	public String getNewsListByChannelId(String id,int page,int rows);
	
	/**
	 * ��Ʒ�б�
	 */
	public String showProuctsList(GetGoodsListKey key,String path);
	
	/**
	 * ¥��
	 */
	public String getFloorHtml(String page,String rows,String path);
	
	/**
	 * ��¥
	 */
	public String getMainFloorHtml(String page,String rows,String path);
	
	/**
	 * ��ȡ���λ
	 */
	public String  getPictureAd(String id,String path);
	
	/**
	 * ��ȡNav
	 */
	public String getNav(String categoryId, String path);

	/**
	 * ������Ŀҳ����������
	 */
	public String getGoodsSerachItem(String categoryId);

	int getProuctsListCount(GetGoodsListKey key);
	
	public ChannelContentVo getNews(String id);

	String getPopularGoodsHtml(String categoryId,String path);

	String getHotGoodsList(String goodsCode, String path);

}
