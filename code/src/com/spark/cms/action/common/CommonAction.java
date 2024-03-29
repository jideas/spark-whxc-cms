package com.spark.cms.action.common;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spark.base.common.key.SortType;
import com.spark.base.common.system.dic.DicItem;
import com.spark.base.common.system.dic.DictionaryType;
import com.spark.base.common.system.dic.SparkDictionaryManager;
import com.spark.base.common.utils.DateUtil;
import com.spark.base.common.utils.StringUtil;
import com.spark.cms.action.BaseAction;
import com.spark.cms.base.constant.SearchConstant;
import com.spark.cms.common.Constant;
import com.spark.cms.common.DataModel;
import com.spark.cms.services.form.station.StationListItem;
import com.spark.cms.services.goods.GetGoodsListKey;
import com.spark.cms.services.goods.GoodsService;
import com.spark.cms.services.station.GetStationListKey;
import com.spark.cms.services.station.StationService;
import com.spark.cms.services.vo.GoodsVo;
import com.spark.cms.services.vo.MemberVo;
import com.spark.cms.services.vo.QuestionVo;
import com.spark.cms.services.vo.StationVo;

/**
 * 基础数据
 * 
 * @author Administrator
 * 
 */
@Controller
public class CommonAction extends BaseAction {

	@Autowired
	private StationService satationService;
	@Autowired
	private GoodsService goodsService;

	@RequestMapping("/common/getAreaList")
	@ResponseBody
	public DataModel<DicItem> getAreaList(String parentId) {
		if (StringUtil.isEmpty(parentId)) {
			parentId = null;
		}
		try {
			List<DicItem> list = SparkDictionaryManager.getDicItemsList(
					DictionaryType.Area, parentId);
			return new DataModel<DicItem>(list, list.size());
		} catch (Exception e) {
			return new DataModel<DicItem>(new ArrayList<DicItem>(), 0);
		}
	}

	@RequestMapping("/common/getDeliveryTimeList")
	@ResponseBody
	public DataModel<DicItem> getDeliveryTimeList(
			@RequestParam(value = "isBook", required = true)
			boolean isBook) {

		List<DicItem> rlist = new ArrayList<DicItem>();
		List<String> dayList = new ArrayList<String>();
		
		
		if (isBook) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date(System.currentTimeMillis()));
			cal.add(Calendar.DAY_OF_MONTH, 2);
			int days = 4;
			
			/*begin of 粽子促销【端午节】*/
			Calendar calendar = Calendar.getInstance();			
			calendar.set(2013, 5, 9, 0, 0, 0);
			if(cal.getTimeInMillis() < calendar.getTimeInMillis()){
				cal.set(2013, 5, 10, 0, 0, 0);
			}
			/*end of 粽子促销【端午节】*/
			
			for(int i=1;i<=days;i++)
			{
				if(i>1)
				cal.add(Calendar.DAY_OF_MONTH, 1);
				dayList.add(DateUtil.dateFromat(cal.getTime())+" 11:00");
				dayList.add(DateUtil.dateFromat(cal.getTime())+" 17:00");
			}
		} else {
			String today = DateUtil.dateFromat(System.currentTimeMillis());
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date(System.currentTimeMillis()));
			int toHour = cal.get(Calendar.HOUR_OF_DAY);
			int days = 4;
			if (0 <= toHour && toHour < 11) {
				dayList.add(today + " 17:00");
				days = 3;
			} 
			for(int i=1;i<=days;i++)
			{
				cal.add(Calendar.DAY_OF_MONTH, 1);
				dayList.add(DateUtil.dateFromat(cal.getTime())+" 11:00");
				dayList.add(DateUtil.dateFromat(cal.getTime())+" 17:00");
			}
		}
		try {
			for(String time:dayList)
			{
				DicItem di = new DicItem(time,time);
				rlist.add(di);
			}
//			List<DicItem> list = SparkDictionaryManager
//					.getDicItemsList(DictionaryType.DeliveryHour);
			return new DataModel<DicItem>(rlist, rlist.size());
		} catch (Exception e) {
			return new DataModel<DicItem>(new ArrayList<DicItem>(), 0);
		}
	}

	@RequestMapping("/common/getStation")
	@ResponseBody
	public DataModel<StationListItem> getStationListByArea(String areaCode) {
		if (StringUtil.isEmpty(areaCode)) {
			return new DataModel<StationListItem>(
					new ArrayList<StationListItem>(), 0);
		}
		try {
			List<StationListItem> list = satationService
					.getComboListItem(new GetStationListKey(areaCode));
			return new DataModel<StationListItem>(list, list.size());
		} catch (Exception e) {
			return new DataModel<StationListItem>(
					new ArrayList<StationListItem>(), 0);
		}
	}

	@RequestMapping("/common/getStationList")
	@ResponseBody
	public DataModel<StationVo> getStationList(String areaCode) {
		List<StationVo> list = satationService.getList(new GetStationListKey(
				areaCode));
		for (StationVo v : list) {
			v.setProvinceTitle(SparkDictionaryManager.getItem(
					DictionaryType.Area, v.getProvince()).getTitle());
			v.setCityTitle(SparkDictionaryManager.getItem(DictionaryType.Area,
					v.getCity()).getTitle());
			v.setTownTitle(SparkDictionaryManager.getItem(DictionaryType.Area,
					v.getTown()).getTitle());
		}
		return new DataModel<StationVo>(list, list.size());

	}

	/**
	 * 获取商品列表
	 */
	@RequestMapping("/common/searchGoods")
	@ResponseBody
	public String getGoodsList(String searchKey, int pageNumber,
			double priceBegin, double priceEnd, String sortType, String sortWay) {
		try {
			GetGoodsListKey key = new GetGoodsListKey();
			key.setOffset((pageNumber - 1) * 12);
			key.setPageSize(12);
			key.setPublished(true);
			key.setSearchText(searchKey);
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
			resultInfo.setSearchKey(searchKey);
			resultInfo.setTotalCount(goodsService.getGoodsTotalCount(key));
			return JSONObject.fromObject(resultInfo).toString();
		} catch (Exception e) {
			log.error("搜索发生异常====" + e.getStackTrace());
			e.printStackTrace();
			return "{}";
		}
	}

	@RequestMapping("/search")
	public String search(String searchKey, HttpServletRequest request) {
		if (StringUtil.isEmpty(searchKey))
			return "/pub/search";
		GetGoodsListKey key = new GetGoodsListKey();
		key.setOffset(0);
		key.setPageSize(12);
		key.setPublished(true);
		key.setSearchText(searchKey);
		key.setSortColumn(GetGoodsListKey.SortColumn.PRICE);
		key.setSortType(SortType.Asc);
		List<GoodsVo> goodsList = goodsService.getGoodsList(key);

		SearchResultInfo initResult = new SearchResultInfo();
		initResult.setGoodsList(goodsList);
		initResult.setTotalCount(goodsService.getGoodsTotalCount(key));
		initResult.setSearchKey(searchKey);
		request.setAttribute(SearchConstant.INITRESULT, JSONObject.fromObject(
				initResult).toString());
		return "/pub/search";
	}

}
