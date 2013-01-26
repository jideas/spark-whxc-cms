package com.spark.front.action.order;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spark.base.common.utils.CheckIsNull;
import com.spark.base.common.utils.DateUtil;
import com.spark.base.common.utils.DoubleUtil;
import com.spark.cms.action.BaseAction;
import com.spark.cms.base.utils.BeanCopy;
import com.spark.cms.common.Constant;
import com.spark.cms.common.Constant.Base.GoodsType;
import com.spark.cms.common.Constant.Order.OnlineOrderType;
import com.spark.cms.services.ServiceMessage;
import com.spark.cms.services.form.Login;
import com.spark.cms.services.freeDelivery.DeliveryPriceService;
import com.spark.cms.services.gift.GiftService;
import com.spark.cms.services.goods.GoodsService;
import com.spark.cms.services.goodsPromotion.GoodsPromotionService;
import com.spark.cms.services.member.MemberService;
import com.spark.cms.services.order.OrderService;
import com.spark.cms.services.orderPromotion.OrderPromotionService;
import com.spark.cms.services.orderPromotion.result.OrderPromotionResult;
import com.spark.cms.services.vo.GiftVo;
import com.spark.cms.services.vo.GoodsPromotionVo;
import com.spark.cms.services.vo.GoodsVo;
import com.spark.cms.services.vo.MemberVo;
import com.spark.cms.services.vo.OrderDetVo;
import com.spark.cms.services.vo.OrderPromotionVo;
import com.spark.front.form.order.OrderData;
import com.spark.front.form.order.OrderInfo;
import com.spark.front.form.order.ShopingCarGoods;

/**
 * 订单
 * 
 */
@Controller
public class OrderAction extends BaseAction {
	@Autowired
	private MemberService memberService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private GoodsService goodsService;

	@Autowired
	private GoodsPromotionService goodsPromotionService;

	@Autowired
	private OrderPromotionService orderPromotionService;

	@Autowired
	private DeliveryPriceService deliveryPriceService;
	
	@Autowired
	private GiftService giftService;


	/**
	 * 新增订单
	 * 
	 * @return
	 */
	@RequestMapping("/order/createOrder")
	@ResponseBody
	public OrderData createOrder(@RequestParam(value = "orderInfo", required = true)
	String orderInfo, HttpServletRequest request, HttpSession session) {
		OrderData data = new OrderData();
		Login login = getLogin(request);
		if (null == login || CheckIsNull.isEmpty(login.getRecid())) {
			data.setNoLogin(true);
			data.setSuccess(false);
			return data;
		}
		if (CheckIsNull.isEmpty(orderInfo)) {
			data.setSuccess(false);
			data.setErrorMsg("未知错误！");
			return data;
		}
		JSONArray jsonArray = JSONArray.fromObject(orderInfo);
		if (jsonArray.size() <= 0) {
			data.setSuccess(false);
			data.setErrorMsg("未知错误！");
			return data;
		}
		OrderInfo info = new OrderInfo();
		List<OrderDetVo> dets = new ArrayList<OrderDetVo>();
		List<OrderDetVo> bookingDets = new ArrayList<OrderDetVo>();
		List<OrderDetVo> vantagesDets = new ArrayList<OrderDetVo>();
		HashMap<String, Double> pgMap = new HashMap<String, Double>();
		double totalVantages = 0;
		double totalVantagesCost = 0;
		double totalGoodsAmount = 0;
		boolean hasOtherGift = false;
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject jo = jsonArray.getJSONObject(i);

			info.setAddress(jo.getString("address"));
			info.setDeliveryedate(DateUtil
					.convertStringToDate(jo.getString("deliverTime"), DateUtil.DATE_TIME_PATTERN2));
			info.setConsignee(jo.getString("consignee"));
			info.setConsigneetel(jo.getString("mobile"));
			info.setCreatedate(new Date(System.currentTimeMillis()));
			double deliveryCost = CheckIsNull.isEmpty(jo.getString("deliveryCost")) ? 0 : Double.valueOf(jo
					.getString("deliveryCost"));
			info.setMemberid(login.getRecid());
			info.setRealname(CheckIsNull.isNotEmpty(login.getName()) ? login.getName() : login.getUsername());
			info.setStationid(jo.getString("station"));
			info.setStationname(jo.getString("stationname"));
			info.setType(OnlineOrderType.Common.getCode());
			info.setVantages(CheckIsNull.isEmpty(jo.getString("vantages")) ? 0 : Double.valueOf(jo
					.getString("vantages")));

			info.setToDoor("是".equals(jo.getString("toDoor")));

			info.setPayType(jo.getString("payType"));
			
			if(CheckIsNull.isEmpty(info.getAddress()))
			{
				data.setSuccess(false);
				data.setErrorMsg("请填写收货人地址！");
				return data;
			}
			if(CheckIsNull.isEmpty(info.getConsignee()))
			{
				data.setSuccess(false);
				data.setErrorMsg("请填写收货人！");
				return data;
			}
			if(CheckIsNull.isEmpty(info.getConsigneetel()))
			{
				data.setSuccess(false);
				data.setErrorMsg("请填写手机号码！");
				return data;
			}
			if(CheckIsNull.isEmpty(info.getDeliveryedate()))
			{
				data.setSuccess(false);
				data.setErrorMsg("请选择配送时间！");
				return data;
			}
			if(CheckIsNull.isEmpty(info.getStationid()))
			{
				data.setSuccess(false);
				data.setErrorMsg("请选择站点！");
				return data;
			}
			if(CheckIsNull.isEmpty(info.getPayType()))
			{
				data.setSuccess(false);
				data.setErrorMsg("请选择支付方式！");
				return data;
			}
			

			JSONArray detArray = jo.getJSONArray("goodsArray");

			boolean freedelivery = false;
			boolean hasGift = false;
			List<JSONObject> gifts = new ArrayList<JSONObject>();
			List<JSONObject> otherGifts = new ArrayList<JSONObject>();
			for (int j = 0; j < detArray.size(); j++) {
				JSONObject g = detArray.getJSONObject(j);
				String goodsId = g.getString("goodsId");

				if (CheckIsNull.isEmpty(goodsId)) {
					data.setErrorMsg("商品信息已过期！");
					data.setSuccess(false);
					return data;
				}
				if ((CheckIsNull.isEmpty(g.getString("count")) ? 0 : Double.valueOf(g.getString("count"))) <= 0) {
					data.setErrorMsg("商品数量错误！");
					data.setSuccess(false);
					return data;
				}
				double count = Double.valueOf(g.getString("count"));
				GoodsVo gv = this.goodsService.getGoodsVo(goodsId);
				if (null == gv) {
					data.setErrorMsg("商品信息已过期！");
					data.setSuccess(false);
					return data;
				}
				if (gv.isFreedelivery()) {
					freedelivery = true;
				}
				double price = g.getDouble("price");
				/**
				 * 积分商城商品
				 */
				boolean vantagesGoods = "true".equals(g.getString("vantagesGoods"));
				double vantagesCost = 0;
				if (CheckIsNull.isNotEmpty(g.getString("vantagesCost"))) {
					vantagesCost = Double.valueOf(g.getString("vantagesCost"));
				}
				if (price == 0 && !"true".equals(g.getString("isGift"))&&!"true".equals(g.getString("isOtherGift")) && vantagesGoods) {
					if (!gv.isVantagesGoods() || vantagesCost != gv.getVantagesCost()) {
						data.setErrorMsg("商品信息已过期！");
						data.setSuccess(false);
						return data;
					}
					OrderDetVo odv = new OrderDetVo();
					odv.setAmount(0d);
					odv.setPrice(0d);
					odv.setVantagesCost(DoubleUtil.mul(vantagesCost, count, 0));
					odv.setCount(count);
					odv.setGoodscode(gv.getGoodscode());
					odv.setGoodsid(goodsId);
					odv.setGoodsname(gv.getGoodsname());
					odv.setGoodsno(gv.getGoodsno());
					odv.setGoodsspec(gv.getGoodsspec());
					odv.setUnit(gv.getGoodsunit());
					vantagesDets.add(odv);
					totalVantagesCost += odv.getVantagesCost();
					continue;
				}

				if ("null" != g.getString("vantagesType") && CheckIsNull.isNotEmpty(g.getString("vantagesType"))
						&& !g.getString("vantagesType").equals(gv.getVantagestype())&&!"true".equals(g.getString("isGift"))&&!"true".equals(g.getString("isOtherGift"))) {
					data.setErrorMsg("商品:" + gv.getGoodsname() + "积分促销信息已过期！");
					data.setSuccess(false);
					return data;
				}

				double disrate = 0;
				if (CheckIsNull.isNotEmpty(g.getString("disrate"))) {
					disrate = Double.valueOf(g.getString("disrate"));
				}

				double vantages = 0;
				if (CheckIsNull.isNotEmpty(g.getString("vantages"))) {
					vantages = Double.valueOf(g.getString("vantages"));
				}
				if (!vantagesGoods) {
					if (price != gv.getRealprice()&&!"true".equals(g.getString("isGift"))&&!"true".equals(g.getString("isOtherGift"))) {
						/**
						 * 商品促销
						 */
						GoodsPromotionVo gpv = goodsPromotionService.findByGoodsId(goodsId);
						if (null != gpv && gpv.isIsactiving()) {
							if (System.currentTimeMillis() >= gpv.getBegintime()
									&& System.currentTimeMillis() <= gpv.getEndtime()) {
								if (gpv.getPcount() > 0) {
									if ((gpv.getSaledcount() + count) <= gpv.getPcount()) {
										if (disrate != gpv.getDisrate()) {
											data.setErrorMsg("商品:" + gv.getGoodsname() + "打折促销信息已过期！");
											data.setSuccess(false);
											return data;
										}
										if (pgMap.containsKey(goodsId)) {
											double oldValue = pgMap.get(goodsId);
											pgMap.put(goodsId, oldValue + count);
										} else {
											pgMap.put(goodsId, count);
										}
									}
								} else {
									if (disrate != gpv.getDisrate()) {
										data.setErrorMsg("商品:" + gv.getGoodsname() + "打折促销信息已过期！");
										data.setSuccess(false);
										return data;
									}
								}
							}
						} else {
							data.setErrorMsg("商品信息已过期！");
							data.setSuccess(false);
							return data;
						}

					}
				} else {
					if (vantagesCost != gv.getVantagesCost()) {
						data.setErrorMsg("商品信息已过期！");
						data.setSuccess(false);
						return data;
					}
				}
				boolean isGift = "true".equals(g.getString("isGift"));
				boolean isOtherGift = "true".equals(g.getString("isOtherGift"));
				if (isGift) {
					hasGift = true;
					gifts.add(g);
				}
				if(isOtherGift)
				{
					hasOtherGift = true;
					otherGifts.add(g);
				}
				OrderDetVo odv = new OrderDetVo();
				if (isGift||isOtherGift) {
					odv.setAmount(0d);
					odv.setPrice(0d);
					odv.setVantages(0d);
					odv.setVantagesCost(0d);
				} else {
					odv.setAmount(DoubleUtil.mul(price, count, 2));
					odv.setPrice(price);
					odv.setVantages(DoubleUtil.mul(vantages, count, 0));
					totalVantages += odv.getVantages();
					totalGoodsAmount += odv.getAmount();
				}
				odv.setCount(count);
				odv.setDisrate(disrate);
				odv.setGoodscode(gv.getGoodscode());
				odv.setGoodsid(goodsId);
				odv.setGoodsname(gv.getGoodsname());
				odv.setGoodsno(gv.getGoodsno());
				odv.setGoodsspec(gv.getGoodsspec());
				odv.setUnit(gv.getGoodsunit());
				odv.setVantagesType(gv.getVantagestype());
				if (GoodsType.Booking.getCode().equals(gv.getGoodsType())) {
					bookingDets.add(odv);
					totalGoodsAmount -= odv.getAmount();
					totalVantages -= odv.getVantages();
				} else {
					dets.add(odv);
				}

			}
			OrderPromotionResult opr = orderPromotionService.findByOrderAmount(totalGoodsAmount);
			if (hasGift) {
				/**
				 * 整单促销
				 */
				List<ShopingCarGoods> goods = new ArrayList<ShopingCarGoods>();
				if (null != opr) {
					if(opr.isFreeDelivery())
					{
						freedelivery = true;
					}
					OrderPromotionVo opv = opr.getVo();
					if (null != opv && null != opv.getGoodsid() && opv.getGoodscount() > 0) {
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
						if (info.isToDoor() && !freedelivery && info.getDeliveryCost() == 0 && !opr.isFreeDelivery()) {
							data.setErrorMsg("整单免运费促销信息已过期！");
							data.setSuccess(false);
							return data;
						}
					}
					if ((null == jo.getString("p_vantages") ? 0 : Double.valueOf(jo.getString("p_vantages"))) > 0) {
						if (null == opv || null == opv.getVantages() || opv.getVantages() <= 0) {
							data.setErrorMsg("整单积分促销信息已过期！");
							data.setSuccess(false);
							return data;
						}
					}
				}
				if (gifts.size() != goods.size()) {
					data.setErrorMsg("整单促销信息已过期！");
					data.setSuccess(false);
					return data;
				} else {
					for (JSONObject gift : gifts) {
						boolean has = false;
						for (ShopingCarGoods g : goods) {
							if (gift.getString("goodsId").equals(g.getGoodsId())
									&& Double.valueOf(gift.getString("count")) == g.getCount()) {
								has = true;
								break;
							}
						}
						if (!has) {
							data.setErrorMsg("整单促销信息已过期！");
							data.setSuccess(false);
							return data;
						}
					}
				}
			}
			if (null != opr) {
				if(opr.isFreeDelivery())
				{
					freedelivery = true;
				}
			}
			if (info.isToDoor() && !freedelivery) {
				if (null != deliveryPriceService.getPrice() && deliveryPriceService.getPrice() > deliveryCost) {
					data.setSuccess(false);
					data.setErrorMsg("上门送货运费信息已过期！");
					return data;
				}
				info.setDeliveryCost(deliveryCost);
			}
			if(hasOtherGift)
			{
				List<GiftVo> gvs = this.giftService.getList(login.getRecid());
				if(otherGifts.size()!=gvs.size())
				{
					data.setSuccess(false);
					data.setErrorMsg("赠品信息已过期！");
					return data;
				}
				for (JSONObject gift : otherGifts) {
					boolean has = false;
					for (GiftVo g : gvs) {
						if (gift.getString("goodsId").equals(g.getGoodsid())
								&& Double.valueOf(gift.getString("count")) == g.getGoodscount()) {
							has = true;
							break;
						}
					}
					if (!has) {
						data.setErrorMsg("赠品信息已过期！");
						data.setSuccess(false);
						return data;
					}
				}
			}

		}

		try {
			List<OrderInfo> list = new ArrayList<OrderInfo>();
			if (CheckIsNull.isNotEmpty(vantagesDets)) {
				OrderInfo voi = BeanCopy.copy(OrderInfo.class, info);
				voi.setRecid(null);
				voi.setBillsno(null);
				voi.setVantagesCost(totalVantagesCost);
				if (CheckIsNull.isEmpty(dets) && CheckIsNull.isEmpty(bookingDets)) {
					if (info.isToDoor()) {
						data.setSuccess(false);
						data.setErrorMsg("抱歉，积分商城商品暂不提供送货上门服务！");
						return data;
					}
				}
				voi.setTotalamount(0d);
				voi.setDeliveryCost(0d);
				voi.setVantages(0d);
				voi.setType(OnlineOrderType.Common.getCode());
				voi.setDets(vantagesDets);
				voi.setVantagesCostOrder(true);
				list.add(voi);
			}
			if (CheckIsNull.isNotEmpty(dets)) {
				info.setVantages(totalVantages);
				info.setDets(dets);
				info.setType(OnlineOrderType.Common.getCode());
				info.setTotalamount(totalGoodsAmount);
				info.setTotalamount(info.getTotalamount() + info.getDeliveryCost());
				if (info.isToDoor()) {
					if (this.memberService.exeLockDeliveryBalance(info.getMemberid(), true, null) > 0) {
						info.setLockedDeliveryBalance(1);
					}
				}
				list.add(info);
			}
			if (CheckIsNull.isNotEmpty(bookingDets)) {
				OrderInfo boi = BeanCopy.copy(OrderInfo.class, info);
				boi.setRecid(null);
				boi.setBillsno(null);
				boi.setType(OnlineOrderType.Booking.getCode());
				boi.setDets(bookingDets);
				double totalAmount = 0;
				double vantages = 0;
				for (OrderDetVo dv : bookingDets) {
					totalAmount += dv.getAmount();
					vantages += dv.getVantages();
				}
				boi.setTotalamount(totalAmount);
				boi.setVantages(vantages);
				if (CheckIsNull.isEmpty(dets)) {
					boi.setDeliveryCost(info.getDeliveryCost());
					boi.setTotalamount(boi.getTotalamount() + info.getDeliveryCost());
					if (info.isToDoor() && info.getDeliveryCost() > 0) {
						if (this.memberService.exeLockDeliveryBalance(info.getMemberid(), true, null) > 0) {
							boi.setLockedDeliveryBalance(1);
						}
					}
				}
				list.add(boi);
			}
			List<String> ids = this.orderService.createOrders(list);
			if (CheckIsNull.isNotEmpty(ids)) {
				data.setSuccess(true);
				data.setOrderId(ids.toArray(new String[0]));
				data.setPayType(info.getPayType());
				if (pgMap.size() > 0) {
					for (Entry<String, Double> entry : pgMap.entrySet()) {
						String goodsId = entry.getKey();
						double count = entry.getValue();
						ServiceMessage sm = this.goodsPromotionService.changeSaledCount(goodsId, count);
						if (!sm.isOperSuccess()) {
							// TODO
						}
					}
				}
				for (OrderInfo oi : list) {
					for (OrderDetVo det : oi.getDets()) {
						this.goodsService.modifyGoodsSaleCount(det.getGoodsid(), det.getCount());
					}
				}
				if(hasOtherGift)
				{
					this.giftService.updateStatus(login.getRecid());
				}
			}

		} catch (ServiceMessage e) {
			System.out.println(e.getMessage());
			data.setSuccess(false);
			data.setErrorMsg(e.getMessage());
			return data;
		}

		return data;
	}

	private Login getLogin(HttpServletRequest request) {
		Login login = null;
		try {
			if (CheckIsNull.isNotEmpty(request.getSession().getAttribute(Constant.LoginMemberUser)))
				login = new Login((MemberVo) request.getSession().getAttribute(Constant.LoginMemberUser));
		} catch (ServiceMessage e) {
			System.out.println(e.getMessage());
		}
		return login;
	}
}
