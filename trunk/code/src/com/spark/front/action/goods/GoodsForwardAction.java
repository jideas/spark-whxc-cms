/**
 * 
 */
package com.spark.front.action.goods;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.spark.base.common.system.dic.DictionaryType;
import com.spark.base.common.system.dic.SparkDictionaryManager;
import com.spark.base.common.utils.DateUtil;
import com.spark.base.common.utils.DoubleUtil;
import com.spark.cms.action.BaseAction;
import com.spark.cms.base.constant.ChannelContentsStatus;
import com.spark.cms.common.Constant;
import com.spark.cms.common.MessageModel;
import com.spark.cms.common.Constant.Base.GoodsType;
import com.spark.cms.services.ServiceMessage;
import com.spark.cms.services.channel.ChannelService;
import com.spark.cms.services.channel.GetChannelAdvertisingKey;
import com.spark.cms.services.form.GoodsCategoryForm;
import com.spark.cms.services.form.GoodsContentForm;
import com.spark.cms.services.form.GoodsForm;
import com.spark.cms.services.goods.GoodsCategoryService;
import com.spark.cms.services.goods.GoodsService;
import com.spark.cms.services.goodsPromotion.GoodsPromotionService;
import com.spark.cms.services.vo.ChannelAdvertisingVo;
import com.spark.cms.services.vo.GoodsPromotionVo;
import com.spark.front.utils.CmsString;
import com.spark.front.utils.GoodsHtmlHelper;

/**
 * @author Jideas
 * 
 */
@Controller
public class GoodsForwardAction extends BaseAction {

	@Autowired
	private GoodsService goodsService;
	@Autowired
	private GoodsPromotionService goodsPmtService;
	@Autowired
	private GoodsCategoryService categoryService;
	@Autowired
	private ChannelService channelService;

	/**
	 * 打开商品详情
	 * 
	 * @param from
	 * @return
	 */
	@RequestMapping("/toGoodsInfoPage")
	public ModelAndView toGoodsInfoPage(@RequestParam(value = "id", required = false)
	String goodsId, @RequestParam(value = "sign", required = false)
	String sign, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		String basePath = request.getContextPath();
		if (CmsString.isEmpty(goodsId)) {
			mv.setViewName("/pub/sub/tryCatch");
			return mv;
		}
		GoodsForm goods = this.goodsService.getGoodsForm(goodsId);
		if (goods == null || !goods.isPublished()) {
			mv.setViewName("/pub/sub/tryCatch");
			return mv;
		}
		mv.setViewName("/pub/product-detail");
		// 左上角导航
		mv.addObject(GoodsKey.CategoryNavigator.toString(), fillCategoryNavigator(goods, basePath));
		// 商品信息
		mv.addObject(GoodsKey.GoodsId.toString(), goodsId);
		mv.addObject(GoodsKey.GoodsCode.toString(), goods.getGoodscode());
		mv.addObject(GoodsKey.GoodsImageUrl.toString(), basePath + goods.getPicturepath3());
		mv.addObject(GoodsKey.GoodsName.toString(), goods.getGoodsname());
		mv.addObject(GoodsKey.GoodsSpec.toString(), goods.getGoodsspec());
		if (CmsString.isEmpty(sign) || "1".equals(sign)) {
			mv.addObject(GoodsKey.GoodsPrice.toString(), "￥" + DoubleUtil.getRoundStr(goods.getRealprice()) + "元/"+goods.getGoodsunit());
			String oldPrice = "";
			if (goods.getOriginalprice() > goods.getRealprice()) {
				oldPrice = DoubleUtil.getRoundStr(goods.getOriginalprice());
			} else {
				oldPrice = DoubleUtil.getRoundStr(DoubleUtil.mul(goods.getRealprice(), 1.2));
			}
			mv.addObject(GoodsKey.GoodsOldPrice.toString(), "￥" + oldPrice + "元/"+goods.getGoodsunit());

			String code = SparkDictionaryManager.getItem(DictionaryType.VantagesType, goods.getVantagestype())
					.getCode();

			double d = DoubleUtil.mul(goods.getRealprice(), DoubleUtil.strToDouble(code));
			int vantege = Integer.parseInt(DoubleUtil.getRoundStrWithOutQfw(d, 0));
			// TODO 积分规则如果不是四舍五入，则取消下面的注释
			// if (d > vantege) {
			// vantege++;
			// }
			mv.addObject(GoodsKey.VantegesRule.toString(), vantege + "");
			// 促销信息
			fillPmtInfo(goods, mv);
			mv.addObject(GoodsKey.GoodsType.toString(), "1");
			// 规格选择
			fillSpecSelector(goods, mv);
		} else if ("2".equals(sign)) {
			mv.addObject(GoodsKey.GoodsPrice.toString(), DoubleUtil.getRoundStr(goods.getVantagesCost(), 0) + "积分");
			mv.addObject(GoodsKey.GoodsOldPrice.toString(), DoubleUtil.getRoundStr(DoubleUtil.mul(goods
					.getVantagesCost(), 1.2), 0)
					+ "积分");
			mv.addObject(GoodsKey.VantegesRule.toString(), "<font color=red >积分商城商品不赠送积分！</font>");
			mv.addObject(GoodsKey.PromotionInfo.toString(), "商品暂未促销");
			mv.addObject(GoodsKey.GoodsType.toString(), "2");

			StringBuilder ss = new StringBuilder();
			ss.append("<div class=\"buying_specil_selected\"  onselectstart=\"return false\" ");
			ss.append(" id=\"specSelect" + goods.getRecid() + "\">");
			ss.append(goods.getGoodsspec());
			ss.append("</div>");
			mv.addObject(GoodsKey.SpecSelector.toString(), ss.toString());
		}
		if (goods.getGoodsType().equals(GoodsType.Booking.getCode())) {
			mv.addObject(GoodsKey.IsBooking.toString(), "");
		} else {
			mv.addObject(GoodsKey.IsBooking.toString(), "none");
		}
		// 同类人气商品
		fillCategoryPopularGoods(goods, mv, basePath);
		fileContentInfo(goods, mv);
		return mv;
	}

	/**
	 * @param goods
	 * @param mv
	 * @param basePath
	 */
	private void fillCategoryPopularGoods(GoodsForm goods, ModelAndView mv, String basePath) {
		List<GoodsForm> gflist = this.goodsService.getPopularGoodsList(goods.getCategoryid3());
		List<String> htmllist = new ArrayList<String>();
		if (null == gflist || gflist.isEmpty()) {
			return;
		}
		for (GoodsForm form : gflist) {
			if (null == form || form.getRecid().equals(goods.getRecid())) {
				continue;
			}
			if (htmllist.size() == 3) {
				break;
			}
			String price = DoubleUtil.getRoundStr(form.getRealprice());
			GoodsPromotionVo pmt = this.goodsPmtService.findByGoodsId(form.getRecid());
			if (null != pmt && pmt.getDisrate() > 0 && pmt.getDisrate() < 1) {
				price = (DoubleUtil.getRoundStr(DoubleUtil.mul(form.getRealprice(), pmt.getDisrate())));
			}
			htmllist.add(GoodsHtmlHelper.getSmallGoodsHtml(form.getRecid(), form.getPicturepath2(),
					form.getGoodsname(), form.getGoodsspec(), price,form.getGoodsunit(), basePath));
		}
		mv.addObject(GoodsKey.SameCategoryPopularGoods.toString(), htmllist);
	}

	/**
	 * @param goods
	 * @param mv
	 */
	private void fileContentInfo(GoodsForm goods, ModelAndView mv) {
		if (null == goods.getGoodsContentForms() || goods.getGoodsContentForms().isEmpty()) {
			return;
		}
		List<String> titles = new ArrayList<String>();
		List<String> contents = new ArrayList<String>();
		for (GoodsContentForm form : goods.getGoodsContentForms()) {
			titles.add(form.getContenttitle());
			contents.add(form.getGoodscontent());
		}
		// 内容介绍
		mv.addObject(GoodsKey.GoodsContentTitles.toString(), titles);
		mv.addObject(GoodsKey.GoodsContentHtmls.toString(), contents);
	}

	private void fillPmtInfo(GoodsForm goods, ModelAndView mv) {
		String message = "";
		GoodsPromotionVo pmt = goodsPmtService.findByGoodsId(goods.getRecid());
		if (null == pmt) {
			if (goods.isFreedelivery()) {
				mv.addObject(GoodsKey.PromotionInfo.toString(), "<font color=red>本商品享受免费送货上门</font>");
			} else {
				mv.addObject(GoodsKey.PromotionInfo.toString(), "商品暂未促销");
			}
			return;
		}
		if (goods.isFreedelivery()) {
			message = "本商品享受免费送货上门";
		} else {
			message = "商品促销中";
		}
		if (pmt.getDisrate() > 0 && pmt.getDisrate() < 1) {
			mv.addObject(GoodsKey.GoodsPrice.toString(), "￥"
					+ DoubleUtil.getRoundStr(DoubleUtil.mul(goods.getRealprice(), pmt.getDisrate())) + "元/"+goods.getGoodsunit());
			if (goods.isFreedelivery()) {
				message += ",且享受促销价格";
			}
		} else {
			if (goods.isFreedelivery()) {
				mv.addObject(GoodsKey.PromotionInfo.toString(), message);
			}
			return;
		}
		if (pmt.getPcount() > 0) {
			message += ",促销剩余数量：" + DoubleUtil.getRoundStr(pmt.getPcount() - pmt.getSaledcount(), 0);
		}
		if (pmt.getEndtime() > new Date().getTime()) {
			message += ",促销截至时间：" + DateUtil.dateFromat(pmt.getEndtime(), DateUtil.DATE_TIME_PATTERN2);
		}
		mv.addObject(GoodsKey.PromotionInfo.toString(), message);
	}

	private void fillSpecSelector(GoodsForm goods, ModelAndView mv) {
		StringBuilder ss = new StringBuilder();
		List<GoodsForm> list = this.goodsService.getSameGoodsList(goods.getGoodscode());
		if (list == null || list.isEmpty()) {
			mv.addObject(GoodsKey.SpecSelector.toString(), ss.toString());
			return;
		}
		for (GoodsForm g : list) {
			if (g.getRecid().equals(goods.getRecid())) {
				ss.append("<div class=\"buying_specil_selected\"  onselectstart=\"return false\" ");
			} else {
				ss.append("<div class=\"buying_specil_select\" onselectstart=\"return false\" ");
			}
			ss.append(" onclick=\"changeGoodsSpec(");
			ss.append("'" + g.getRecid() + "'");
			ss.append(",'" + g.getGoodsspec() + "'");
			String price = DoubleUtil.getRoundStr(g.getRealprice());
			String message = "";
			String oldPrice = "";
			if (g.getOriginalprice() > g.getRealprice()) {
				oldPrice = DoubleUtil.getRoundStr(g.getOriginalprice());
			} else {
				oldPrice = DoubleUtil.getRoundStr(DoubleUtil.mul(g.getRealprice(), 1.2));
			}
			oldPrice = "￥" + oldPrice + "元/"+goods.getGoodsunit();
			String code = SparkDictionaryManager.getItem(DictionaryType.VantagesType, goods.getVantagestype())
					.getCode();
			double d = DoubleUtil.mul(g.getRealprice(), DoubleUtil.strToDouble(code));
			int vantege = Integer.parseInt(DoubleUtil.getRoundStrWithOutQfw(d, 0));
			GoodsPromotionVo pmt = goodsPmtService.findByGoodsId(g.getRecid());
			if (null != pmt && pmt.getDisrate() > 0 && pmt.getDisrate() < 1) {
				message = "商品促销中,";
				if (g.isFreedelivery()) {
					message = "本商品享受免费送货上门,且价格促销中";
				}
				price = DoubleUtil.getRoundStr(DoubleUtil.mul(g.getRealprice(), pmt.getDisrate()));
			} else {
				message = "商品暂未促销";
				if (g.isFreedelivery()) {
					message = "<font color=red>本商品享受免费送货上门</font>";
				}
			}
			if (null != pmt && pmt.getPcount() > 0) {
				message += ",促销剩余数量：" + DoubleUtil.getRoundStr(pmt.getPcount() - pmt.getSaledcount(), 0);
			}
			if (null != pmt && pmt.getEndtime() > new Date().getTime()) {
				message += ",促销截至时间：" + DateUtil.dateFromat(pmt.getEndtime(), "yyyy-MM-dd hh:mm:ss");
			}
			String bookingText = "none";
			if (g.getGoodsType().equals(GoodsType.Booking.getCode())) {
				bookingText = "";
			}
			ss.append(",'￥" + price + "元/"+goods.getGoodsunit()+"'");
			ss.append(",'" + message + "'");
			ss.append(",'" + oldPrice + "'");
			ss.append(",'" + vantege + "'");
			ss.append(",'" + bookingText + "'");
			ss.append(")\" id=\"specSelect" + g.getRecid() + "\">");
			ss.append(g.getGoodsspec());
			ss.append("</div>");
		}
		mv.addObject(GoodsKey.SpecSelector.toString(), ss.toString());
	}

	/**
	 * @param goods
	 */
	private String fillCategoryNavigator(GoodsForm goods, String path) {
		StringBuilder category = new StringBuilder(
				"<a class=\"canClickSpan\"  href=\"javascript:void(0)\" onclick=\"navigatorForward('" + path);
		category.append("')\">首页</a> <span>&gt;</span>");
		String categoryUrl = path + "/front/toCategoryPage/";
		GoodsCategoryForm gc1 = this.categoryService.getGoodsCategoryById(goods.getCategoryid3());
		category.append("<a class=\"canClickSpan\"  href=\"javascript:void(0)\" onclick=\"navigatorForward('"
				+ categoryUrl + gc1.getRecid() + "')\">");
		category.append(gc1.getCategoryname());
		category.append("</a><span>&gt;</span>");
		GoodsCategoryForm gc2 = this.categoryService.getGoodsCategoryById(goods.getCategoryid2());
		category.append("<a class=\"canClickSpan\" href=\"javascript:void(0)\" onclick=\"navigatorForward('"
				+ categoryUrl + gc2.getRecid() + "')\">");
		category.append(gc2.getCategoryname());
		category.append("</a><span>&gt;</span><span>");
		category.append(this.categoryService.getGoodsCategoryById(goods.getCategoryid1()).getCategoryname());
		category.append("</span>");
		return category.toString();
	}

	/**
	 * 是否已登录
	 * 
	 * @param from
	 * @return
	 */
	@RequestMapping("/getDownStairImages")
	@ResponseBody
	public ResponseEntity<MessageModel> getDownStairImages(HttpServletRequest request) {
		String[] array = new String[4];
		GetChannelAdvertisingKey key = new GetChannelAdvertisingKey();
		key.setChannelId(Constant.SECOND_PAGE_CHANNELID_ONE);
		key.setStatus(ChannelContentsStatus.ENABLE);
		List<ChannelAdvertisingVo> list0 = this.channelService.getChannelAdvertisingVos(key);
		if (null == list0 || list0.isEmpty()) {
			return new ServiceMessage(false, "", 0).getMessageModel();
		}
		array[0] = list0.get(0).getImageurl();
		array[2] = list0.get(0).getUrl();
		key.setChannelId(Constant.SECOND_PAGE_CHANNELID_TWO);
		List<ChannelAdvertisingVo> list1 = this.channelService.getChannelAdvertisingVos(key);
		if (null == list1 || list1.isEmpty()) {
			return new ServiceMessage(false, "", 0).getMessageModel();
		}
		array[1] = list1.get(0).getImageurl();
		array[3] = list1.get(0).getUrl();
		return new ServiceMessage(true, "", array).getMessageModel();
	}
}
