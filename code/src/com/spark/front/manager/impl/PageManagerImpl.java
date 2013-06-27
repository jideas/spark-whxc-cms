package com.spark.front.manager.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spark.base.common.utils.StringUtil;
import com.spark.cms.base.constant.ChannelContentsStatus;
import com.spark.cms.base.constant.ChannelType;
import com.spark.cms.common.DataModel;
import com.spark.cms.common.generate.AdGen;
import com.spark.cms.common.generate.CategoryNavigatorGen;
import com.spark.cms.common.generate.CategoryTreeGen;
import com.spark.cms.common.generate.FloorGen;
import com.spark.cms.common.generate.MainFloorGen;
import com.spark.cms.common.generate.MenuGen;
import com.spark.cms.common.generate.NewsCenterGen;
import com.spark.cms.common.generate.ProductListGen;
import com.spark.cms.common.generate.SearchGen;
import com.spark.cms.common.generate.SliderGen;
import com.spark.cms.services.channel.ChannelService;
import com.spark.cms.services.channel.GetChannelAdvertisingKey;
import com.spark.cms.services.channel.GetChannelContentListKey;
import com.spark.cms.services.channel.GetChannelGoodsListKey;
import com.spark.cms.services.channel.info.FloorInfo;
import com.spark.cms.services.form.GoodsCategoryForm;
import com.spark.cms.services.form.GoodsForm;
import com.spark.cms.services.goods.GetGoodsListKey;
import com.spark.cms.services.goods.GoodsCategoryService;
import com.spark.cms.services.goods.GoodsService;
import com.spark.cms.services.goodsPromotion.GoodsPromotionService;
import com.spark.cms.services.messageborder.MessageborderService;
import com.spark.cms.services.vo.ChannelAdvertisingVo;
import com.spark.cms.services.vo.ChannelContentVo;
import com.spark.cms.services.vo.ChannelGoodsVo;
import com.spark.cms.services.vo.ChannelVo;
import com.spark.cms.services.vo.GoodsCategoryVo;
import com.spark.cms.services.vo.GoodsVo;
import com.spark.cms.services.vo.LightGoodsCategoryTree;
import com.spark.front.manager.PageManager;
import com.spark.front.utils.GoodsHtmlHelper;

@Component
public class PageManagerImpl implements PageManager {
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private GoodsPromotionService promotionService;
	@Autowired
	private ChannelService channelService;
	@Autowired
	private GoodsCategoryService goodsCategoryService;
	@Autowired
	private GoodsCategoryService categoryService;
	@Autowired
	private MessageborderService messageborderService;
	
	/**
	 * 生成菜单栏
	 * 
	 * @return
	 */
	public String getMenuHtml(String path) {
		List<ChannelVo> menuList = channelService.getMainMenuList();
		return MenuGen.getMenuHtml(menuList, path);
	}
	
	/**
	 * 生成菜单栏
	 * 
	 * @return
	 */
	public String getMenuHtmlIndex(String path) {
		List<ChannelVo> menuList = channelService.getMainMenuList();
		return MenuGen.getMenuHtmlIndex(menuList, path);
	}

	/**
	 * 生成菜单
	 */
	public String getGoodsCategoryHiddenTreeHtml() {
		LightGoodsCategoryTree categoryTree = categoryService.getLightGoodsCategoryTree();
		return CategoryTreeGen.getTreeHtml(categoryTree);
	}

	/**
	 * 生成产品栏
	 */
	public String getGoodsCategoryTreeHtml() {
		LightGoodsCategoryTree categoryTree = categoryService.getLightGoodsCategoryTree();
		return CategoryTreeGen.getTreeHtml(categoryTree);
	}

	/**
	 * 咨询促销
	 */
	public String getNewsList(String id, String page, String rows, String path) {
		GetChannelContentListKey key = new GetChannelContentListKey();
		key.setStatus(ChannelContentsStatus.ENABLE);
		key.setChannelId(id);
		key.setOffset(0);
		key.setPageSize(Integer.valueOf(rows));

		List<ChannelContentVo> contentList = channelService.getChannelContentVos(key);
		return NewsCenterGen.getNewsHtml(contentList, path);
	}

	/**
	 * 资讯/促销【导航】
	 */
	public String getNewsNavigation(String page, String rows, String path) {
		String str = "";
		str += NewsCenterGen.getNewsNavBeginOrEnd(true);

		ChannelVo cv31 = channelService.findChannel("9B3689226C6C30E5EB5E42F824F15B2F");
		GetChannelContentListKey key31 = new GetChannelContentListKey();
		key31.setStatus(ChannelContentsStatus.ENABLE);
		key31.setChannelId(cv31.getRecid());
		key31.setOffset(Integer.valueOf(page));
		key31.setPageSize(Integer.valueOf(rows));
		List<ChannelContentVo> contentList31 = channelService.getChannelContentVos(key31);
		str += NewsCenterGen.getNewsNavHTML(contentList31, path, cv31);

		ChannelVo cv32 = channelService.findChannel("1C9EEDA0B6FD28490AB85B72111C5A1E");
		GetChannelContentListKey key32 = new GetChannelContentListKey();
		key32.setStatus(ChannelContentsStatus.ENABLE);
		key32.setChannelId(cv32.getRecid());
		key32.setOffset(Integer.valueOf(page));
		key32.setPageSize(Integer.valueOf(rows));
		List<ChannelContentVo> contentList32 = channelService.getChannelContentVos(key32);
		str += NewsCenterGen.getNewsNavHTML(contentList32, path, cv32);

		str += NewsCenterGen.getNewsNavBeginOrEnd(false);
		return str;
	}

	/**
	 * 新闻 -> 【新闻页】【新闻栏目】点击
	 */
	public String getNewsListByChannelId(String id, int page, int rows) {
		String str = "";
		GetChannelContentListKey key = new GetChannelContentListKey();
		key.setChannelId(id);
		key.setOffset(0);
		key.setPageSize(rows);
		key.setStatus(ChannelContentsStatus.ENABLE);
		List<ChannelContentVo> contentList = channelService.getChannelContentVos(key);
		str += NewsCenterGen.getNewsListHTML(contentList);
		return str;
	}

	/**
	 * 获取内容列表
	 */
	@Override
	public ChannelContentVo getNews(String id) {
		return channelService.findChannelContent(id);
	}

	/**
	 * 产品列表
	 */
	public String showProuctsList(GetGoodsListKey key, String path) {
		List<GoodsVo> goodsList = goodsService.getGoodsList(key);
		return ProductListGen.generateProductList(goodsList, path, promotionService);
	}

	@Override
	public int getProuctsListCount(GetGoodsListKey key) {
		return goodsService.getGoodsTotalCount(key);
	}

	/**
	 * 楼层
	 */
	public String getFloorHtml(String page, String rows, String path) {
		// 获取楼层
		List<FloorInfo> fList = channelService.getFloorInfoList();
		/*begin of 改版 -> 首页只显示【生鲜】【速冻菜肴】*/
		List<FloorInfo> delete_fList = new ArrayList<FloorInfo>();
		for(FloorInfo floorVo : fList){
			if(!"4C86135A02B00D01975360C301C6B04A".equals(floorVo.getGoodsCategoryId()) && !"6110D0D5329E2FADAB41140644931916".equals(floorVo.getGoodsCategoryId())){
				delete_fList.add(floorVo);
			}
		}
		fList.removeAll(delete_fList);
		/*end of 改版 -> 首页只显示【生鲜】【速冻菜肴】*/
		for (FloorInfo floorVo : fList) {
			List<ChannelVo> list = Arrays.asList(floorVo.getChannelVos());
			floorVo.setChannelVoList(list);

			for (ChannelVo channelVo : list) {
				GetChannelGoodsListKey key = new GetChannelGoodsListKey();
				key.setStatus(ChannelContentsStatus.ENABLE);
				if (StringUtil.isNotEmpty(page) && StringUtil.isNotEmpty(rows)) {
					key.setOffset((Integer.parseInt(page) - 1) * Integer.parseInt(rows));
					key.setPageSize(Integer.parseInt(rows));
				}
				key.setChannelId(channelVo.getRecid());
				List<ChannelGoodsVo> goodsList = channelService.getChannelGoodsVos(key);
				channelVo.setGoodsModel(new DataModel<ChannelGoodsVo>(goodsList, goodsList.size()));
			}
		}
		return FloorGen.genFloors(fList, path);
	}

	/**
	 * 主楼
	 */
	public String getMainFloorHtml(String page, String rows, String path) {
		List<String> fList = new ArrayList<String>();
		fList.add("A62C2148BCE33143A2BF805480462963");
		fList.add("B291761D709379E0D40DF9927A1AE431");
		fList.add("2737A62152E25CCC5F968346E4928C01");
		fList.add("B02F6CB3D8AFAE393790CECB37C447C0");
		List<ChannelVo> channelVoslist = new ArrayList<ChannelVo>();
		// 获取楼层
		for (String channelId : fList) {
			ChannelVo channel = channelService.findChannel(channelId);
			channelVoslist.add(channel);
		}
		for (ChannelVo channelVo : channelVoslist) {
			GetChannelGoodsListKey key = new GetChannelGoodsListKey();
			key.setStatus(ChannelContentsStatus.ENABLE);
			key.setOffset(Integer.valueOf(page));
			key.setPageSize(Integer.valueOf(rows));
			if (channelVo == null || !ChannelType.GoodsChannel.getCode().equals(channelVo.getChanneltype())) {
				continue;
			}
			key.setChannelId(channelVo.getRecid());
			List<ChannelGoodsVo> goodsList = channelService.getChannelGoodsVos(key);
			if (null != goodsList && !goodsList.isEmpty()) {
				for (int index = goodsList.size() - 1; index >= 0; index--) {
					ChannelGoodsVo goods = goodsList.get(index);
					if (!goods.isPublished()) {
						goodsList.remove(index);
					}
				}
			}
			channelVo.setGoodsModel(new DataModel<ChannelGoodsVo>(goodsList, goodsList.size()));
		}
		return MainFloorGen.getMainFloor(channelVoslist, path);
	}

	/**
	 * 获取广告位
	 */
	public String getPictureAd(String id, String path) {
		GetChannelAdvertisingKey key = new GetChannelAdvertisingKey();
		key.setChannelId(id);
		key.setStatus(ChannelContentsStatus.ENABLE);
		List<ChannelAdvertisingVo> contentList = channelService.getChannelAdvertisingVos(key);
		if (contentList.size() > 0) {
			ChannelAdvertisingVo channelAdvertisingVo = contentList.get(0);
			path = path + channelAdvertisingVo.getImageurl();
			return AdGen.genAd(channelAdvertisingVo.getUrl(), path);
		}
		return "";
	}

	/**
	 * 获取分类导航 并组织成html
	 */
	@Override
	public String getNav(String categoryId, String path) {

		List<GoodsCategoryForm> gcf = new ArrayList<GoodsCategoryForm>();
		GoodsCategoryForm goodsCategoryForm = this.categoryService.getGoodsCategoryById(categoryId);

		if (goodsCategoryForm.getParentid() != null) {
			GoodsCategoryForm pgoodsCategoryForm = this.categoryService.getGoodsCategoryById(goodsCategoryForm
					.getParentid());

			if (pgoodsCategoryForm != null && pgoodsCategoryForm.getParentid() != null) {
				GoodsCategoryForm ppgoodsCategoryForm = this.categoryService.getGoodsCategoryById(pgoodsCategoryForm
						.getParentid());
				if (ppgoodsCategoryForm != null)
					gcf.add(ppgoodsCategoryForm);
			}
			gcf.add(pgoodsCategoryForm);
		}
		gcf.add(goodsCategoryForm);
		return CategoryNavigatorGen.fillCategoryNavigator(gcf, path);
	}

	/**
	 * 二级栏目页面搜索条件
	 */
	@Override
	public String getGoodsSerachItem(String categoryId) {
		return getChildrenGoodsCategoryHtml(categoryId) + SearchGen.genDefaultPriceSearch()
				+ getGoodsCategoryPropHtml(categoryId);
	}

	public String getGoodsCategoryPropHtml(String categoryId) {
		GoodsCategoryForm categoryInfo = categoryService.getGoodsCategoryById(categoryId);
		return SearchGen.getGoodsCategoryPropHtml(categoryInfo);
	}

	public String getChildrenGoodsCategoryHtml(String categoryId) {
		List<GoodsCategoryVo> gcvlist = categoryService.getChildrenCategoryList(categoryId);
		if (gcvlist != null && gcvlist.size() > 0) {
			GoodsCategoryForm categoryInfo = categoryService.getGoodsCategoryById(categoryId);
			if (categoryInfo != null && categoryInfo.getParentid() != null) {
				GoodsCategoryForm pcategoryInfo = categoryService.getGoodsCategoryById(categoryInfo.getParentid());
				if (pcategoryInfo != null && pcategoryInfo.getParentid() != null) {
					GoodsCategoryForm ppcategoryInfo = categoryService.getGoodsCategoryById(categoryInfo.getParentid());
					if (ppcategoryInfo != null && ppcategoryInfo.getRecid() != null) {
						gcvlist = categoryService.getChildrenCategoryList(ppcategoryInfo.getRecid());
					}
				}
			}
		}
		return SearchGen.genCategorySearch(gcvlist);
	}

	@Override
	public String getPopularGoodsHtml(String categoryId, String path) {

		List<GoodsForm> gflist = this.goodsService.getPopularGoodsList(categoryId);
		String str = "";
		str += GoodsHtmlHelper.getPopularBegin();
		if (gflist == null || gflist.size() == 0) {
			str += "</div>";
			return str;
		}
		for (int i = 0; i < gflist.size(); i++) {
			if (i > 3)
				break;
			GoodsForm goodsVo = gflist.get(i);
			String imgsrc = null;
			if (goodsVo.getPicturepath2() != null) {
				imgsrc = goodsVo.getPicturepath2();
			} else if (goodsVo.getPicturepath3() != null) {
				imgsrc = goodsVo.getPicturepath3();
			} else if (goodsVo.getPicturepath1() != null) {
				imgsrc = goodsVo.getPicturepath1();
			}
			str += GoodsHtmlHelper.getItemBeginOrEnd(true);
			str += GoodsHtmlHelper.getSmallGoodsHtml(goodsVo.getRecid(), imgsrc, goodsVo.getGoodsname(), goodsVo
					.getGoodsspec(), String.valueOf(goodsVo.getRealprice()),goodsVo.getGoodsunit(), path);
			str += GoodsHtmlHelper.getItemBeginOrEnd(false);
		}
		str += "</div>";
		return str;
	}

	@Override
	public String getHotGoodsList(String categoryId, String path) {
		return SliderGen.getSliderHTML(goodsService.getHotGoodsList(categoryId), path);
	}
	

}
