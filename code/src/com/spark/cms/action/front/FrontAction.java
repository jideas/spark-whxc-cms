package com.spark.cms.action.front;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.spark.base.common.key.SortType;
import com.spark.base.common.utils.StringUtil;
import com.spark.cms.action.BaseAction;
import com.spark.cms.action.common.SearchResultInfo;
import com.spark.cms.base.constant.ChannelContentsStatus;
import com.spark.cms.base.constant.SearchConstant;
import com.spark.cms.base.utils.BussinessCommon;
import com.spark.cms.common.Constant;
import com.spark.cms.common.ResponseEntityUtil;
import com.spark.cms.services.channel.ChannelService;
import com.spark.cms.services.channel.GetChannelGoodsListKey;
import com.spark.cms.services.goods.GetGoodsListKey;
import com.spark.cms.services.goods.GoodsCategoryService;
import com.spark.cms.services.goods.GoodsService;
import com.spark.cms.services.vo.ChannelContentVo;
import com.spark.cms.services.vo.ChannelVo;
import com.spark.cms.services.vo.GoodsVo;
import com.spark.front.manager.PageManager;

@Controller
public class FrontAction extends BaseAction{	
	@Autowired
	private PageManager pageManager;
	@Autowired
	private ChannelService channelService;
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private GoodsCategoryService categoryService;
	
	@RequestMapping("/channel/getMenuHtml")
	@ResponseBody
	public List<String> getMenuHtml(HttpServletRequest request) {
		List<String> menuHtml = new ArrayList<String>();
		try {
//			request.getScheme() + "://"	+ request.getServerName() + ":" + request.getServerPort();
			String webURLContext = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort();
			menuHtml.add(pageManager.getMenuHtml(webURLContext));
			return menuHtml;
		} catch (Exception e) {
			log.error("获取菜单发生异常====" + e.getStackTrace());
		}
		return null;
	}
	
	@RequestMapping("/channel/getMainFloorHtml")
	@ResponseBody
	public List<String> getMainFloorHtml(@RequestParam(value = "page", required = false) String page, @RequestParam(value = "rows", required = false)String rows,HttpServletRequest request) {
		List<String> floorHtml = new ArrayList<String>();
		String path = request.getContextPath();
		if(StringUtil.isEmpty(rows)) rows ="6";
		try {
			floorHtml.add(pageManager.getMainFloorHtml("0", rows, path));
			return floorHtml;
		} catch (Exception e) {
			log.error("获取主楼发生异常====" + e.getStackTrace());
		}
		return null;
	}
	
	@RequestMapping("/channel/getFloorHtml")
	@ResponseBody
	public List<String> getFloorHtml(@RequestParam(value = "page", required = false) String page, @RequestParam(value = "rows", required = false)String rows,HttpServletRequest request) {
		List<String> floorHtml = new ArrayList<String>();
		String path = request.getContextPath();
		try {
			floorHtml.add(pageManager.getFloorHtml(page, rows, path));
			return floorHtml;
		} catch (Exception e) {
			log.error("获取菜单发生异常====" + e.getStackTrace());
		}
		return null;
	}
	
	@RequestMapping(value = "/goods/getCategoryTreeHtml")
	@ResponseBody
	public ResponseEntity<List<String>> getGoodsCategoryTreeHtml() {
		List<String> list = new ArrayList<String>();
		list.add(pageManager.getGoodsCategoryTreeHtml());
		return ResponseEntityUtil.getResponseEntity(list);
	} 
	
	
	
	@RequestMapping("/channel/getNewsInfo")
	@ResponseBody
	public List<String> getNewsList(String id,String page,String rows,HttpServletRequest request) {
		List<String> newsHtml = new ArrayList<String>();
		String path = request.getContextPath();
		try {
			newsHtml.add(pageManager.getNewsList(id, page, rows,path));
			return newsHtml;
		} catch (Exception e) {
			log.error("获取新闻资讯异常====" + e.getStackTrace());
		}
		return null;
	}
	
	/**
	 * 新闻 -> 【首页】点击
	 * @param id
	 * @param offset
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/channel/getNews/{id}")
	public ModelAndView getNewsInfo(@PathVariable String id,String offset,String pageSize) {
		try {
			ModelAndView mav = new ModelAndView();
			mav.addObject("news", pageManager.getNews(id));
			//导航
			mav.addObject("newsNav",pageManager.getNewsNavigation("0", "10", ""));
			//栏目标题
			ChannelVo cv = channelService.findChannel(pageManager.getNews(id).getChannelid());
			mav.addObject("channelTitle", cv.getName());
			//跳转路径
			mav.setViewName("/pub/news");
			return mav;
		} catch (Exception e) {
			log.error("获取新闻资讯异常====" + e.getStackTrace());
		}
		return null;
	}
	
	/**
	 * 新闻 -> 【新闻页】点击
	 * @param id
	 * @param offset
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/channel/getNewsLocal/{id}")
	@ResponseBody
	public ChannelContentVo getNewsInfo(@PathVariable String id) {
		try {
			return pageManager.getNews(id);
		} catch (Exception e) {
			log.error("获取新闻资讯异常====" + e.getStackTrace());
		}
		return null;
	}
	
	/**
	 * 新闻 -> 【首页】【新闻栏目】点击
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping("/channel/getNewsListToNews/{id}")
	public ModelAndView getNewsListToNews(@PathVariable String id,String offset,String pageSize) {
		ModelAndView mav = new ModelAndView();
		try {
			//导航
			mav.addObject("newsNav",pageManager.getNewsNavigation("0", "10", ""));
			//栏目标题
			ChannelVo cv = channelService.findChannel(id);
			mav.addObject("channelTitle", cv.getName());
			mav.addObject("channelId",id);
			//新闻列表
			mav.addObject("newsList", pageManager.getNewsListByChannelId(id, 0, 15));
			//跳转路径
			mav.setViewName("/pub/news");
			return mav;
		} catch (Exception e) {
			log.error("获取新闻资讯异常====" + e.getStackTrace());
		}
		return mav;
	}
	
	/**
	 * 新闻 -> 【新闻页】【新闻栏目】点击
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping("/channel/getNewsListByChannelId/{id}")
	@ResponseBody
	public List<String> getNewsListByChannelId(@PathVariable String id) {
		try {	
			List<String> strList = new ArrayList<String>();
			strList.add(pageManager.getNewsListByChannelId(id, 0, 15));
			return strList;
		} catch (Exception e) {
			log.error("获取新闻资讯异常====" + e.getStackTrace());
		}
		return null;
	}

	@RequestMapping("/channel/getPictureAd")
	@ResponseBody
	public List<String> getPictureAd(String id,HttpServletRequest request) {
		List<String> adHtml = new ArrayList<String>();
		String path = request.getContextPath();
		try {
			adHtml.add(pageManager.getPictureAd(id, path));
			return adHtml;
		} catch (Exception e) {
			log.error("获取广告位异常====" + e.getStackTrace());
		}
		return null;
	}
	
	
	@RequestMapping("/showProuctsListHtml")
	@ResponseBody
	public List<String> showProuctsList(String goodsCategoryId,String offset,String pageSize,String orderBy,String serachInfo, String serachKey,String minPrice,String maxPrice,HttpServletRequest request) {
		List<String> goodslistHtml = new ArrayList<String>();
		try {
			GetGoodsListKey key = new GetGoodsListKey();
			if(pageSize!=null){
				if(Integer.valueOf(pageSize)>50){
					return null;
				}
			}
			String path = request.getContextPath();
			if(offset==null) offset="1";
			if(pageSize==null) pageSize = "20";
			if(minPrice==null) minPrice = "0";
			if(maxPrice==null) maxPrice = "10000";
			//设置排序规则
			if(orderBy==null){
				//默认按照价格升序
				key.setSortColumn(GetGoodsListKey.SortColumn.PRICE);
				key.setSortType(SortType.Asc);
			}else{
				String str[] = orderBy.split("~");
				if(str==null||str.length!=2)return null;
				if(SortType.Asc.name().equals(str[1])){
					key.setSortType(SortType.Asc);
				}else{
					key.setSortType(SortType.Desc);
				}
				
				if(GetGoodsListKey.SortColumn.PRICE.getColumnName().equals(str[0])){
					key.setSortColumn(GetGoodsListKey.SortColumn.PRICE);
				}else if(GetGoodsListKey.SortColumn.SALECOUNT.getColumnName().equals(str[0])){
					key.setSortColumn(GetGoodsListKey.SortColumn.SALECOUNT);
				}else if(GetGoodsListKey.SortColumn.PUBLISHDATE.getColumnName().equals(str[0])){
					key.setSortColumn(GetGoodsListKey.SortColumn.PUBLISHDATE);
				}
			}
			
			if(serachInfo!=null){
				String args[] =serachInfo.split("~") ;
				for(String arg:args){
					String keyValue[] = arg.split("|");
					if(keyValue!=null&&keyValue.length==2){
						String keymin = keyValue[0];
						String value = keyValue[1];
						if("price".equals(keymin)){
							String valuecells[] = value.split("^");
							if(valuecells!=null&&valuecells.length==2){
								key.setPriceBegin(Double.valueOf(valuecells[0]));
								key.setPriceEnd(Double.valueOf(valuecells[1]));
							}
						}else{
							key.setSearchText(value);
						}
					}
				}
			}
			
		
			key.setGoodsCategoryId(goodsCategoryId);
			key.setPublished(true);
			key.setOffset((Integer.valueOf(offset)-1) * Integer.valueOf(pageSize));
			key.setPageSize(Integer.valueOf(pageSize));
			key.setPriceBegin(Double.valueOf(minPrice));
			key.setPriceEnd(Double.valueOf(maxPrice));
			if(!StringUtil.isEmpty(serachKey)){
				key.setSearchText(serachKey);
			}
			
			goodslistHtml.add(pageManager.showProuctsList(key, path));
			
			 int allNum = pageManager.getProuctsListCount(key);
			 goodslistHtml.add(allNum+"");
			    
			 int size = Integer.valueOf(pageSize);
			 int pagenum = (allNum/size+(allNum%size>0?1:0));
			 goodslistHtml.add(""+pagenum);
			 
			 goodslistHtml.add(""+offset);
			 
			return goodslistHtml;
		} catch (Exception e) {
			log.error("获取产品列表异常====" + e.getStackTrace());
		}
		return null;
	}
	/**
	 * 获取商品列表
	 */
	@RequestMapping("/toCategoryPage/{id}")
	public ModelAndView showCategoryPage(@PathVariable String id,HttpServletRequest request) {
		
		ModelAndView mav = new ModelAndView();
		String path = request.getContextPath();
		try {
			if(null==id||StringUtil.isEmpty(id))return null;
			//获取下拉导航
			mav.addObject("nav",  pageManager.getNav(id, path));
			
			mav.addObject("serachKeyItems",pageManager.getGoodsSerachItem(id));
			//获取热卖商品
			mav.addObject("hot",pageManager.getHotGoodsList(id, path));
			mav.addObject("categoryId",id);
			
			//获取最近浏览商品  志坚提供结果
			
			//同类人气商品
			mav.addObject("pop",pageManager.getPopularGoodsHtml(id, path));
			
			mav.addObject("menu",pageManager.getMenuHtml(request.getContextPath()));
			
			mav.setViewName("/pub/productlist");
			return mav;
		} catch (Exception e) {
			log.error("获取栏目列表异常====" + e.getStackTrace());
		}
		return null;
	}
	/**
	 * 
	 * @param categoryId
	 * @return
	 */
	@RequestMapping("/getHotSaleList")
	@ResponseBody
	public String getHotSaleGoodsList(String categoryId) {
		List<GoodsVo> hotGoodsList = goodsService.getHotGoodsVoList(categoryId);
		if (hotGoodsList == null || hotGoodsList.size() < 1) return "";
		return JSONArray.fromObject(hotGoodsList).toString();
	}
	
	@RequestMapping("/getVantageGoodsList")
	@ResponseBody
	public String geVantageGoodsList(String categoryId, int pageNumber, double priceBegin, double priceEnd, String sortType, String sortWay) {
		try {
			GetGoodsListKey key = new GetGoodsListKey();
			key.setOffset((pageNumber - 1) * SearchConstant.PAGESIZE);
			key.setPageSize(SearchConstant.PAGESIZE);
			key.setPublished(true);
			key.setVantageOnly(true);
			if (StringUtil.isNotEmpty(categoryId)) {
				key.setGoodsCategoryId(categoryId);
			}
			if (priceBegin >= 0) {
				key.setPriceBegin(priceBegin);
			}
			if (priceEnd > 0) {
				key.setPriceEnd(priceEnd);
			}
			if (StringUtil.isNotEmpty(sortType)) {
				if ("0".equals(sortType)) {
					key.setSortType(SortType.Asc);
				} else {
					key.setSortType(SortType.Desc);
				}
			}
			if (StringUtil.isNotEmpty(sortWay)) {
				if ("1".equals(sortWay)) {
					key.setSortColumn(GetGoodsListKey.SortColumn.VANTAGESCOST);
				} else if ("2".equals(sortWay)) {
					key.setSortColumn(GetGoodsListKey.SortColumn.PUBLISHDATE);
				} else {
					key.setSortColumn(GetGoodsListKey.SortColumn.SALECOUNT);
				}
			}
			List<GoodsVo> goodsList = goodsService.getGoodsList(key);
			
			SearchResultInfo resultInfo = new SearchResultInfo();
			resultInfo.setGoodsList(goodsList);
			resultInfo.setSearchKey(categoryId);
			resultInfo.setTotalCount(goodsService.getGoodsTotalCount(key));
			return JSONObject.fromObject(resultInfo).toString();
		} catch (Exception e) {
			log.error("搜索发生异常====" + e.getStackTrace());
			e.printStackTrace();
			return "{}";
		}
	}
	
	@RequestMapping("/vantageGoods")
	public String vantageGoods(HttpServletRequest request) {
		GetGoodsListKey key = new GetGoodsListKey();
		key.setPublished(true);
		key.setSortColumn(GetGoodsListKey.SortColumn.VANTAGESCOST);
		key.setSortType(SortType.Asc);
		key.setVantageOnly(true);
		List<GoodsVo> vantageGoodsList = goodsService.getGoodsList(key);
		
		
		SearchResultInfo initResult = new SearchResultInfo();
		initResult.setGoodsList(vantageGoodsList);
		initResult.setTotalCount(goodsService.getGoodsTotalCount(key));
		initResult.setCategorySummarys(BussinessCommon.getCategorySummaryByGoodsList(vantageGoodsList, categoryService));
		request.setAttribute(SearchConstant.INITRESULT, initResult);
		return "/pub/vantage";
	}
	
	@RequestMapping("/getSpeicalGoodsList")
	@ResponseBody
	public String getSepcialGoodsList(String categoryId, int pageNumber, double priceBegin, double priceEnd, String sortType, String sortWay) {
		try {
			GetChannelGoodsListKey key = new GetChannelGoodsListKey();
			key.setChannelId(Constant.SPECIAL_OFFER_CHANNELID);
			key.setStatus(ChannelContentsStatus.ENABLE);
			key.setSortType(SortType.Asc);
			key.setOffset((pageNumber - 1) * SearchConstant.PAGESIZE);
			key.setPageSize(SearchConstant.PAGESIZE);
			
			if (StringUtil.isNotEmpty(categoryId)) {
				key.setGoodsCategoryId(categoryId);
			}
			if (priceBegin >= 0) {
				key.setPriceBegin(priceBegin);
			}
			if (priceEnd > 0) {
				key.setPriceEnd(priceEnd);
			}
			if (StringUtil.isNotEmpty(sortType)) {
				if ("0".equals(sortType)) {
					key.setSortType(SortType.Asc);
				} else {
					key.setSortType(SortType.Desc);
				}
			}
			if (StringUtil.isNotEmpty(sortWay)) {
				if ("1".equals(sortWay)) {
					key.setSortColumn(GetChannelGoodsListKey.SortColumn.PRICE);
				} else if ("2".equals(sortWay)) {
					key.setSortColumn(GetChannelGoodsListKey.SortColumn.PUBLISHDATE);
				} else {
					key.setSortColumn(GetChannelGoodsListKey.SortColumn.SALECOUNT);
				}
			}
			
			//List<ChannelGoodsVo> channelGoodsList = channelService.getChannelGoodsVos(key);
			
			List<GoodsVo> goodsList = channelService.getGoodsListByChannel(key);
			
			SearchResultInfo resultInfo = new SearchResultInfo();
			resultInfo.setGoodsList(goodsList);
			resultInfo.setSearchKey(categoryId);
			resultInfo.setTotalCount(channelService.getChannelGoodsCount(key));
			return JSONObject.fromObject(resultInfo).toString();
		} catch (Exception e) {
			log.error("搜索发生异常====" + e.getStackTrace());
			e.printStackTrace();
			return "{}";
		}
	}
	
	@RequestMapping("/specialGoods")
	public String specialGoods(HttpServletRequest request) {
		GetChannelGoodsListKey key = new GetChannelGoodsListKey();
		key.setChannelId(Constant.SPECIAL_OFFER_CHANNELID);
		key.setStatus(ChannelContentsStatus.ENABLE);
		key.setSortColumn(GetChannelGoodsListKey.SortColumn.PRICE);
		key.setSortType(SortType.Asc);
//		List<ChannelGoodsVo> channelGoodsList = channelService.getGoodsListByChannel(key);
		
		List<GoodsVo> goodsList = channelService.getGoodsListByChannel(key);
		
		SearchResultInfo initResult = new SearchResultInfo();
		initResult.setGoodsList(goodsList);
		initResult.setTotalCount(channelService.getChannelGoodsCount(key));
		initResult.setCategorySummarys(BussinessCommon.getCategorySummaryByGoodsList(goodsList, categoryService));
		request.setAttribute(SearchConstant.INITRESULT, initResult);
		return "/pub/specialoffer";
	}
	
	@RequestMapping("/getOneyuanGoodsList")
	@ResponseBody
	public String getOneyuanGoodsList(String categoryId, int pageNumber, double priceBegin, double priceEnd, String sortType, String sortWay) {
		try {
			GetGoodsListKey key = new GetGoodsListKey();
			key.setOffset((pageNumber - 1) * SearchConstant.PAGESIZE);
			key.setPageSize(SearchConstant.PAGESIZE);
			key.setPublished(true);
			key.setPriceBegin(0);
			key.setPriceEnd(Constant.ONE_YUAN_UPPER_LIMIT);
			
			if (StringUtil.isNotEmpty(categoryId)) {
				key.setGoodsCategoryId(categoryId);
			}
			if (StringUtil.isNotEmpty(sortType)) {
				if ("0".equals(sortType)) {
					key.setSortType(SortType.Asc);
				} else {
					key.setSortType(SortType.Desc);
				}
			}
			if (StringUtil.isNotEmpty(sortWay)) {
				if ("1".equals(sortWay)) {
					key.setSortColumn(GetGoodsListKey.SortColumn.PRICE);
				} else if ("2".equals(sortWay)) {
					key.setSortColumn(GetGoodsListKey.SortColumn.PUBLISHDATE);
				} else {
					key.setSortColumn(GetGoodsListKey.SortColumn.SALECOUNT);
				}
			}
			List<GoodsVo> goodsList = goodsService.getGoodsList(key);
			
			SearchResultInfo resultInfo = new SearchResultInfo();
			resultInfo.setGoodsList(goodsList);
			resultInfo.setSearchKey(categoryId);
			resultInfo.setTotalCount(goodsService.getGoodsTotalCount(key));
			return JSONObject.fromObject(resultInfo).toString();
		} catch (Exception e) {
			log.error("搜索发生异常====" + e.getStackTrace());
			e.printStackTrace();
			return "{}";
		}
	}
	
	@RequestMapping("/oneyuanGoods")
	public String oneyuanGoods(HttpServletRequest request) {
		GetGoodsListKey key = new GetGoodsListKey();
		key.setPublished(true);
		key.setSortColumn(GetGoodsListKey.SortColumn.PRICE);
		key.setSortType(SortType.Asc);
		key.setPriceBegin(0);
		key.setPriceEnd(Constant.ONE_YUAN_UPPER_LIMIT);
		//查询“蔬菜类”
		key.setGoodsCategoryId("124ACEA916AABA4B22CACD6C5B7C87A2");
		List<GoodsVo> vantageGoodsList = goodsService.getGoodsList(key);
		
		SearchResultInfo initResult = new SearchResultInfo();
		initResult.setGoodsList(vantageGoodsList);
		initResult.setTotalCount(goodsService.getGoodsTotalCount(key));
		initResult.setCategorySummarys(BussinessCommon.getCategorySummaryByGoodsList(vantageGoodsList, categoryService));
		request.setAttribute(SearchConstant.INITRESULT, initResult);
		return "/pub/oneyuan";
	}
}