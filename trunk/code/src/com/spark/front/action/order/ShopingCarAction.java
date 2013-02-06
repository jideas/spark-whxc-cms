package com.spark.front.action.order;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.spark.base.common.system.dic.DicItem;
import com.spark.base.common.system.dic.DictionaryType;
import com.spark.base.common.system.dic.SparkDictionaryManager;
import com.spark.base.common.utils.CheckIsNull;
import com.spark.base.common.utils.DoubleUtil;
import com.spark.base.common.utils.StringUtil;
import com.spark.cms.action.BaseAction;
import com.spark.cms.common.Constant;
import com.spark.cms.common.Constant.Base.GoodsType;
import com.spark.cms.services.ServiceMessage;
import com.spark.cms.services.channel.ChannelService;
import com.spark.cms.services.channel.GetChannelGoodsListKey;
import com.spark.cms.services.form.Login;
import com.spark.cms.services.freeDelivery.DeliveryPriceService;
import com.spark.cms.services.gift.GiftService;
import com.spark.cms.services.goods.GoodsService;
import com.spark.cms.services.goodsPromotion.GoodsPromotionService;
import com.spark.cms.services.member.MemberService;
import com.spark.cms.services.orderPromotion.OrderPromotionService;
import com.spark.cms.services.orderPromotion.result.OrderPromotionResult;
import com.spark.cms.services.vo.ChannelGoodsVo;
import com.spark.cms.services.vo.GiftVo;
import com.spark.cms.services.vo.GoodsPromotionVo;
import com.spark.cms.services.vo.GoodsVo;
import com.spark.cms.services.vo.MemberAddressVo;
import com.spark.cms.services.vo.MemberVo;
import com.spark.cms.services.vo.OrderPromotionVo;
import com.spark.front.form.order.OrderForm;
import com.spark.front.form.order.ShopingCarGoods;
import com.spark.front.utils.FrontConstant;

/**
 * 购物车
 * 
 */
@Controller
public class ShopingCarAction extends BaseAction {
	@Autowired
	private MemberService memberService;

	@Autowired
	private GoodsService goodsService;

	@Autowired
	private GoodsPromotionService goodsPromotionService;

	@Autowired
	private OrderPromotionService orderPromotionService;

	@Autowired
	private DeliveryPriceService deliveryPriceService;
	
	@Autowired
	private ChannelService channelService;
	
	@Autowired
	private GiftService giftService;

	/**
	 * 获取购物车商品列表
	 * 
	 * @return
	 */
	@RequestMapping("/shopingCar/getGoods")
	public ModelAndView getGoods(HttpServletRequest request) {
		List<ShopingCarGoods> goods = getShoppingCarGoodsList(request);
		String footerHtml = getFooterHtml(request);
		ModelAndView maw = new ModelAndView();
		maw.addObject("goodsList", goods);
		maw.addObject("footerHtml", footerHtml);
		maw.setViewName("/pro/member/shopingCar");
		return maw;
	}

	@RequestMapping(value = "/shopingCar/getGoodsListAsy")
	@ResponseBody
	public List<ShopingCarGoods> getCarGoodsList(HttpServletRequest request) {
		return getShoppingCarGoodsList(request);
	}

	private List<ShopingCarGoods> getShoppingCarGoodsList(
			HttpServletRequest request) {
		List<ShopingCarGoods> goods = new ArrayList<ShopingCarGoods>();
		Cookie[] cookies = request.getCookies();
		if (CheckIsNull.isNotEmpty(cookies)) {
			for (Cookie c : cookies) {
				if (!c.getName()
						.equals(FrontConstant.ShopingCarGoodsCookieName)
						&& !c
								.getName()
								.equals(
										FrontConstant.ShopingCarVantagesGoodsCookieName)) {
					continue;
				}
				if (c.getName().equals(FrontConstant.ShopingCarGoodsCookieName)
						&& CheckIsNull.isNotEmpty(c.getValue())) {
					String value = c.getValue();
					if (CheckIsNull.isNotEmpty(value)) {
						for (String goodsStr : value
								.split(FrontConstant.GoodsSplitRegex)) {
							if (StringUtil.isEmpty(goodsStr.trim()))
								continue;
							String goodsId = goodsStr
									.split(FrontConstant.GoodsCountSplitRegex)[0];
							double count = Double
									.valueOf(goodsStr
											.split(FrontConstant.GoodsCountSplitRegex)[1]);
							ShopingCarGoods scg = new ShopingCarGoods();
							scg.setGoodsId(goodsId);
							scg.setCount(count);
							GoodsVo v = goodsService.getGoodsVo(goodsId);
							if (null == v || !v.isPublished()) {
								continue;
							}
							if (null != v) {
								if (v.isFreedelivery()) {
									scg.setFreedelivery(true);
								}
								if(Constant.Base.GoodsType.Booking.getCode().equals(v.getGoodsType()))
									scg.setBookingGoods(true);
								scg.setOriginalprice(v.getOriginalprice());
								scg.setGoodsCode(v.getGoodscode());
								scg.setGoodsName(v.getGoodsname());
								scg.setPrice(v.getRealprice());
								scg.setSpec(v.getGoodsspec());
								scg.setVantagesType(v.getVantagestype());
								if (CheckIsNull.isNotEmpty(v.getVantagestype()))
									scg.setVantages(DoubleUtil.mul(v
											.getRealprice(), Double.valueOf(v
											.getVantagestype()),0));
								scg.setMiniPicturePath(v.getPicturepath1());
								GoodsPromotionVo gpv = goodsPromotionService
										.findByGoodsId(goodsId);
								if (null != gpv && gpv.isIsactiving()) {
									if (System.currentTimeMillis() >= gpv
											.getBegintime()
											&& System.currentTimeMillis() <= gpv
													.getEndtime()) {
										if (gpv.getPcount() > 0) {
											if ((gpv.getSaledcount() + count) <= gpv
													.getPcount()) {
												scg.setPrice(DoubleUtil.mul(scg
														.getPrice(), gpv
														.getDisrate(), 2));
												scg
														.setDisrate(gpv
																.getDisrate());
											}
										} else {
											scg.setPrice(DoubleUtil.mul(scg
													.getPrice(), gpv
													.getDisrate()));
											scg.setDisrate(gpv.getDisrate());
										}
									}
								}
								goods.add(scg);
							}

						}
					}
				} else {
					String value = c.getValue();
					if (CheckIsNull.isNotEmpty(value)) {
						for (String goodsStr : value
								.split(FrontConstant.GoodsSplitRegex)) {
							if (StringUtil.isEmpty(goodsStr.trim()))
								continue;
							String goodsId = goodsStr
									.split(FrontConstant.GoodsCountSplitRegex)[0];
							double count = Double
									.valueOf(goodsStr
											.split(FrontConstant.GoodsCountSplitRegex)[1]);
							ShopingCarGoods scg = new ShopingCarGoods();
							scg.setGoodsId(goodsId);
							scg.setCount(count);
							GoodsVo v = goodsService.getGoodsVo(goodsId);
							if (null != v) {
								// if (v.isFreedelivery()) {
								// scg.setFreedelivery(true);
								// }
								// scg.setOriginalprice(v.getOriginalprice());
								scg.setGoodsCode(v.getGoodscode());
								scg.setGoodsName(v.getGoodsname());
								// scg.setPrice(v.getRealprice());
								scg.setSpec(v.getGoodsspec());
								// scg.setVantagesType(v.getVantagestype());
								// if(CheckIsNull.isNotEmpty(v.getVantagestype()))
								// scg.setVantages(DoubleUtil.mul(v.getRealprice(),
								// Double
								// .valueOf(v.getVantagestype())));
								scg.setMiniPicturePath(v.getPicturepath1());
								if (v.isVantagesGoods()) {
									scg.setVantagesGoods(true);
									scg.setVantagesCost(v.getVantagesCost());
									goods.add(scg);
								}
							}

						}
					}
				}
			}
		}
		return goods;
	}
	
	private void removeGoodsFromCookie(Cookie cookie, String goodsIds) {
		//cookie.
	}

	/**
	 * 初始订单信息
	 * 
	 * @RequestParam(value = "vantagesGoodsArray", required = true) String
	 *                     vantagesGoodsArray,
	 * @return
	 */
	@RequestMapping("/shopingCar/toOrder")
	public ModelAndView toOrder(
			@RequestParam(value = "goodsArray", required = false)
			String goodsArray,
			@RequestParam(value = "vgoodsArray", required = false)
			String vgoodsArray, HttpServletRequest request, HttpSession session) {
		ModelAndView maw = new ModelAndView();
		Login login = null;
		try {
			if (CheckIsNull.isNotEmpty(request.getSession().getAttribute(
					Constant.LoginMemberUser)))
				login = new Login((MemberVo) request.getSession().getAttribute(
						Constant.LoginMemberUser));
		} catch (ServiceMessage e) {
			System.out.println(e.getMessage());
		}
		if (null == login || CheckIsNull.isEmpty(login.getRecid())) {
			maw.addObject("url", request.getContextPath()
					+ "/front/shopingCar/toOrder?goodsArray=" + goodsArray
					+ "&vgoodsArray=" + vgoodsArray+"&vgoodsArray="+vgoodsArray);
			maw.setViewName("/pub/sub/login");
			return maw;
		}
		boolean hasBookingGoods = false;
		boolean onlyBookingGoods = true;
		OrderForm o = new OrderForm();
		double totalAmount = 0;
		double totalVantages = 0;
		double totalVantagesCost = 0;
		List<ShopingCarGoods> goods = new ArrayList<ShopingCarGoods>();
		JSONArray jsonArray = JSONArray.fromObject(goodsArray);
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			String goodsId = jsonObject.getString("goodsId");
			double count = Double.valueOf(jsonObject.getString("count"));
			ShopingCarGoods scg = new ShopingCarGoods();
			scg.setGoodsId(goodsId);
			scg.setCount(count);
			GoodsVo v = goodsService.getGoodsVo(goodsId);
			if (null != v) {
				if (v.isFreedelivery()) {
					o.setP_freedelivery(true);
					scg.setFreedelivery(true);
				}
				scg.setOriginalprice(v.getOriginalprice());
				scg.setGoodsCode(v.getGoodscode());
				scg.setGoodsName(v.getGoodsname());
				scg.setPrice(v.getRealprice());
				scg.setSpec(v.getGoodsspec());
				scg.setVantagesType(v.getVantagestype());
				if (CheckIsNull.isNotEmpty(v.getVantagestype()))
					scg.setVantages(DoubleUtil.mul(v.getRealprice(), Double
							.valueOf(v.getVantagestype()),0));
				/**
				 * 商品促销
				 */
				GoodsPromotionVo gpv = goodsPromotionService
						.findByGoodsId(goodsId);
				if (null != gpv && gpv.isIsactiving()) {
					if (System.currentTimeMillis() >= gpv.getBegintime()
							&& System.currentTimeMillis() <= gpv.getEndtime()) {
						if (gpv.getPcount() > 0) {
							if ((gpv.getSaledcount() + count) <= gpv.getPcount()) {
								scg.setPrice(DoubleUtil.mul(scg.getPrice(), gpv
										.getDisrate(), 2));
								scg.setDisrate(gpv.getDisrate());
							}
						} else {
							scg.setPrice(DoubleUtil.mul(scg.getPrice(), gpv
									.getDisrate(), 2));
							scg.setDisrate(gpv.getDisrate());
						}
					}
				}
				if(Constant.Base.GoodsType.Booking.getCode().equals(v.getGoodsType()))
				{
					scg.setBookingGoods(true);
					hasBookingGoods = true;
				}
				else
				{
					onlyBookingGoods = false;
				}
				goods.add(scg);
			}
			totalVantages = DoubleUtil.sum(totalVantages, DoubleUtil.mul(scg
					.getVantages(), scg.getCount()));
			totalAmount = DoubleUtil.sum(totalAmount, DoubleUtil.mul(scg
					.getPrice(), scg.getCount()));

		}
		/**
		 * 整单促销
		 */
		OrderPromotionResult opr = orderPromotionService
				.findByOrderAmount(totalAmount);
		if (null != opr) {
			OrderPromotionVo opv = opr.getVo();
			if (null != opv && null != opv.getGoodsid()
					&& opv.getGoodscount() > 0) {
				ShopingCarGoods scg = new ShopingCarGoods();
				scg.setGoodsId(opv.getGoodsid());
				scg.setCount(opv.getGoodscount());
				scg.setGift(true);
				GoodsVo v = goodsService.getGoodsVo(opv.getGoodsid());
				if (null != v) {
					scg.setOriginalprice(v.getOriginalprice());
					scg.setGoodsCode(v.getGoodscode());
					scg.setGoodsName(v.getGoodsname());
					scg.setPrice(0d);
					scg.setSpec(v.getGoodsspec());
					scg.setVantagesType("0");
					scg.setVantages(0);
					goods.add(scg);
				}
				
			}
			if (opr.isFreeDelivery()) {
				o.setP_freedelivery(true);
			}
			o.setBeginAmount(opr.getBeginAmount());
			if (null != opv && null != opv.getVantages()
					&& opv.getVantages() > 0) {
				o.setP_vantages(opv.getVantages());
			}
		}
		List<ShopingCarGoods> vgoodsList = new ArrayList<ShopingCarGoods>();
		if (CheckIsNull.isNotEmpty(vgoodsArray)) {
			JSONArray vga = JSONArray.fromObject(vgoodsArray);
			for (int i = 0; i < vga.size(); i++) {
				JSONObject jsonObject = vga.getJSONObject(i);
				String goodsId = jsonObject.getString("goodsId");
				double count = Double.valueOf(jsonObject.getString("count"));
				ShopingCarGoods scg = new ShopingCarGoods();
				scg.setGoodsId(goodsId);
				scg.setCount(count);
				GoodsVo v = goodsService.getGoodsVo(goodsId);
				if (null != v) {
					// scg.setOriginalprice(v.getOriginalprice());
					scg.setGoodsCode(v.getGoodscode());
					scg.setGoodsName(v.getGoodsname());
					// scg.setPrice(v.getRealprice());
					scg.setSpec(v.getGoodsspec());
					// scg.setVantagesType(v.getVantagestype());
					// if(CheckIsNull.isNotEmpty(v.getVantagestype()))
					// scg.setVantages(DoubleUtil.mul(v.getRealprice(), Double
					// .valueOf(v.getVantagestype())));
					if (v.isVantagesGoods()) {
						scg.setVantagesGoods(true);
						scg.setVantagesCost(v.getVantagesCost());
						if(v.getGoodsType().equals(GoodsType.Booking.getCode()))
						{
							hasBookingGoods = true;
							scg.setBookingGoods(true);
						}
						else
						{
							onlyBookingGoods = false;
						}
						goods.add(scg);
						totalVantagesCost += DoubleUtil.mul(scg.getCount(), scg.getVantagesCost());
					}
				}
			}
		}
		List<GiftVo> otherGifts = this.giftService.getList(login.getRecid());
		if(CheckIsNull.isNotEmpty(otherGifts))
		{
			for(GiftVo gift:otherGifts)
			{
				ShopingCarGoods scg = new ShopingCarGoods();
				scg.setGoodsId(gift.getGoodsid());
				scg.setCount(gift.getGoodscount());
				scg.setOtherGift(true);
				GoodsVo v = goodsService.getGoodsVo(gift.getGoodsid());
				if (null != v) {
					scg.setOriginalprice(v.getOriginalprice());
					scg.setGoodsCode(v.getGoodscode());
					scg.setGoodsName(v.getGoodsname());
					scg.setPrice(0d);
					scg.setSpec(v.getGoodsspec());
					scg.setVantagesType("0");
					scg.setVantages(0);
					goods.add(scg);
				}
			}
		}
		List<MemberAddressVo> addressList = memberService.getAdressList(login
				.getRecid());
//		o.setVgoodsList(vgoodsList);
		o.setOnlyBookingGoods(onlyBookingGoods);
		o.setHasBookingGoods(hasBookingGoods);
		o.setAddressList(addressList);
		o.setGoodsList(goods);
		o.setTotalamount(totalAmount);
		o.setVantages(DoubleUtil.sum(totalVantages,
				null == o.getP_vantages() ? 0 : o.getP_vantages()));
		o.setVantagesCost(totalVantagesCost);
		/**
		 * 送货上门运费价格
		 */
		o.setDeliverPrice(CheckIsNull.isNotEmpty(deliveryPriceService.getPrice())?deliveryPriceService.getPrice():0d);
		/**
		 * 购物袋价格
		 */
		// TODO
//		o.setBagPrice(0.50);
		/**
		 * 配送时间点:上午/下午
		 */
		List<DicItem> list = SparkDictionaryManager
				.getDicItemsList(DictionaryType.DeliveryHour);
		o.setDeliverTimeItems(list);
		maw.addObject("order", o);
		maw.setViewName("/pro/member/orderInfoMain");
		return maw;
	}
	
	private String getFooterHtml(HttpServletRequest request)
	{
		String path = request.getContextPath();
		StringBuffer sb = new StringBuffer();
		GetChannelGoodsListKey key = new GetChannelGoodsListKey();
		key.setChannelId(Constant.SHOPPINGCAR_CHANNELID);
			key.setOffset(0);
			key.setPageSize(20);
		List<ChannelGoodsVo> list = this.channelService.getChannelGoodsVos(key);
		for(ChannelGoodsVo gv:list){
			
			String imgsrc =path+ gv.getPicturepath1();
			String url = path + "/front/toGoodsInfoPage?id=" + gv.getGoodsid();
			
			sb.append("<li>");
			sb.append("<div class=\"scroll-image\">");
			sb.append("<a style=\" width:130;height:130;border:0\" href=\"" + url + "\" target=\"_blank\"><img title=\""+gv.getGoodsName()+"\" src=\""+imgsrc+"\" width=\"130px;\"/></a>");
			sb.append("</div>");
			sb.append("<div class=\"scroll-detail\">");
			String goodsName = "";
			if(gv.getGoodsName() != null && gv.getGoodsName() != ""){
				if(gv.getGoodsName().length() > 10){
					goodsName = gv.getGoodsName().substring(0,10);
				}else{
					goodsName = gv.getGoodsName();
				}
			}
			sb.append("<div><a title=\""+gv.getGoodsName()+"\" target=\"_blank\" href=\"" + url + "\" >"+goodsName+"</a></div>");
			sb.append("<div>规格："+gv.getGoodsspec()+"</div>");
			sb.append("<div><b>￥"+gv.getRealprice()+"/"+gv.getGoodsunit()+"</b></div>");
			sb.append("<div><input type=\"button\" recid=\""+gv.getGoodsid()+"\" value=\"\" onClick=\"shopingCar.addHotGoods(this)\"/></div>");
			sb.append("</div>");
			sb.append("</li>");
			
		}
		return sb.toString();
	}
}
